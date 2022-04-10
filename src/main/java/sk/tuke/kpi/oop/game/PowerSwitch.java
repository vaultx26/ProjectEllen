package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor  {
    private Switchable switchable;
    public PowerSwitch(Switchable switchable) {
        this.switchable = switchable;
        setAnimation(new Animation("sprites/switch.png"));
    }

    public void toggle() {
        if(this.switchable.isOn()) {
            this.switchOn();
        } else this.switchOff();
    }
    public Switchable getDevice() {
        return this.switchable;
    }
    public void switchOn() {
        if(this.switchable != null) {
            this.switchable.turnOn();
        }
        getAnimation().setTint(Color.WHITE);
    }
    public void switchOff() {
        if(this.switchable != null) {
            this.switchable.turnOff();
        }
        getAnimation().setTint(Color.GRAY);
    }
}
