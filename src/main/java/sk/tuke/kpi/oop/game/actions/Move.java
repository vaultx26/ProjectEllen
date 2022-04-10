package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<T extends Movable> implements Action<T> {
    private T actor;
    private Direction direction;
    private int time;
    private boolean done;
    private float duration;
    public Move(Direction direction, float duration) {
        time = 0;
        this.direction =direction;
        this.duration = duration;
        this.done = false;
    }
    public Move(Direction direction) {
        this.direction = direction;
    }
    @Override
    public T getActor(){
        return this.actor;
    }
    @Override
    public void setActor(T move) {
        this.actor = move;
    }
    @Override
    public boolean isDone() {
        return this.done;
    }
    @Override
    public void reset() {
        this.done = false;
        this.time = 0;
        this.duration = 0;
    }
    @Override
    public void execute(float deltaTime) {
        if(actor == null) {
            return;
        }
        duration = duration - deltaTime;
        if(!this.done){
            if(time == 0) {
                actor.startedMoving(direction);
                time += 1;
            }
            if(duration > 0) {
                actor.setPosition(actor.getPosX() + direction.getDx() * actor.getSpeed(), actor.getPosY() + direction.getDy() * actor.getSpeed());
                if(getActor().getScene().getMap().intersectsWithWall(actor)) {
                    actor.setPosition(actor.getPosX() - direction.getDx() * actor.getSpeed(), actor.getPosY() - direction.getDy() * actor.getSpeed());
                    actor.collidedWithWall();
                }
            } else {
                stop();
            }

        }
    }
    public void stop() {
        if(actor != null) {
            this.done = true;
            getActor().stoppedMoving();
        }
    }
}
