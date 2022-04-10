package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Computer extends AbstractActor implements EnergyConsumer{
    private boolean powered;
    public Computer() {
        this.powered = false;
        setAnimation(new Animation("sprites/computer.png",80,48,0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }
    public int add(int n1, int n2) {
        if(this.powered) {
            return n1 + n2;
        }
        return 0;
    }
    public float add(float n1, float n2) {
        if(this.powered) {
            return n1 + n2;
        }
        return 0;
    }
    public int sub(int n1, int n2) {
        if(this.powered) {
            return n1 - n2;
        }
        return 0;
    }
    public float sub(float n1, float n2) {
        if(this.powered) {
            return n1 - n2;
        }
        return 0;
    }

    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }
}
