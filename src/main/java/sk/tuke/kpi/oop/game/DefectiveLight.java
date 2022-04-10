package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class DefectiveLight extends Light implements Repairable{
    private boolean undefect;
    public DefectiveLight() {
        super();
        this.undefect = false;
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::defectiveBehavior)).scheduleFor(this);
    }

    private void defectiveBehavior() {
        if(!this.undefect) {
            int tmp = (int)(Math.random()*20);
            if(tmp == 1) {
                this.toggle();
            }
        }
    }

    @Override
    public boolean repair() {
        if(this.undefect) {
            return false;
        }
        this.undefect = true;
        new ActionSequence<>(
            new Wait<>(10),
            new Invoke<>(this::defect)
        ).scheduleFor(this);
        return true;
    }

    private void defect() {
        this.undefect = false;
    }
}
