package game_engine;

// Interface that all components must implement
public class Component {

    public GameObject gameObject; // Parent game object this component is associated with
    public Game game;

    public Component (GameObject gameObject) {
        this.gameObject = gameObject;
        this.gameObject.addComponent(this);
        this.game = this.gameObject.game;
    }

    public GameObject getParentGameObject() {
        return this.gameObject;
    }

}