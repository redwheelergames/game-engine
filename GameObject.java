package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class GameObject {
    
    public Vector2D position;
    public Vector2D scale;
    public int rotation;
    public Game game;
    public boolean active;
    private ArrayList<Component> components;
    
    public GameObject(Game game, boolean active) {
        this.game = game;
        this.active = active;
        this.position = new Vector2D(0, 0);
        this.rotation = 0;
        this.scale = new Vector2D(0, 0);
        this.components = new ArrayList<Component>();
    }
    
    public GameObject(Game game, boolean active, double posX, double posY, double scaleX, double scaleY, int rotation) {
        this.game = game;
        this.active = active;
        this.position = new Vector2D(posX, posY);
        this.rotation = rotation;
        this.scale = new Vector2D(scaleX, scaleY);
        this.components = new ArrayList<Component>();
    }
    
    // returns a unit vector of the object's forward facing direction
    public Vector2D getForwardVector() {
        double rotationRadians = Math.toRadians(this.rotation);
        return new Vector2D(Math.cos(rotationRadians), Math.sin(rotationRadians));
    }

    // return a unit vector of the object's right facing direction
    public Vector2D getRightVector() {
        // Get angle 90 degrees clockwise of forward angle
        int rightAngle = this.rotation - 90;
        double rotationRadians = Math.toRadians(rightAngle);
        return new Vector2D(Math.cos(rotationRadians), Math.sin(rotationRadians));
    }

    // Set rotation based on forward facing vector
    public void setForwardVector(Vector2D forward) {
        // Calculate signed angle between new forward vector and <1, 0> (0 degrees)
        Vector2D positiveX = new Vector2D(1, 0);
        this.rotation = (int)positiveX.angleSigned(forward);
    }

    // Add new component to instance
    public void addComponent(Component component) {
        this.components.add(component);
    }

    // Return ArrayList of components of matching type
    public <T extends Component> ArrayList<T> getComponents(Class<T> type) {
        ArrayList<T> matchingComponents = new ArrayList<T> ();
        for (Component component: this.components) {
            boolean isType = type.isAssignableFrom(component.getClass());
            // return component if it is of type or a subtype of 'type'
            if (isType) {
                matchingComponents.add(type.cast(component));
            }
        }
        return matchingComponents;
    }

    public void update() {
        for (Component component : components) {
            component.update();
        }
    }
}