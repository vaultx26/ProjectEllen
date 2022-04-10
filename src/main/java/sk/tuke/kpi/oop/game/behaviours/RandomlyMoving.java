package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving implements Behaviour<Movable> {
    public void startMove(Movable actor) {
        if(actor == null) return;
        int x , y;
        x = (int) (Math.random() * 3) - 1;
        y = x;
        Direction direction = null;
        for(Direction i : Direction.values()) {
            if(x == i.getDx() && y == i.getDy()) {
                direction = i;
            }
        }
        actor.getAnimation().setRotation(direction.getAngle());
        new Move<>(direction,3).scheduleFor(actor);
    }
    @Override
    public void setUp(Movable actor) {
        if(actor == null) {
            return;
        }
        new Loop<>(new ActionSequence<>(new Invoke<>(this::startMove),new Wait<>(3))).scheduleFor(actor);
    }
}
