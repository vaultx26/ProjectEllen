package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.oop.game.behaviours.MovingToRipley;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.characters.Trap;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;

import java.util.Objects;

public class MyScenario implements SceneListener {
    private Ripley player;
    public static class Factory implements ActorFactory {
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            switch (name) {
                case "player":
                    return new Ripley();
                case "ammo":
                    return new Ammo();
                case "health":
                    return new Energy();
                case "door":
                    return new Door(name, Door.Orientation.HORIZONTAL);
                case "door1":
                    return new Door(name , Door.Orientation.VERTICAL);
                case "alien":
                    return new Alien(100,new RandomlyMoving());
                case "alienForRipley" :
                    return new Alien(100 , new MovingToRipley());
                case "hammer":
                    return new Hammer();
                case "trap" :
                    return new Trap();
                case "food" :
                    return new Food();
                case "armorrefill":
                    return new ArmorRefill();
                case "Quest" :
                    return new Quest();
                case "wrench" :
                    return new Wrench();
                case "fire" :
                    return new FireExtinguisher();
                default:
                    return null;
            }
        }
    }
    public void setUpBackpack(Game game , Ripley player) {
        game.pushActorContainer(player.getBackpack());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        SceneListener.super.sceneUpdating(scene);
        if(player != null) {
            player.showRipleyState();
            scene.follow(player);
        }
        int height = Objects.requireNonNull(scene.getGame().getWindowSetup().getHeight());
        int position = height - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("Items you need to find :",950,position);
        position -= GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("Hammer,Wrench,FireExtinguisher",950,position);
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        for(Actor player1 : scene.getActors()) {
            if (player1 instanceof Ripley) {
                player = (Ripley) player1;
                Disposable move = scene.getInput().registerListener(new MovableController(player));
                Disposable keep = scene.getInput().registerListener(new KeeperController(player));
                Disposable shoot = scene.getInput().registerListener(new ShooterController(player));

                setUpBackpack(scene.getGame(), player);
                scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED,player -> {
                        move.dispose();
                        keep.dispose();
                        shoot.dispose();
                        new ActionSequence<>(new Invoke<>(() -> scene.getGame().getOverlay().drawText("Ripley died! Game over",
                            scene.getGame().getWindowSetup().getWidth() / 2,
                            scene.getGame().getWindowSetup().getHeight() / 2).showFor(7)),
                            new Wait<>(3),
                            new Invoke<>(() -> scene.getGame().stop())
                        ).scheduleFor(player);
                });
            }
        }
    }
}
