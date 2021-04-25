package game_engine;

public class Scene {
    public void addGameObject(GameObject gameObject){
        var sceneManager = gameObject.game.sceneManager;
        sceneManager.addGameObject(gameObject);
    }

    public void addGameObject(GameObject gameObject, String group){
        var sceneManager = gameObject.game.sceneManager;
        sceneManager.addGameObject(gameObject, group);

    }

    public void load(Game game){}

}


