import java.lang.Math;

class PlayerController implements Component {

    public GameObject parent;
    public Scene scene;
    public float speed;

    public PlayerController(Scene scene, GameObject parent, float speed) {
        this.scene = scene;
        this.parent = parent;
        this.speed = speed;
    }

    public void update() { 
        double deltaTime = this.scene.deltaTime;

        // Set game object's forward facing vector to point at mouse position
        Vector2D playerPosition = this.parent.position;
        Vector2D mousePosition = this.scene.mousePosition;
        Vector2D newForward = mousePosition.subtract(playerPosition);

        // Don't update rotation if mouse is too close, prevent jumping
        if (newForward.magnitude() > 15) {
            this.parent.setForwardVector(newForward);
        }

        if (this.scene.getKeyPressed("w")) {
            Vector2D forward = this.parent.getForwardVector();
            Vector2D delta = forward.scale(this.speed * deltaTime);
            this.parent.position = this.parent.position.add(delta);
        }
        
    }
}