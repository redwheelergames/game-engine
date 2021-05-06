import java.lang.Math;
import game_engine.*;

class PlayerController extends Script {

    public float speed;

    public PlayerController(GameObject gameObject, float speed) {
        super(gameObject);
        this.speed = speed;
    }

    @Override
    public void update() {
        double deltaTime = this.game.deltaTime;
        if (this.game.wasPressed.getKey("a")) {
            this.gameObject.rotation +=  (int)Math.rint(360 * deltaTime);
        }
        if (this.game.wasPressed.getKey("d")) {
            this.gameObject.rotation -= (int)Math.rint(360 * deltaTime);
        }
        if (this.game.wasPressed.getKey("w")) {
            Vector2D up = this.gameObject.getForwardVector();
            Vector2D delta = up.scale(this.speed * deltaTime);
            this.gameObject.position = this.gameObject.position.add(delta);
        }
        if (this.game.wasPressed.getKey("s")) {
            Vector2D down = this.gameObject.getForwardVector().scale(-1);
            Vector2D delta = down.scale(this.speed*3/4 * deltaTime);
            this.gameObject.position = this.gameObject.position.add(delta);
        }        
    }
}