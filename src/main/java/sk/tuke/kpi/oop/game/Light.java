package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer{
    private boolean powered;
    private boolean ready;
    private Animation on;
    private Animation off;
    public Light() {
        this.powered = false;
        this.ready = false;
        this.off = new Animation("sprites/light_off.png");
        this.on = new Animation("sprites/light_on.png");
        setAnimation(off);
    }
    private void updateAnimation(){
        if(this.powered && this.ready){
            setAnimation(on);
        }else{
            setAnimation(off);
        }
    }
    public boolean isOn() {
        return this.ready;
    }
    public void turnOn() {
        this.ready = true;
        this.updateAnimation();
    }
    public void turnOff() {
        this.ready = false;
        this.updateAnimation();
    }
    public void setPowered(boolean powered) {
        this.powered = powered;
        this.updateAnimation();
    }

    public void toggle() {
        if(this.isOn()) {
            this.turnOff();
        } else {
            this.turnOn();
        }
    }
}
