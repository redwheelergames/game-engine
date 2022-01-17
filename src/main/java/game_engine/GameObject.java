package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class GameObject {

    public Game game;
    public boolean active;
    public Transform transform;
    private ArrayList<Component> components;
    
    public GameObject(Game game, boolean active) {
        this.game = game;
        this.active = active;
        this.transform = new Transform();
        this.components = new ArrayList<Component>();
    }

    public GameObject(Game game, boolean active, Transform transform) {
        this.game = game;
        this.active = active;
        this.transform = transform;
        this.components = new ArrayList<Component>();
    }
    
    public GameObject(Game game, boolean active, double posX, double posY, double scaleX, double scaleY, int rotation) {
        this.game = game;
        this.active = active;
        this.transform = new Transform(new Vector2D(posX, posY), new Vector2D(scaleX, scaleY), rotation);
        this.components = new ArrayList<Component>();
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
}