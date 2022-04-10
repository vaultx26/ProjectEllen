package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {
    private int capacity;
    private String name;
    private List<Collectible> items = new ArrayList<>();
    public Backpack(String name , int capacity) {
        this.capacity = capacity;
        this.name = name;
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return items.iterator();
    }

    @Nullable
    @Override
    public  Collectible peek() {
        if(items.isEmpty()) {
            return null;
        }
        return items.get(this.items.size()-1);
    }

    @Override
    public void shift() {
        if(items.isEmpty() || this.items.size() == 1) {
            return;
        }
        Collections.rotate(items,1);
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if(items.size() < getCapacity()) {
            items.add(actor);
        } else {
            throw new IllegalStateException(getName() + "is full");
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if(items != null) {
            items.remove(actor);
        }
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(items);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

}
