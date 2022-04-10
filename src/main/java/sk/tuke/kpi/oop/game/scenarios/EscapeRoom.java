package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class EscapeRoom implements SceneListener {
    public static class Factory implements ActorFactory {
        public @Nullable Actor create(@Nullable String type, String name) {
            switch (name) {
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "door":
                    return new LockedDoor();
                case "alien":
                    return new Alien();
                default:
                    return null;
            }
        }
    }
    @Override
    public void sceneCreated(@NotNull Scene scene) {}

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

    private void setUpBackpack(Game game, Ripley actor) {
        actor.getBackpack().add(new Hammer());
        game.pushActorContainer(actor.getBackpack());
    }

    private void setListeners(Scene scene, Ripley actor) {
        MovableController move = new MovableController(actor);
        KeeperController keep = new KeeperController(actor);
        scene.getInput().registerListener(move);
        scene.getInput().registerListener(keep);
    }
}
