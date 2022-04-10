package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Trap extends AbstractActor implements Enemy , Alive{
    private Health health;
    public Trap() {
        setAnimation(new Animation("sprites/bomb.png",16,16));
        health = new Health(50);
        health.onExhaustion(() -> getScene().removeActor(this));
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.health.onExhaustion(this::dieTrap);
        drainHealth();
    }
    public void dieTrap() {
        getScene().removeActor(this);
    }
    public void drainHealth() {
        for(Actor actor : this.getScene().getActors()) {
            if(!(actor instanceof Enemy) && actor instanceof Alive && actor.intersects(this)) {
                ((Alive) actor).getHealth().drain(1);
            }
        }
        new Invoke<>(this::drainHealth).scheduleFor(this);
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
