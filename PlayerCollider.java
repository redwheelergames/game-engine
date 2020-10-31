
class PlayerCollider extends Collider {

    public PlayerCollider (Scene scene, GameObject parent, int radius) {
        super(scene, parent, radius);
    }

    public void onCollide(Collider collider) {
        Vector2D thisPosition = this.parent.position;
        Vector2D colliderPosition = collider.parent.position;

        Vector2D distanceVector = thisPosition.subtract(colliderPosition);
        double distance = distanceVector.magnitude();
        double newDistance = this.radius + collider.radius;

        double scaleFactor = (newDistance - distance)/distance; // determine how much the difference vector needs to be scaled by
        Vector2D translate = distanceVector.scale(scaleFactor);
        this.parent.position = this.parent.position.add(translate);
    }
}