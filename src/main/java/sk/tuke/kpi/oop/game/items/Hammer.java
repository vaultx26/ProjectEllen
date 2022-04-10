package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> implements Collectible {
    public Hammer(){
        super(1);
        setAnimation(new Animation("sprites/hammer.png"));
    }
    public Hammer(int remainingUses){
        super(remainingUses);
        setAnimation(new Animation("sprites/hammer.png"));
    }
    public void useWith(Reactor reactor) {
        if(reactor != null && reactor.repair()) {
            super.useWith(reactor);
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}
