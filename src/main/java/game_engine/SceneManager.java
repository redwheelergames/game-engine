package game_engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SceneManager {
    public ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> removedObjects;
    private ArrayList<GameObject> addedObjects;
    private HashMap<String, ArrayList<GameObject>> groups;
    private ArrayList<String> addedGroups;
    private final String DEFAULT_GROUP = new String();

    public Scene nextScene;

    public SceneManager(){
        this.gameObjects = new ArrayList<GameObject>();
        this.removedObjects = new ArrayList<GameObject>();
        this.addedObjects = new ArrayList<GameObject>();
        this.addedGroups = new ArrayList<String>();
        this.groups = new HashMap<String, ArrayList<GameObject>>();
        this.nextScene = null;
    }

    public void removeGameObject(GameObject object){
        this.removedObjects.add(object);
    }

    public void addGameObject(GameObject object){
        this.addedObjects.add(object);
        this.addedGroups.add(this.DEFAULT_GROUP);
    }

    // Add a gameObject to the scene with one group
    public void addGameObject (GameObject gameObject, String groupName) {
        this.addedObjects.add(gameObject);
        this.addedGroups.add(groupName);
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
            //Clear all data structures for scene management
            this.gameObjects.clear();
            this.removedObjects.clear();
            this.addedObjects.clear();
            this.addedGroups.clear();
            this.groups.clear();

            //load the new scene
            this.nextScene.load(game);
            this.nextScene = null;
        }

        //Update the game objects in the scene
        //If number of added groups and game objects differ
        //something went really wrong
        assert this.addedGroups.size() == this.addedObjects.size();

        //Start by removing requested objects from scene
        this.gameObjects.removeAll(this.removedObjects);

        //Next add requested game objects to the scene
        //And add game object to correct group
        for(int i=0; i<this.addedGroups.size(); i++){
            var groupName = this.addedGroups.get(i);
            var gameObject = this.addedObjects.get(i);

            this.groups.putIfAbsent(groupName, new ArrayList<GameObject>());

            // Check if `GameObject` reference has already been added to scene
            if(!this.gameObjects.contains(gameObject)) {
                this.gameObjects.add(gameObject);
            }

            // Check if group already contains reference to gameObject
            if (!this.groups.get(groupName).contains(gameObject)) {
                this.groups.get(groupName).add(gameObject);
            }
        }

        //Clear old requests for game objects to be added or removed from scene
        this.addedObjects.clear();
        this.addedGroups.clear();
        this.removedObjects.clear();

        //Update all Script components
        ArrayList<Script> scripts = this.getComponents(Script.class);
        for (Script script : scripts) {
            script.update();
        }
    }

    public <T extends Component> ArrayList<T> getComponents(Class<T> componentType){
        ArrayList<T> matchingComponents = new ArrayList<T> ();
        for (GameObject gameObject : gameObjects) {
            matchingComponents.addAll(gameObject.getComponents(componentType));
        }
        return matchingComponents;
    }
}
