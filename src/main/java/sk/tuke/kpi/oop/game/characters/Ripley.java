package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;

public class Ripley extends AbstractActor implements Movable , Keeper , Alive , Armed {
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("RIPLEY DIED", Ripley.class);
    //private int energy;
    private int ammo;
    private int speed;
    private Armor armor;
    private Health health;
    private Backpack backpack;
    private Animation die;
    private Firearm gun;
    public Ripley() {
        super("Ellen");
        //this.energy = 100;
        this.ammo = 100;
        gun = new Gun(100,200);
        this.health = new Health(100);
        this.armor = new Armor(100);
        this.speed = 2;
        this.die = new Animation("sprites/player_die.png",32,32,0.1f,Animation.PlayMode.ONCE);
        this.backpack = new Backpack("Ripley's backpack", 10);
        setAnimation(new Animation(
            "sprites/player.png",
            32,
            32,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        ));
        health.onExhaustion(() -> {
            this.setAnimation(new Animation("sprites/player_die.png",32,32,0.1f,Animation.PlayMode.ONCE));
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED,this);
        });
    }

    @Override
    public Health getHealth() {
        return this.health;
    }
    public void showRipleyState() {
        int height = Objects.requireNonNull(getScene()).getGame().getWindowSetup().getHeight();
        int position = height - GameApplication.STATUS_LINE_OFFSET;
        getScene().getGame().getOverlay().drawText("Health "+this.getHealth().getValue(),120,position);
        getScene().getGame().getOverlay().drawText("Your ammo "+this.getFirearm().getAmmo(),260,position);
        getScene().getGame().getOverlay().drawText("Maximum Ammo "+this.getFirearm().getMaximum(),430,position);
        getScene().getGame().getOverlay().drawText("Your armor " + this.armor.getValueArmor(),630,position);
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    public int getAmmo() {
        return ammo;
    }
//    public void setEnergy(int energy) {
//        this.energy = energy;
//    }
//    public int getEnergy() {
//        return energy;
//    }
    @Override
    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().pause();
    }

    @Override
    public Backpack getBackpack() {
        return this.backpack;
    }

    @Override
    public Firearm getFirearm() {
        return gun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        gun = weapon;
    }
    public void die() {
        if(this.health.getValue() <= 0) {
            setAnimation(die);
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED,this);
        }
    }

    @Override
    public Armor getArmor() {
        return armor;
    }
}
