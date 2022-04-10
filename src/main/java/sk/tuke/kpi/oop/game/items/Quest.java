package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Quest extends AbstractActor  implements Usable<Ripley> {
    public Quest() {
        setAnimation(new Animation("sprites/computer.png",80,48,0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }

    @Override
    public void useWith(Ripley actor) {
        int tmp = 0;
        if(actor.intersects(this)) {
           for(Actor actor1 : actor.getBackpack().getContent()) {
               if(actor1 instanceof Hammer) {
                    tmp++;
                    actor.getBackpack().remove((Collectible) actor1);
               }
               if(actor1 instanceof FireExtinguisher) {
                   tmp++;
                   actor.getBackpack().remove((Collectible) actor1);
               }
               if(actor1 instanceof Wrench) {
                   tmp++;
                   actor.getBackpack().remove((Collectible) actor1);
               }
           }
           if(tmp == 3) {
               new ActionSequence<>(new Invoke<>(() -> getScene().getGame().getOverlay().drawText("Your find all items. Game over!",
                   450, 150).showFor(4)),
                   new Wait<>(6),
                   new Invoke<>(() -> getScene().getGame().stop())
               ).scheduleFor(actor);

           }
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
