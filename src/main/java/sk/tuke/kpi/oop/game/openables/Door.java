package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class Door extends AbstractActor implements Usable<Actor> , Openable {
    private Animation closedAnim;
    public enum Orientation {VERTICAL,HORIZONTAL}
    private Animation openedAnim;
    private boolean opened;
    private String source = "sprites/vdoor.png";
    public static final Topic<Door> DOOR_OPENED = Topic.create("Door Opened",Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("Door Closed",Door.class);
    public Door() {
        closedAnim = new Animation(source,16,32,0.1f,Animation.PlayMode.ONCE);
        openedAnim = new Animation(source,16,16,0.1f,Animation.PlayMode.ONCE_REVERSED);
        setAnimation(new Animation(source,16,32));
        setAnimation(new Animation("sprites/vdoor.png",16,32,0.1f));
        getAnimation().pause();
        this.opened = false;
    }
    public Door(String name , Orientation orientation) {
        super(name);
        this.opened = false;
        if(orientation == Orientation.VERTICAL) {
            setAnimation(new Animation("sprites/vdoor.png",16,32,0.1f));
            closedAnim = new Animation(source,16,32,0.1f,Animation.PlayMode.ONCE);
            openedAnim = new Animation(source,16,16,0.1f,Animation.PlayMode.ONCE_REVERSED);
            setAnimation(new Animation(source,16,32));
            getAnimation().stop();
        } else if(orientation == Orientation.HORIZONTAL) {
            closedAnim = new Animation("sprites/hdoor.png",32,16,0.1f,Animation.PlayMode.ONCE);
            openedAnim = new Animation("sprites/hdoor.png",32,16,0.1f,Animation.PlayMode.ONCE_REVERSED);
            setAnimation(new Animation("sprites/hdoor.png",32,16));
            getAnimation().stop();
        }
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() / 16,this.getPosY() / 16).setType(MapTile.Type.WALL);
        getScene().getMap().getTile(this.getPosX() / 16,this.getPosY() / 16 + 1).setType(MapTile.Type.WALL);
    }
    @Override
    public void useWith(Actor actor) {
        if(isOpen()) {
            close();
        } else open();
    }

    @Override
    public boolean isOpen() {
        return this.opened;
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        this.opened = true;
        Objects.requireNonNull(getScene()).getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
        getScene().getMap().getTile(getPosX() / 16 , getPosY() / 16 + 1).setType(MapTile.Type.CLEAR);
        setAnimation(openedAnim);
        getAnimation().play();
        getAnimation().stop();
        getScene().getMessageBus().publish(DOOR_OPENED,this);
    }

    @Override
    public void close() {
        this.opened = false;
        Objects.requireNonNull(getScene()).getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
        getScene().getMap().getTile(getPosX() / 16 , getPosY() / 16 + 1).setType(MapTile.Type.WALL);
        setAnimation(closedAnim);
        getAnimation().play();
        getAnimation().stop();
        getScene().getMessageBus().publish(DOOR_CLOSED,this);
    }
}
