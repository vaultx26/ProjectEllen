package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Energy extends AbstractActor implements Usable<Alive> {
    public Energy() {
        setAnimation(new Animation("sprites/energy.png",16,16,0.1f,Animation.PlayMode.ONCE));

    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }

    @Override
    public void useWith(Alive actor) {
//        if(actor == null) return;
//        if(actor.getHealth().getValue() == 100) {
//            return;
//        } else if (actor.getHealth().getValue() < 100){
//            actor.getHealth().restore();
//            Objects.requireNonNull(getScene()).removeActor(this);
//        }
        try {
            actor.getHealth().refill(50);
            getScene().removeActor(this);
        } catch (Exception i) {
            i.printStackTrace();
        }
    }
}
