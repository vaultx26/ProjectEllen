package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int current;
    private int maximum;
    public Firearm(int maximum) {
        this.maximum = maximum;
        this.current = maximum;
    }
    public Firearm(int current,int maximum) {
        this.current = current;
        this.maximum = maximum;
    }

    public int getAmmo() {
        return this.current;
    }

    public int getMaximum() {
        return this.maximum;
    }

    public void reload(int newAmmo) {
        this.current = getAmmo() + newAmmo < this.maximum ? this.current + newAmmo : this.maximum;
    }
    protected abstract Fireable createBullet();
    public Fireable fire() {
        if(this.current == 0) {
            return null;
        }
        this.current--;
        return createBullet();
    }
}
