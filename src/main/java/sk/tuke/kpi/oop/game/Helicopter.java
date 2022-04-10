package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    private Player hell;
    public Helicopter() {
        setAnimation(new Animation("sprites/heli.png",64,64,0.05f,Animation.PlayMode.LOOP));

    }
    public void searchAndDestroy(){
        new Loop<>(new Invoke<>(this::goForPlayer)).scheduleFor(this);
    }

    private void goForPlayer() {
        int newX , newY;
        int hellX = this.hell.getPosX();
        int hellY = this.hell.getPosY();
        if(hellX > this.getPosX()) {
            newX = this.getPosX() + 1;
        } else newX = this.getPosX() - 1;
        if(hellY > this.getPosY()) {
            newY = this.getPosY() + 1;
        }else newY = this.getPosY() - 1;
        this.setPosition(newX,newY);
        if(this.intersects(this.hell)) {
            int tmp = 0;
            if(this.hell.getEnergy() - 1 > 0) {
                tmp = this.hell.getEnergy() - 1;
            } else tmp = 0;
            this.hell.setEnergy(tmp);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.hell = this.getScene().getFirstActorByType(Player.class);
    }

}
