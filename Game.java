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
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Date;
import java.util.HashMap;

public class Game extends JFrame implements KeyListener, MouseListener, MouseMotionListener, ActionListener{

    public static final int TITLE_SIZE = 39; // Constant that accounts for the window title bar

    public int windowWidth;
    public int windowHeight;

    public Canvas canvas;
    public KeyMap wasPressed;
    public KeyMap wasReleased;
    public Vector2D mousePosition;

    private Timer timer;
    private long lastTime;
    public double deltaTime;

    public Scene currentScene;

    public Game(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.setSize(this.windowWidth, this.windowHeight+TITLE_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.canvas = new Canvas();
        this.add(this.canvas);
        
        this.mousePosition = new Vector2D (0, 0);
        this.wasPressed = new KeyMap();
        this.wasReleased = new KeyMap();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

        this.timer = new Timer(1, this); 
    }

    public void setScene(Scene scene) {
        this.currentScene = scene;
        this.currentScene.load(this);
    }

    public void changeScene(String transitionName) {
        this.currentScene = this.currentScene.transition(transitionName);
        this.currentScene.load(this);
    }

    public void actionPerformed(ActionEvent e){
        Date date = new Date();
        this.deltaTime = (date.getTime() - this.lastTime) / 1000.0;
        this.lastTime = date.getTime();
        for (GameObject gameObject: this.currentScene.gameObjects) {
            if (gameObject.active) {
               gameObject.update(); 
            }
        }
        this.wasReleased.reset();
        this.canvas.repaint();
    } 

    public void run() {
        this.setVisible(true);
        Date date = new Date();
        this.lastTime = date.getTime();
        this.deltaTime = 0;
        this.timer.start();
    }

    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    public void mouseMoved(MouseEvent e) {
        this.mousePosition.x = e.getX();
        this.mousePosition.y = this.windowHeight - e.getY();
    }

    public void mouseClicked(MouseEvent e) {
        // do nothing
    }

    public void mouseEntered (MouseEvent e) {
        // do nothing
    }

    public void mouseExited (MouseEvent e) {
        // do nothing
    }

    public void mousePressed (MouseEvent e) {
        this.wasPressed.setKey(e.getButton(), true);
    }

    public void mouseReleased (MouseEvent e) {
        this.wasPressed.setKey(e.getButton(), false);
        this.wasReleased.setKey(e.getButton(), true);
    }

    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    public void keyPressed(KeyEvent e) {
        this.wasPressed.setKey(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        this.wasPressed.setKey(e.getKeyCode(), false);
        this.wasReleased.setKey(e.getKeyCode(), true);
    }

    // Double buffered drawing area
    class Canvas extends JPanel {
        private AffineTransform transformDefault;
        private Image offscreen;
        private Graphics2D buffer;

        public Canvas() {
            this.offscreen = new BufferedImage(Game.this.windowWidth, Game.this.windowHeight, BufferedImage.TYPE_INT_RGB);
            this.buffer = (Graphics2D)this.offscreen.getGraphics();
            this.transformDefault = this.buffer.getTransform();
        }

        public void paintComponent(Graphics g) {
            g.drawImage(this.offscreen, 0, 0, this);
            // Clear bufffer
            this.buffer.setColor(getBackground());
            this.buffer.fillRect(0, 0, Game.this.windowWidth, Game.this.windowHeight);
        }

        // Draw Text centered at position
        public void drawText(String text, Font font, Vector2D position) {
            FontMetrics metrics = this.buffer.getFontMetrics(font);
            int x = (int)Math.rint(position.x - metrics.stringWidth(text)/2);
            int y = (int)Math.rint(Game.this.windowHeight - position.y - metrics.getHeight()/2);
            this.buffer.setFont(font);
            this.buffer.drawString(text, x, y);
        }

        public void drawSprite(BufferedImage sprite, Vector2D position, Vector2D scale, int rotation) {
            int width = (int)(sprite.getWidth() * scale.x);
            int height = (int)(sprite.getHeight() * scale.y);
            int x = (int)Math.rint(position.x - width/2);
            int y = (int)Math.rint(Game.this.windowHeight - position.y - height/2);
            // Rotate the given rotation offset by 90 (90 means no rotation)
            int translateX = (int)Math.rint(position.x);
            int translateY = (int)Math.rint(Game.this.windowHeight - position.y);
            this.buffer.rotate(Math.toRadians(-1*(rotation-90)), translateX, translateY);
            this.buffer.drawImage(sprite, x, y, width, height, null);
            this.buffer.setTransform(this.transformDefault);
        }
    }

    static public class KeyMap {

        private HashMap<Integer, Boolean> keyMap;
        static final HashMap<String, Integer> aliases;
        
        static {
            aliases = new HashMap<String, Integer> ();
            aliases.put("w", KeyEvent.VK_W);
            aliases.put("a", KeyEvent.VK_A);
            aliases.put("s", KeyEvent.VK_S);
            aliases.put("d", KeyEvent.VK_D);
            aliases.put("q", KeyEvent.VK_Q);
            aliases.put("e", KeyEvent.VK_E);
            aliases.put("space", KeyEvent.VK_SPACE);
            aliases.put("mouse1", MouseEvent.BUTTON1);
            aliases.put("mouse2", MouseEvent.BUTTON2);
            aliases.put("mouse3", MouseEvent.BUTTON3);
        }

        public KeyMap() {
            this.keyMap = new HashMap<Integer, Boolean> ();
            this.keyMap.put(KeyEvent.VK_W, false);
            this.keyMap.put(KeyEvent.VK_A, false);
            this.keyMap.put(KeyEvent.VK_S, false);
            this.keyMap.put(KeyEvent.VK_D, false);
            this.keyMap.put(KeyEvent.VK_Q, false);
            this.keyMap.put(KeyEvent.VK_E, false);
            this.keyMap.put(KeyEvent.VK_SPACE, false);
            this.keyMap.put(MouseEvent.BUTTON1, false);
            this.keyMap.put(MouseEvent.BUTTON2, false);
            this.keyMap.put(MouseEvent.BUTTON3, false);
        }

        // Set all values in keyMap to false
        public void reset() {
            for (HashMap.Entry<Integer, Boolean> entry: this.keyMap.entrySet()) {
                this.keyMap.put(entry.getKey(), false);
            }
        }

        public void setKey(int keyCode, boolean value) {
            this.keyMap.put(keyCode, value);
        }

        public void setKey(String keyAlias, boolean value) {
            int keyCode = aliases.get(keyAlias); // This isn't safe
            setKey(keyCode, value);
        }

        public boolean getKey(int keyCode) {
            return this.keyMap.get(keyCode);
        }

        public boolean getKey(String keyAlias) {
            int keyCode = aliases.get(keyAlias);
            return getKey(keyCode);
        }
    }
}