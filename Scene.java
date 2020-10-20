import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

class Scene extends JPanel implements KeyListener, MouseMotionListener, ActionListener{
    
    private HashMap<String, Boolean> keyPressed;
    private Timer timer;
    private long lastTime;
    public Graphics buffer;
    private Image offscreen;
    public double deltaTime;
    public ArrayList<GameObject> gameObjects; 
    public int mousePosX;
    public int mousePosY;
    public int windowSizeX;
    public int windowSizeY;

    public Scene(int windowSizeX, int windowSizeY) {
        this.mousePosX = 0;
        this.mousePosY = 0;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
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
        this.offscreen = createImage(this.windowSizeX, this.windowSizeY);
        this.buffer = this.offscreen.getGraphics();
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
        this.buffer.fillRect(0, 0, this.windowSizeX, this.windowSizeY);
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
        this.mousePosX = e.getX();
        this.mousePosY = e.getY();
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
