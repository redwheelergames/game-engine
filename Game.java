import java.io.*;
import javax.swing.*;

class Game extends JFrame{
    public static void main(String[] args) {

        Scene scene = new Scene(640, 360);
        Game game = new Game(640, 360, scene);

        GameObject background = new GameObject(320, 180, 90);
        Sprite backgroundSprite = new Sprite(scene, background, "background.png");
        background.addComponent(backgroundSprite);

        GameObject character = new GameObject(320, 180, 90);
        Sprite characterSprite = new Sprite(scene, character, "player_sprite.png");
        PlayerController playerController = new PlayerController(scene, character, 100, 100);
        TestComponent testComponent = new TestComponent(character);
        character.addComponent(characterSprite);
        character.addComponent(playerController);
        character.addComponent(testComponent);

        scene.addGameObject(background);
        scene.addGameObject(character);
        character.getComponent(Sprite.class);

        game.run();
    }

    public static final int TITLE_SIZE = 39; // Constant that accounts for the window title bar
    public Scene scene;

    public Game(int windowWidth, int windowHeight, Scene scene) {
        this.scene = scene;
        this.add(scene);
        this.setSize(windowWidth, windowHeight+TITLE_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() {
        this.setVisible(true);
        this.scene.start();
    }
}