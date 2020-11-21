package game_engine;

import java.io.*;
import javax.swing.*;
import java.awt.geom.*;
import java.lang.Math;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;

public class Game extends JFrame implements KeyListener, MouseMotionListener, ActionListener{

    public static final int TITLE_SIZE = 39; // Constant that accounts for the window title bar

    public int windowWidth;
    public int windowHeight;

    private Canvas canvas;
    private HashMap<String, Boolean> keyPressed;
    public Vector2D mousePosition;

    private Timer timer;
    private long lastTime;
    public double deltaTime;

    public Scene currentScene;

    public Game(int windowWidth, int windowHeight) {
        this.setSize(windowWidth, windowHeight+TITLE_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mousePosition = new Vector2D (0, 0);
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.keyPressed = new HashMap<String, Boolean>();
        this.keyPressed.put("w", false);
        this.keyPressed.put("a", false);
        this.keyPressed.put("s", false);
        this.keyPressed.put("d", false);
        this.timer = new Timer(17, this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(this);
        addMouseMotionListener(this);
        this.canvas = new Canvas();
        this.add(this.canvas);
    }

    public void setScene(Scene scene) {
        this.currentScene = scene;
    }

    public void changeScene(String transitionName) {
        this.currentScene = this.currentScene.transition(transitionName);
    }

    public void drawSprite (BufferedImage sprite, Vector2D position, Vector2D scale, int rotation) {
        int width = (int)(sprite.getWidth() * scale.x);
        int height = (int)(sprite.getHeight() * scale.y);
        int x = (int)Math.rint(position.x - width/2);
        int y = (int)Math.rint(this.windowHeight - position.y - height/2);
        // Rotate the given rotation offset by 90 (90 means no rotation)
        int translateX = (int)Math.rint(position.x);
        int translateY = (int)Math.rint(this.windowHeight - position.y);
        this.canvas.buffer.rotate(Math.toRadians(-1*(rotation-90)), translateX, translateY);
        this.canvas.buffer.drawImage(sprite, x, y, width, height, null);
        this.canvas.buffer.setTransform(this.canvas.transformDefault);
    }

    public void actionPerformed(ActionEvent e){
        Date date = new Date();
        this.deltaTime = (date.getTime() - this.lastTime) / 1000.0;
        this.lastTime = date.getTime();
        for (GameObject gameObject: this.currentScene.gameObjects) {
            gameObject.update();
        }
        this.canvas.repaint();
    } 

    public void run() {
        this.setVisible(true);
        Date date = new Date();
        this.lastTime = date.getTime();
        this.deltaTime = 0;
        this.timer.start();
    }

    public boolean getKeyPressed(String key) {
        return keyPressed.get(key);
    }

    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    public void mouseMoved(MouseEvent e) {
        this.mousePosition.x = e.getX();
        this.mousePosition.y = this.windowHeight - e.getY();
    }

    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            this.keyPressed.put("w", true);
        }
        else if(key == KeyEvent.VK_A) {
            this.keyPressed.put("a", true);
        }
        else if(key == KeyEvent.VK_S) {
            this.keyPressed.put("s", true);
        }
        else if(key == KeyEvent.VK_D) {
            this.keyPressed.put("d", true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            this.keyPressed.put("w", false);
        }
        else if(key == KeyEvent.VK_A) {
            this.keyPressed.put("a", false);
        }
        else if(key == KeyEvent.VK_S) {
            this.keyPressed.put("s", false);
        }
        else if(key == KeyEvent.VK_D) {
            this.keyPressed.put("d", false);
        }
    }

    // Double buffered drawing area
    class Canvas extends JPanel {
        private AffineTransform transformDefault;
        private Image offscreen;
        private Graphics2D buffer;

        public Canvas () {
            this.offscreen = new BufferedImage(Game.this.windowWidth, Game.this.windowHeight, BufferedImage.TYPE_INT_RGB);
            this.buffer = (Graphics2D)this.offscreen.getGraphics();
            this.transformDefault = this.buffer.getTransform();
        }

        public void paintComponent (Graphics g) {
            g.drawImage(this.offscreen, 0, 0, this);
            // Clear bufffer
            this.buffer.setColor(getBackground());
            this.buffer.fillRect(0, 0, Game.this.windowWidth, Game.this.windowHeight);
        }
    }
}