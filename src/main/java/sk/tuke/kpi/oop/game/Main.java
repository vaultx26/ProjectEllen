package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.MyScenario;

public class Main {
    public static void main(String[] args) {
        WindowSetup windowSetup = new WindowSetup("My Project Ellen", 1280, 720);
        Game game = new GameApplication(windowSetup, new LwjglBackend());
        Scene scene = new World("MyScenario","maps/map.tmx",new MyScenario.Factory());
        game.addScene(scene);
        MyScenario myScenario = new MyScenario();
        scene.addListener(myScenario);
        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);

        game.start();

    }
}
