package com.github.redwheelergames.gameengine;

import java.util.List;
import java.util.ArrayList;

// Generic circle collider that has no collission behavior
public class Collider extends Component {

    public int radius;
    public List<String> groups;

    public Collider (GameObject gameObject, int radius) {
        super(gameObject);
        this.radius = radius;
        this.groups = new ArrayList<String> ();
    }

    public Collider (GameObject gameObject, int radius, List<String> groups) {
        super(gameObject);
        this.radius = radius;
        this.groups = groups;
    }

    // Check if this collider has collided with passed in collider
    public boolean hasCollided(Collider collider) {
        Vector2D thisPosition = this.gameObject.transform.position;
        Vector2D colliderPosition = collider.gameObject.transform.position;
        Vector2D distanceVector = thisPosition.subtract(colliderPosition);
        double distance = distanceVector.magnitude();
        if (distance < this.radius + collider.radius) {
            return true; // If the distance between the two colliders is less than the sum of their radii, they overlap
        }
        else {
            return false;
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