import game_engine.Game;
import game_engine.Scene;

class DemoGame {

    public static void main(String args[]) {
        Game game = new Game(500, 500);
        Scene mainScene = new MainScene();
        game.sceneManager.nextScene = mainScene;
        game.run();
    }

}
