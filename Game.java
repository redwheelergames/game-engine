import java.io.*;
import javax.swing.*;
import java.util.Random;

class Game extends JFrame{

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