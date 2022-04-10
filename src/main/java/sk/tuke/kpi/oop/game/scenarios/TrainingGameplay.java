package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.framework.Scenario;


public class TrainingGameplay extends Scenario implements SceneListener {
    public TrainingGameplay() {

    }

    @Override
    public void setupPlay(@NotNull Scene scene) {
//        Reactor reactor = new Reactor();
//        scene.addActor(reactor,64,64);
//        reactor.turnOn();
//        Cooler cooler = new Cooler(reactor);
//        scene.addActor(cooler,100,100);
//        new ActionSequence<>(new Wait<>(5), new Invoke<>(cooler::turnOn)).scheduleFor(cooler);
//        Hammer hammer = new Hammer();
//        scene.addActor(hammer,100,200);
//        new When<>(() -> reactor.getTemperature() >= 3000, new Invoke<>(() -> reactor.repair())).scheduleFor(reactor);


    }
}
