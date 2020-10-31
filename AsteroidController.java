import java.util.Random;

class AsteroidController implements Component {
    
    public Scene scene;
    public GameObject parent;
    public double spriteOffset;
    public int speed;
    private Random rng;

    public AsteroidController (Scene scene, GameObject parent, double spriteOffset, int speed) {
        this.scene = scene;
        this.parent = parent;
        this.spriteOffset = spriteOffset;
        this.speed = speed;
        this.rng = new Random();
    }

    public void update() {
        Vector2D newPosition;
        // Move asteroid to right side of screen if it has moved off the left side of the screen
        if (this.parent.position.x + this.spriteOffset/2 < 0) {
            newPosition = new Vector2D(this.scene.windowWidth, this.rng.nextInt(this.scene.windowHeight));
        }
        else {
            newPosition = this.parent.position.subtract(new Vector2D(this.speed * this.scene.deltaTime, 0));
        }
        this.parent.position = newPosition;
        this.parent.rotation += 5;
    }
}