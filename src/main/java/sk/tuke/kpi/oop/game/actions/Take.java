package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Take<A extends Keeper> extends AbstractAction<A> {
    private A player;
    public Take() {
    }
    public Take(A player) {
        this.player = player;
    }

    @Override
    public void execute(float deltaTime) {
        try {
            for(Actor item : getActor().getScene().getActors()) {
                if(item != this.player && getActor().intersects(item)) {
                    getActor().getBackpack().add((Collectible) item);
                    getActor().getScene().removeActor(item);
                    break;
                }
            }
        } catch(IllegalStateException i) {
            i.printStackTrace();
        } catch(Exception j) {
            System.err.println("Unexpected");
            j.printStackTrace();
        } finally {
            setDone(true);
        }
    }
}
