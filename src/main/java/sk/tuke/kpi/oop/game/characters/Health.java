package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Health {
    private Set<ExhaustionEffect> effectListeners = new HashSet<>();
    private int currentHealth;
    private int maximumHealth;
    public Health(int currentHealth, int maximumHealth) {
        this.currentHealth = currentHealth;
        this.maximumHealth = maximumHealth;
    }
    public Health(int currentHealth) {
        this.currentHealth = currentHealth;
        this.maximumHealth = currentHealth;
    }
    public int getValue() {
        return this.currentHealth;
    }

    public void drain(int amount) {
        if(this.currentHealth > 0) {
            if(this.currentHealth > amount) {
                this.currentHealth -= amount;
            } else exhaust();
        }
    }
    void exhaust() {
        if(this.currentHealth > 0) {
            this.currentHealth = 0;
        }
        if(effectListeners != null) {
            effectListeners.forEach(ExhaustionEffect::apply);
            effectListeners.clear();
        }
    }
    public void restore() {
        this.currentHealth = this.maximumHealth;
    }
    public void refill(int amount) {
        this.currentHealth += amount;
        if(this.currentHealth > this.maximumHealth) {
            restore();
        }
    }
    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }
    public void onExhaustion(@NotNull Health.ExhaustionEffect effect) {
        effectListeners.add(effect);
    }
}
