//package sk.tuke.kpi.oop.game.actions;
//
//import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
//import sk.tuke.kpi.oop.game.Reactor;
//
//public class PerpetualReactorHeating extends AbstractAction<Reactor> {
//    private int increase;
//    public PerpetualReactorHeating(int increase) {
//        this.increase = increase;
//    }
//    @Override
//    public void execute(float deltaTime) {
//        if(this.getActor() == null) {
//            setDone(true);
//        } else {
//            this.getActor().increaseTemperature(this.increase);
//        }
//    }
//
//}
