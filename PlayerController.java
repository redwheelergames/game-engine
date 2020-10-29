import java.lang.Math;

class PlayerController implements Component {
    private GameObject parent;
    private Scene scene;
    private int dx;
    private int dy;

    public PlayerController(Scene scene, GameObject parent, int dx, int dy) {
        this.scene = scene;
        this.parent = parent;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() { 
        double deltaTime = this.scene.deltaTime;

        // Set game object's forward facing vector to point at mouse position
        Vector2D<Integer> playerPosition = this.parent.position;
        Vector2D<Integer> mousePosition = this.scene.mousePosition;
        Vector2D<Double> forward = new Vector2D<Double> ((double)(mousePosition.x - playerPosition.x), (double)(mousePosition.y - playerPosition.y));
        this.parent.setForwardVector(forward);

        if (this.scene.getKeyPressed("w")) {
            Vector2D<Double> translation = this.parent.getForwardVector();
            this.parent.position.x += (int)Math.rint(translation.x * this.dx * deltaTime);
            this.parent.position.y += (int)Math.rint(translation.y * this.dx * deltaTime);
        }
        
    }
}