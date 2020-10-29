import java.lang.Math;

class PlayerController implements Component {
    private GameObject parent;
    private Scene scene;
    private float dx;
    private float dy;

    public PlayerController(Scene scene, GameObject parent, float dx, float dy) {
        this.scene = scene;
        this.parent = parent;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() { 
        double deltaTime = this.scene.deltaTime;

        // Set game object's forward facing vector to point at mouse position
        Vector2D playerPosition = this.parent.position;
        Vector2D mousePosition = this.scene.mousePosition;
        Vector2D newForward = mousePosition.subtract(playerPosition);
        this.parent.setForwardVector(newForward);

        if (this.scene.getKeyPressed("w")) {
            Vector2D forward = this.parent.getForwardVector();
            Vector2D delta = forward.scale(this.dx * deltaTime);
            this.parent.position = this.parent.position.add(delta);
        }
        
    }
}