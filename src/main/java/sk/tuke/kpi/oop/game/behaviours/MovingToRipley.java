package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class MovingToRipley implements Behaviour<Movable> {
    private Disposable disposable = null;
    private Move<Movable> move = null;
    public void moveToRipley(Movable actor) {
        if(actor == null) return;
        int x , y;
        int ripleyX , ripleyY;
        x = actor.getPosX();
        y = actor.getPosY();
        ripleyX = actor.getScene().getFirstActorByType(Ripley.class).getPosX();
        ripleyY = actor.getScene().getFirstActorByType(Ripley.class).getPosY();
        Direction direction = null;
        x = x > ripleyX ? x - 1 : x + 1;
        y = y > ripleyY ? y - 1 : y + 1;

        x = x - ripleyX;
        y = y - ripleyY;

        x = x > 0 ? -1 : 1;
        y = y > 0 ? -1 : 1;

        for(Direction direction1 : Direction.values()) {
            if(x == direction1.getDx() && y == direction1.getDy()) {
                direction = direction1;
            }
        }
        if(move != null) {
            move.stop();
            disposable.dispose();
            move = null;
        }
        if(direction != null) {
            move = new Move<>(direction,Float.MAX_VALUE);
            disposable = move.scheduleFor(actor);
        }
    }
    @Override
    public void setUp(Movable actor) {
        if(actor == null) return;
        new Loop<>(new ActionSequence<Movable>(new Invoke<>(() -> {
            moveToRipley(actor);
        }), new Wait<>(1))).scheduleFor(actor);
    }
}
