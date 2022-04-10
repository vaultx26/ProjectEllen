package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable{
    private Reactor reactor;
    private boolean ready;
    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        ready = false;
        setAnimation(new Animation("sprites/fan.png",32,32,0.2f, Animation.PlayMode.LOOP_PINGPONG));
        this.turnOff();
    }
    public void coolReactor() {
        if(this.ready && this.reactor != null) {
            reactor.decreaseTemperature(1);
        }
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
    public Reactor getReactor() {
        return reactor;
    }
    @Override
    public void turnOn() {
        this.ready = true;
        getAnimation().play();
    }
    @Override
    public void turnOff() {
        this.ready = false;
        getAnimation().pause();
    }

    @Override
    public boolean isOn() {
        return this.ready;
    }
}
