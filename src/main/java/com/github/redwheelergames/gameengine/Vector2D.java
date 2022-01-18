package com.github.redwheelergames.gameengine;

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

    // Return z component of 3D cross product between this vector and passed in vector
    public double crossProduct(Vector2D vector) {
        return this.x*vector.y - this.y*vector.x;
    }

    public Vector2D normalized() {
        return this.scale(1/this.magnitude());
    }

    // Get unsigned angle between this vector and passed in vector
    public double angleUnsigned(Vector2D vector) {
        double dotProduct = this.dotProduct(vector);
        double radians = Math.acos(dotProduct / (this.magnitude() * vector.magnitude()));
        return Math.toDegrees(radians);
    }

    // Get signed angle from this vector to passed in vector
    public double angleSigned(Vector2D vector) {
        double unsigned = this.angleUnsigned(vector);
        double crossProduct = this.crossProduct(vector);
        if (crossProduct < 0) {
            return unsigned * -1;
        }
        else {
            return unsigned;
        }

    }

    public double magnitude() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }
}