import game_engine.Component;
import game_engine.GameObject;
import game_engine.Game;

public class DrawText implements Component {

    public Game game;
    public GameObject parent;

    public DrawText (GameObject parent) {
        this.parent = parent;
        this.game = parent.game;
    }

    public void update () {
        if (this.game.wasReleased.getKey("mouse1")) {
            this.parent.position = game.mousePosition;
        }      
    }
}