package game_engine;

import java.util.ArrayList;

// Generic circle collider that has no collission behavior
public class Collider implements Component {
    
    public GameObject parent;
    public int radius;

    public Collider (GameObject parent, int radius) {
        this.parent = parent;
        this.radius = radius;
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
        ArrayList<GameObject> gameObjects = this.parent.game.currentScene.gameObjects;
        for (GameObject gameObject: gameObjects) {
            Collider collider = gameObject.getComponents(Collider.class).get(0);
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