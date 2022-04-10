package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.Objects;
import java.util.function.Predicate;

public class Observing <A extends Actor, T> implements Behaviour<A> {
    private A actor = null;
    private Topic<T> topic;
    private Predicate<T> predicate;
    private Behaviour<A> delegate;
    public Observing(Topic<T> topic, Predicate<T> predicate , Behaviour<A> delegate) {
        this.topic = topic;
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public void setUp(A actor) {
        this.actor = actor;
        if(actor != null) {
            Objects.requireNonNull(actor.getScene()).getMessageBus().subscribe(topic,this::test);
        }
    }
    private void test(T text) {
        if(!predicate.test(text) || actor == null ) return;
            delegate.setUp(actor);
    }
}
