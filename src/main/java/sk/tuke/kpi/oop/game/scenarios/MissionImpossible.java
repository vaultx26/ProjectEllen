package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {

    public static class Factory implements ActorFactory {
        public @Nullable Actor create(@Nullable String type, String name) {
            switch (name) {
                case "ellen" :
                    return new Ripley();
                case "energy" :
                    return new Energy();
                case "door" :
                    return new LockedDoor();
                case "access card" :
                    return new AccessCard();
                case "ventilator" :
                    return new Ventilator();
                case "locker" :
                    return new Locker();
                default:
                    return null;
            }
        }
    }
    public void setListeners(Scene scene , Ripley ripley) {
        MovableController move = new MovableController(ripley);
        KeeperController keep = new KeeperController(ripley);
        scene.getInput().registerListener(move);
        scene.getInput().registerListener(keep);
    }
    public void setUpBackpack(Game game , Ripley ripley) {
        ripley.getBackpack().add(new Hammer());
        game.pushActorContainer(ripley.getBackpack());
    }
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        for(Actor actor : scene.getActors()) {
            if(actor instanceof Ripley) {
                scene.follow(actor);
                setListeners(scene, (Ripley) actor);
                setUpBackpack(scene.getGame(),(Ripley) actor);
            }
        }
    }
}
