package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

public class ArmorRefill extends AbstractActor implements Usable<Alive> {
    public ArmorRefill() {
        setAnimation(new Animation("sprites/button_green.png",16,16));
    }

    @Override
    public void useWith(Alive actor) {
        if(actor == null) return;
        if(actor.intersects(this)) {
            if(actor.getArmor().getValueArmor() < 100) {
                actor.getArmor().refillArmor(50);
                this.getScene().removeActor(this);
            }
        }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
