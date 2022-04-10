package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{
    public Gun(int current , int maximum) {
        super(current,maximum);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
