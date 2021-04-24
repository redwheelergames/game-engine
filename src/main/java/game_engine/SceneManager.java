package game_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SceneManager {
    public ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> removedObjects;
    private ArrayList<GameObject> addedObjects;
    private HashMap<String, ArrayList<GameObject>> groups;

    public Scene nextScene;

    public SceneManager(){
        this.gameObjects = new ArrayList<GameObject>();
        this.removedObjects = new ArrayList<GameObject>();
        this.addedObjects = new ArrayList<GameObject>();
        this.groups = new HashMap<String, ArrayList<GameObject>>();
        this.nextScene = null;
    }

    public void removeGameObject(GameObject object){
        this.removedObjects.add(object);
    }

    public void addGameObject(GameObject object){
        this.addedObjects.add(object);
    }

    // Add a gameObject to the scene with one group
    public void addGameObject (GameObject gameObject, String groupName) {
        this.addGameObject(gameObject);

        ArrayList<GameObject> group;
        // Get list of objects in group if groupName is found
        if (this.groups.containsKey(groupName)) {
            group = this.groups.get(groupName);
        }
        // Add new list to groupss if groupName is not found
        else {
            group = new ArrayList<GameObject> ();
            this.groups.put(groupName, group);
        }
        // Check if group already contains reference to gameObject
        if (!group.contains(gameObject)) {
            group.add(gameObject);
        }
    }

    // Add a gameObject to the scene with list groups
    public void addGameObject (GameObject gameObject, List<String> groupNames) {
        this.addGameObject(gameObject);
        for (String groupName: groupNames) {
            this.addGameObject(gameObject, groupName);
        }
    }

    // Return ArrayList of GameObjects within group specified by groupName
    public ArrayList<GameObject> getGroup (String groupName) {
        if (groups.containsKey(groupName)) {
            ArrayList<GameObject> gameObjects = groups.get(groupName);
            ArrayList<GameObject> active = new ArrayList<GameObject> ();
            for (GameObject gameObject : gameObjects) {
                if (gameObject.active) {
                    active.add(gameObject);
                }
            }
            return active;
        }
        else {
            return null;
        }
    }

    public void updateGameObjects(Game game){
        if(this.nextScene != null){
            this.gameObjects.clear();
            this.removedObjects.clear();
            this.addedObjects.clear();

            this.nextScene.load(game);
            this.nextScene = null;
        }

        else{
            this.gameObjects.removeAll(this.removedObjects);
            this.gameObjects.addAll(this.addedObjects);
            this.removedObjects.clear();
            this.addedObjects.clear();

            for(GameObject gameObject: this.gameObjects) {
                if (gameObject.active) {
                    gameObject.update();
                }
            }
        }
    }
}
