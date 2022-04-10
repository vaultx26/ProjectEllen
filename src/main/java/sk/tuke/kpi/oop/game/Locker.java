package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class Locker extends AbstractActor implements Usable<Ripley> {
    private boolean used;
    public Locker() {
        setAnimation(new Animation("sprites/locker.png",16,16));
        this.used = false;
    }

    @Override
    public void useWith(Ripley actor) {
        if(!used) {
            used = true;
            Objects.requireNonNull(getScene()).addActor(new Hammer(),getPosX(),getPosY());
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
