package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Set<Direction> pressedKeys;
    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP , Direction.NORTH),
        Map.entry(Input.Key.LEFT , Direction.WEST),
        Map.entry(Input.Key.DOWN , Direction.SOUTH),
        Map.entry(Input.Key.RIGHT , Direction.EAST)
        );
    private Actor actor;
    private Move<Movable> move = null;
    public MovableController(Movable actor) {
        this.actor = actor;
        pressedKeys = new HashSet<>();
    }
    @Override

    public void keyPressed(@NotNull Input.Key key) {
        if(keyDirectionMap.containsKey(key)) {
            pressedKeys.add(keyDirectionMap.get(key));
            update();
        }
    }

    @Override

    public void keyReleased(@NotNull Input.Key key) {
        if(keyDirectionMap.containsKey(key)) {
            pressedKeys.remove(keyDirectionMap.get(key));
            update();
        }
    }

    private void update() {
        stopMove();
        Direction newDirection = Direction.NONE;
        for(Direction direction : pressedKeys) {
            newDirection = newDirection.combine(direction);
        }
        if(!newDirection.equals(Direction.NONE)) {
            startMove(newDirection);
        }
    }
    private void stopMove() {
        if(Objects.nonNull(move)) {
            move.stop();
        }
    }
    private void startMove(Direction direction) {
        move = new Move<Movable>(direction,Float.MAX_VALUE);
        move.scheduleFor((Movable) actor);
    }
}
