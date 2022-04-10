package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door{
    private boolean locked;
    public LockedDoor() {
        super();
        this.locked = true;
    }

    @Override
        public void useWith(Actor actor) {
        if(!isLocked())
            super.useWith(actor);
    }

    public void lock() {
        this.locked = true;
        this.close();
    }
    public void unlock() {
        this.locked = false;
        this.open();
    }
    private boolean isLocked() {
        return this.locked;
    }
}
