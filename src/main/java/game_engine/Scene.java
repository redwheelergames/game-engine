package game_engine;

public class Scene {
    //Added for backwards compatibility
    public void addGameObject(GameObject gameObject){
        var sceneManager = gameObject.game.sceneManager;
        sceneManager.addGameObject(gameObject);
    }

    //Added for backwards compatibility
    public void addGameObject(GameObject gameObject, String group){
        var sceneManager = gameObject.game.sceneManager;
        sceneManager.addGameObject(gameObject, group);

    }

    public void load(Game game){}

}


