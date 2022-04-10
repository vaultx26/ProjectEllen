package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;


public class Use<T extends Actor> extends AbstractAction<T> {
    private Usable<T> usableActor;
    public Use(Usable<T> usableActor) {
        this.usableActor = usableActor;
    }
    @Override
    public void execute(float deltaTime) {
        this.usableActor.useWith(getActor());
        setDone(true);
    }
    public Disposable scheduleForIntersectingWith(Actor mediatingActor) {
        Scene scene = mediatingActor.getScene();
        if (scene == null) return null;
        Class<T> usingActorClass = usableActor.getUsingActorClass();  // `usable` je spominana clenska premenna
        for (Actor actor : scene) {
            if (mediatingActor.intersects(actor) && usingActorClass.isInstance(actor)) {
                return this.scheduleFor(usingActorClass.cast(actor));  // naplanovanie akcie v pripade, ze sa nasiel vhodny akter
            }
        }
        return null;
    }
}
