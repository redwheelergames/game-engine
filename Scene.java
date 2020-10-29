import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

class Scene extends JPanel implements KeyListener, MouseMotionListener, ActionListener{
    
    private HashMap<String, Boolean> keyPressed;
    private Timer timer;
    private long lastTime;
    public Graphics2D buffer;
    private AffineTransform transformDefault;
    private Image offscreen;
    public double deltaTime;
    public ArrayList<GameObject> gameObjects; 
    public Vector2D mousePosition;
    public int windowWidth;
    public int windowHeight;

    public Scene(int windowWidth, int windowHeight) {
        this.mousePosition = new Vector2D (0, 0);
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameObjects = new ArrayList<GameObject>();
        this.keyPressed = new HashMap<String, Boolean>();
        this.timer = new Timer(17, this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(this);
        addMouseMotionListener(this);
    }

    public void start() {
        this.keyPressed.put("w", false);
        this.keyPressed.put("a", false);
        this.keyPressed.put("s", false);
        this.keyPressed.put("d", false);
        Date date = new Date();
        this.lastTime = date.getTime();
        this.deltaTime = 0;
        this.offscreen = createImage(this.windowWidth, this.windowHeight);
        this.buffer = (Graphics2D)this.offscreen.getGraphics();
        this.transformDefault = this.buffer.getTransform();
        this.timer.start();
    }

    public void actionPerformed(ActionEvent e){
        Date date = new Date();
        this.deltaTime = (date.getTime() - this.lastTime) / 1000.0;
        this.lastTime = date.getTime();
        for (GameObject gameObject: gameObjects) {
            gameObject.update();
        }
        this.repaint();
    } 

    public void paintComponent (Graphics g) {
        g.drawImage(this.offscreen, 0, 0, this);
        // Clear bufffer
        this.buffer.setColor(getBackground());
        this.buffer.fillRect(0, 0, this.windowWidth, this.windowHeight);
    }

    public void drawSprite (BufferedImage sprite, Vector2D position, int rotation) {
        int x = (int)Math.rint(position.x - sprite.getWidth()/2);
        int y = (int)Math.rint(this.windowHeight - position.y - sprite.getHeight()/2);
        // Rotate the given rotation offset by 90 (90 means no rotation)
        this.buffer.rotate(Math.toRadians(-1*(rotation-90)), x, y);
        this.buffer.drawImage(sprite, x, y, sprite.getWidth(), sprite.getHeight(), null);
        this.buffer.setTransform(this.transformDefault);
    }

    public boolean getKeyPressed(String key) {
        return keyPressed.get(key);
    }

    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
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

}
