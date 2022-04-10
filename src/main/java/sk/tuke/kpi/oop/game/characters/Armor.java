package sk.tuke.kpi.oop.game.characters;

public class Armor{
    private int current;
    private int maximum;
    public Armor(int current , int maximum) {
        this.current = current;
        this.maximum = maximum;
    }
    public Armor(int current) {
        this.maximum = current;
        this.current = current;
    }
    public int getValueArmor() {
        return this.current;
    }
    public void drainArmor(int amount) {
        if(this.current > 0) {
            if(this.current > amount) {
                this.current -= amount;
            } else {
                this.current = 0;
            }
        }
    }
    public void restoreArmor() {
        this.current = this.maximum;
    }
    public void refillArmor(int amount) {
        this.current += amount;
        if(this.current > this.maximum) {
            restoreArmor();
        }
    }
}
