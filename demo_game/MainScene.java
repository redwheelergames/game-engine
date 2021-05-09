import game_engine.*;
import java.util.ArrayList;

class MainScene extends Scene {
    @Override
    public void load (Game game) {
        GameObject player = new GameObject(game, true, game.windowWidth/2, game.windowHeight/2, 2, 2, 90);
        ArrayList<String> framePaths = new ArrayList<String> ();
        framePaths.add("player_idle_1.png");
        framePaths.add("player_idle_2.png");
        framePaths.add("player_idle_3.png");
        framePaths.add("player_idle_4.png");
        new Animation(player, framePaths, 15, true);
        new PlayerController(player, 200);
        game.sceneManager.addGameObject(player);
    }
}