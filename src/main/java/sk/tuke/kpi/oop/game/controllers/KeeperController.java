package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;



public class KeeperController implements KeyboardListener {
    private Keeper keeper;
    public KeeperController(Keeper keeper) {
        this.keeper = keeper;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        switch (key) {
            case S:
                new Shift<>().scheduleFor(this.keeper);
                break;
            case ENTER:
                if(this.keeper != null) {
                    new Take<>(this.keeper).scheduleFor(this.keeper);
                }
                break;
            case BACKSPACE:
                new Drop<>().scheduleFor(this.keeper);
                break;
            case U:
                this.hUse();
                break;
            case B:
                this.useBackpack();
                break;
            default:
                break;
        }
    }

    private void useBackpack() {
//        Collectible items = this.keeper.getBackpack().peek();
//        if(items instanceof Usable) {
//            new Use<>((Usable<? extends Actor>) items).scheduleForIntersectingWith(this.keeper);
//            this.keeper.getBackpack().remove(Objects.requireNonNull(items));
//        }
        if (keeper.getBackpack().peek() instanceof Usable) {
            Use<?> use=new Use<>((Usable<?>)keeper.getBackpack().peek());
            use.scheduleForIntersectingWith(keeper);
        }

    }

    private void hUse() {
//        for(Actor item : this.keeper.getScene().getActors()) {
//            if(item instanceof Usable && this.keeper.intersects(item)) {
//                new Use<>((Usable<? extends Actor>) item).scheduleForIntersectingWith(this.keeper);
//            }
//        }
        Usable<?> usable = keeper.getScene().getActors().stream().filter(Usable.class::isInstance).filter(keeper::intersects).map(Usable.class::cast).findFirst().orElse(null);
        if (usable != null) {
            new Use<>(usable).scheduleForIntersectingWith(keeper);
        }

    }
}
