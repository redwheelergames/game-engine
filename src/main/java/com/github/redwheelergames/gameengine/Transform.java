package com.github.redwheelergames.gameengine;

public class Transform {

    public Vector2D position;
    public Vector2D scale;
    public int rotation;

    public Transform() {
        this(new Vector2D(0, 0), new Vector2D(0, 0), 0);
    }

    public Transform (Vector2D position, Vector2D scale, int rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    // returns a unit vector of the object's forward facing direction
    public Vector2D getForwardVector() {
        double rotationRadians = Math.toRadians(this.rotation);
        return new Vector2D(Math.cos(rotationRadians), Math.sin(rotationRadians));
    }

    // Set rotation based on forward facing vector
    public void setForwardVector(Vector2D forward) {
        // Calculate signed angle between new forward vector and <1, 0> (0 degrees)
        Vector2D positiveX = new Vector2D(1, 0);
        this.rotation = (int)positiveX.angleSigned(forward);
    }
}
