package game_engine;

import java.util.List;
import java.util.ArrayList;

// Generic circle collider that has no collission behavior
public class Collider implements Component {
    
    public GameObject parent;
    public int radius;
    public List<String> groups;

    public Collider (GameObject parent, int radius) {
        this.parent = parent;
        this.radius = radius;
        this.groups = new ArrayList<String> ();
    }

    public Collider (GameObject parent, int radius, List<String> groups) {
        this.parent = parent;
        this.radius = radius;
        this.groups = groups;
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
        // If no groups are specified, check all gameObjects in the scene
        if (this.groups.size() == 0) {
            ArrayList<GameObject> gameObjects = this.parent.game.sceneManager.gameObjects;
            for (GameObject gameObject: gameObjects) {
                ArrayList<Collider> colliders = gameObject.getComponents(Collider.class);
                for (Collider collider: colliders) {
                    if (collider != this && hasCollided(collider)) {
                        this.onCollide(collider);
                    }
                }
            }
        }
        // If groups are specified
        else {
            // Iterate through all specified groups
            for (String groupName : this.groups) {
                var gameObjects = this.parent.game.sceneManager.getGroup(groupName);
                for (GameObject gameObject: gameObjects) {
                    // Get all colliders on gameObject
                    ArrayList<Collider> colliders = gameObject.getComponents(Collider.class);
                    for (Collider collider: colliders) {
                        if (collider != this && hasCollided(collider)) {
                            this.onCollide(collider, groupName);
                        }
                    }
                }
            }
        }
    }

    // Override to provide unique collision behavior
    public void onCollide(Collider collider) {
        return;
    }

    // Override to provide unique collision behavior
    public void onCollide(Collider collider, String group) {
        return;
    }
}