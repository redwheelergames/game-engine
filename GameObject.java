package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class GameObject {
    
    private Vector2D bufferedPosition;
    private Vector2D position;
    private int bufferedRotation;
    private int rotation;
    public Vector2D scale;
    public Game game;
    public Scene scene;
    public boolean active;
    private ArrayList<Component> components;
    
    public GameObject(Game game, Scene scene, boolean active) {
        this.game = game;
        this.scene = scene;
        this.active = active;
        this.position = new Vector2D(0, 0);
        this.bufferedPosition = new Vector2D(0, 0);
        this.rotation = 0;
        this.bufferedRotation = 0;
        this.scale = new Vector2D(0, 0);
        this.components = new ArrayList<Component>();
    }
    
    public GameObject(Game game, Scene scene, boolean active, double posX, double posY, double scaleX, double scaleY, int rotation) {
        this.game = game;
        this.scene = scene;
        this.active = active;
        this.position = new Vector2D(posX, posY);
        this.bufferedPosition = this.position;
        this.rotation = rotation;
        this.bufferedRotation = this.rotation;
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

    // Return game object's current position
    public Vector2D getPosition() {
        return this.position;
    }

    // Return current value for game object's buffered position
    public Vector2D getNextPosition() {
        return this.bufferedPosition;
    }

    // Set the game object's buffered position - to be set next frame
    public void setPosition(Vector2D position) {
        this.bufferedPosition = position;
    }

    // Return game object's current rotation
    public int getRotation() {
        return this.rotation;
    }

    // Return game object's buffered rotation
    public int getNextRotation() {
        return this.bufferedRotation;
    }

    // Set the game object's buffered rotation - to be set next frame
    public void setRotation(int rotation) {
        this.bufferedRotation = rotation;
    }

    public void update() {
        for (Component component : components) {
            component.update();
        }
        this.position = this.bufferedPosition;
        this.rotation = this.bufferedRotation;
    }
}