package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Bullet extends AbstractActor implements Fireable {
    private int speed;
    private int damage = 10;
    public Bullet() {
        speed = 4;
        setAnimation(new Animation("sprites/laser.png",16,16));

    }

    @Override
    public void startedMoving(Direction direction) {
        if(direction != null && direction != Direction.NONE) {
            this.getAnimation().setRotation(direction.getAngle());
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<Actor>(() -> {
        scene.getActors().forEach(actor -> {
            if(!(actor instanceof Ripley) && (actor instanceof Alive && this.intersects(actor)))
            {((Alive) actor).getHealth().drain(damage);
                scene.removeActor(this);
            }
        }
        );
        })).scheduleFor(this);
    }

    @Override
    public void collidedWithWall() {
        getScene().removeActor(this);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

}
