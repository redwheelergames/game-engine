package game_engine;

import java.util.ArrayList;
import java.util.HashMap;

public class Scene {
    
    public ArrayList<GameObject> gameObjects; 
    public HashMap<String, Scene> transitions;

    public Scene() {
        this.gameObjects = new ArrayList<GameObject> ();
        this.transitions = new HashMap<String, Scene> ();
    }

    public void addGameObject (GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void addTransition (String transitionName, Scene nextScene) {
        this.transitions.put(transitionName, nextScene);
    }

    public Scene transition (String transitionName) {
        if (transitions.containsKey(transitionName)) {
            return transitions.get(transitionName);
        }
        else {
            return this;
        }
    }

}


