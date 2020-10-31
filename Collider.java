import java.util.ArrayList;

// Generic circle collider that has no collission behavior
class Collider implements Component {
    
    public GameObject parent;
    public Scene scene;
    public int radius;

    public Collider (Scene scene, GameObject parent, int radius) {
        this.parent = parent;
        this.radius = radius;
        this.scene = scene;
    }

    // Check if this collider has collided with passed in collider
    public boolean hasCollided(Collider collider) {
        Vector2D thisPosition = this.parent.position;
        Vector2D colliderPosition = collider.parent.position;
        Vector2D distanceVector = thisPosition.subtract(colliderPosition);
        double distance = distanceVector.magnitude();
        if (distance < this.radius + collider.radius) {
            return true; // If the distance between the two colliders is less than the sum of their radii, they overlap
        }
        else {
            return false;
        }
    }

    public void update() {
        ArrayList<GameObject> gameObjects = scene.gameObjects;
        for (GameObject gameObject: gameObjects) {
            Collider collider = gameObject.getComponent(Collider.class);
            if (collider != null && collider != this && hasCollided(collider)) {
                collider.onCollide(this); // notify collider that this collider has collided with it
            }
        }
    }

    // Override to provide unique collision behavior
    public void onCollide(Collider collider) {
        return;
    }
}