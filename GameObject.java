import java.util.ArrayList;
import java.lang.Math;

class GameObject {
    public Vector2D<Integer> position;
    public int rotation;
    private Scene scene;
    private ArrayList<Component> components;

    public GameObject(int x, int y, int rotation) {
        this.position = new Vector2D<Integer>(x, y);
        this.rotation = rotation;
        this.components = new ArrayList<Component>();
    }
    
    // returns a unit vector of the objects forward facing direction
    public Vector2D<Double> getForwardVector() {
        double rotationRadians = Math.toRadians(this.rotation);
        return new Vector2D<Double>(Math.cos(rotationRadians), Math.sin(rotationRadians));
    }

    // return a unit vector of the objects right facing direction
    public Vector2D<Double> getRightVector() {
        // Get angle 90 degrees clockwise of forward angle
        int rightAngle = this.rotation - 90;
        double rotationRadians = Math.toRadians(rightAngle);
        return new Vector2D<Double>(Math.cos(rotationRadians), Math.sin(rotationRadians));
    }

    public void setForwardVector(Vector2D<Double> forward) {
        // Calculate angle between new forward vector and <1, 0> (0 degrees)
        double dotProduct = forward.x;
        double magnitude = Math.sqrt(forward.x*forward.x + forward.y*forward.y);
        double radians = Math.acos(dotProduct/magnitude);
        int degrees = (int)Math.toDegrees(radians);
        if (forward.y > 0) {
            this.rotation = degrees;
        }
        else {
            this.rotation = 360 - degrees;
        }

    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    /*
    public Component getComponent(String componentType) {

    }*/

    // 
    public void update() {
        for (Component component : components) {
            component.update();
        }
    }
}