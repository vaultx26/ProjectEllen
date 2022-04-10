package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler{

    public SmartCooler(Reactor reactor) {
        super(reactor);
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::smartBehaviour)).scheduleFor(this);

    }

    private void smartBehaviour() {
        if(this.getReactor() != null) {
            if(this.getReactor().getTemperature() > 2500) {
                this.turnOn();
            } else if(this.getReactor().getTemperature() < 1500) {
                this.turnOff();
            }
        }
    }
}
