package game_engine;

import java.lang.Math;

public class Vector2D {
    
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D vector) {
        double x = this.x + vector.x;
        double y = this.y + vector.y;
        return new Vector2D(x, y);
    }

    public Vector2D subtract(Vector2D vector) {
        double x = this.x - vector.x;
        double y = this.y - vector.y;
        return new Vector2D(x, y);
    }

    public Vector2D scale(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        return new Vector2D(x, y);
    }
    
    public double dotProduct(Vector2D vector) {
        return this.x*vector.x + this.y*vector.y;
    }

    public double magnitude() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }
}