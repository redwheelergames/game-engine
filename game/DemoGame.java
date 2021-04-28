import game_engine.*;

class DemoGame {

    public static void main(String[] args) {
        Game game = new Game(1280, 720);
        Scene scene = new MainScene();
        game.sceneManager.nextScene = scene;
        game.run();
    }

    static public class MainScene extends Scene {
        public void load (Game game) {
            GameObject background = new GameObject(game, this, true, game.windowWidth/2, game.windowHeight/2, 2, 2, 90);
            Sprite backgroundSprite = new Sprite(background, "background.png");
            background.addComponent(backgroundSprite);
            this.addGameObject(background);

            // Create GameObjects here
            GameObject text = new GameObject(game, this, true, 0, 0, 2, 2, 90);
            Text textComp = new Text(text, "THIS IS A TEST", 18);
            //text.addComponent(new Sprite(text, "alpha_boid_sprite.png"));
            text.addComponent(new DrawText(text));
            text.addComponent(textComp);
            this.addGameObject(text);
        }
    }
}