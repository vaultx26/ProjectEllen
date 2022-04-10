package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.Objects;

public class Alien extends AbstractActor implements Movable , Enemy , Alive{
    private Health health;
    private Behaviour<? super Alien> idk;
    public Alien() {
        setAnimation(new Animation("sprites/alien.png",32,32,0.1f,Animation.PlayMode.LOOP));
        health = new Health(100,100);
        health.onExhaustion(() -> getScene().removeActor(this));
    }
    public Alien(int newHealth , Behaviour<? super Alien> behaviour) {
        setAnimation(new Animation("sprites/alien.png",32,32,0.1f,Animation.PlayMode.LOOP));
        health = new Health(newHealth,100);

        idk = behaviour;
        health.onExhaustion(() -> getScene().removeActor(this));
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if(Objects.nonNull(idk)) {
            idk.setUp(this);
        }
        this.health.onExhaustion(this::dieAlien);
        drainIntersecting();
    }
    public void drainIntersecting() {
        for (Actor actor : this.getScene().getActors()) {
            if (!(actor instanceof Enemy) && actor instanceof Alive && actor.intersects(this)) {
                if (((Alive) actor).getArmor().getValueArmor() > 0)
                    ((Alive) actor).getArmor().drainArmor(2);
                else
                    ((Alive) actor).getHealth().drain(1);
            }
        }
        new Invoke<>(this::drainIntersecting).scheduleFor(this);
    }
    @Override
    public void stoppedMoving() {
        this.getAnimation().pause();
    }
    public void dieAlien() {
        getScene().removeActor(this);
    }
    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.getAnimation().play();
        this.getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Armor getArmor() {
        return null;
    }
}
