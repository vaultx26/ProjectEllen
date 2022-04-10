package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<A extends Keeper> extends AbstractAction<A> {
    public Drop() {

    }

    @Override
    public void execute(float deltaTime) {
        try {
            Collectible items = getActor().getBackpack().peek();
            getActor().getScene().addActor(items,getActor().getPosX()+items.getWidth()/2 , getActor().getPosX() + items.getHeight() / 2);
            getActor().getBackpack().remove(items);
        } catch(Exception a) {
            a.printStackTrace();
        }
        setDone(true);
    }
}
