package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
//import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable , Repairable{
    private boolean ready;
    private boolean extinguish;
    private int temperature, damage;
    private Animation off;
    private Animation broke;
    private Animation hot;
    private Animation simple;
    private Animation extinguishAnimation;
    private Set<EnergyConsumer> devices;

    public Reactor() {
        this.ready = false;
        this.extinguish = false;
        this.temperature = 0;
        this.damage = 0;
        this.devices = new HashSet<>();
        this.off = new Animation("sprites/reactor.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        this.simple = new Animation("sprites/reactor_on.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        this.hot = new Animation("sprites/reactor_hot.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        this.extinguishAnimation = new Animation("sprites/reactor_extinguished.png",80,80,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.broke = new Animation("sprites/reactor_broken.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        this.updateAnimation();
    }

    public int getTemperature() {
        return this.temperature;
    }
    public int getDamage() {
        return this.damage;
    }

    public boolean isOn() {
        return this.ready;
    }
    public void addDevice(EnergyConsumer device) {
        this.devices.add(device);
        this.updateDevice();
    }
    public void removeDevice(EnergyConsumer energyConsumer) {
        energyConsumer.setPowered(false);
        this.devices.remove(energyConsumer);
        this.updateDevice();

    }
    private void updateDevice() {
        for(EnergyConsumer device : this.devices) {
            device.setPowered(this.isOn());
        }
    }

//    @Override
//    public void addedToScene(@NotNull Scene scene) {
//        scene.scheduleAction(new PerpetualReactorHeating(1),this);
//    }
    public void increaseTemperature(int increment) {
        if(!isOn() || increment <= 0) return;
        if(this.damage < 33) {
            this.temperature += increment;
        } else if(this.damage <= 66) {
            this.temperature += increment * 1.5f;
        } else if(this.damage < 100) {
            this.temperature += increment * 2;
        } else return;
        if (this.temperature > 2000 && this.damage < (this.temperature - 2000) / 40) {
            this.damage = (this.temperature - 2000) / 40;
        }
        if(this.temperature >= 6000) {
            this.damage = 100;
            this.turnOff();
        }
        this.updateAnimation();
    }
    public void decreaseTemperature(int decrement) {
        if(!isOn() || decrement <= 0 || damage >= 100) return;
        if(this.damage < 50) {
            this.temperature = this.temperature - decrement;
            if(this.temperature < 0) {
                this.temperature = 0;
            }
        } else if(this.damage < 100) {
            if(this.temperature - decrement * 0.5f > 0) {
                this.temperature -= decrement * 0.5f;
                Math.round(this.temperature);
            } else this.temperature = 0;
        } else return;
        this.updateAnimation();
    }
    private void updateAnimation(){
        if (!this.ready){
            if(this.damage == 100){
                if(!this.extinguish){
                    setAnimation(this.broke);
                }else{
                    setAnimation(this.extinguishAnimation);
                }
            }else {
                setAnimation(this.off);
            }
        } else {
            if (this.temperature <= 4000) {
                setAnimation(this.simple);
            } else if (this.temperature < 6000) {
                setAnimation(this.hot);
            } else {
                setAnimation(this.broke);
            }
        }
    }
    @Override
    public boolean repair() {
        if(this.damage == 0 || this.damage == 100) {
            return false;
        }
        if(this.damage - 50 <= 0) {
            this.damage = 0;
        } else {
            this.damage = this.damage - 50;
        }
        if(damage * 40 + 2000 < this.temperature) {
            this.temperature = damage * 40 + 2000;
        }
        this.updateAnimation();
        return true;
    }
    public boolean extinguish() {
        if (this.damage >= 100) {
            if (this.temperature - 4000 > 0) {
                this.temperature -= 4000;
            } else this.temperature = 0;
            this.extinguish = true;
            this.updateAnimation();
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void turnOff() {
        this.ready = false;
        this.updateAnimation();
        this.updateDevice();
    }
    @Override
    public void turnOn()  {
        if(this.damage < 100) {
            this.ready = true;
            this.updateAnimation();
            this.updateDevice();
        }
    }

}
