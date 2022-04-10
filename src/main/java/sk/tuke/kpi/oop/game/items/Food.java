package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Food extends AbstractActor implements Usable<Alive>{
    public Food() {
        setAnimation(new Animation("sprites/hamburger.png",16,16));
    }

    @Override
    public void useWith(Alive actor) {
        if(actor == null) {
            return;
        }
        if(actor.intersects(this)) {
            if(actor.getHealth().getValue() < 100) {
                actor.getHealth().refill(10);
            }
            if(actor instanceof Ripley) {
                new ActionSequence<Ripley>(
                new Invoke<>(() -> ((Ripley) actor).setSpeed(4)),
                new Wait<>(4),
                new Invoke<>(() -> ((Ripley) actor).setSpeed(2))).scheduleFor((Ripley) actor);
            }
            getScene().removeActor(this);
        }

    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
