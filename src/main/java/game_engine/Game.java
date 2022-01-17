package game_engine;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.util.HashMap;
import java.awt.Dimension;
import java.awt.Point;

public class Game extends JFrame implements KeyListener, MouseListener, MouseMotionListener, ActionListener{

    public int windowWidth;
    public int windowHeight;
    private Canvas canvas;
    public KeyMap wasPressed;
    public KeyMap wasReleased;
    public Vector2D mousePosition;

    private Timer timer;
    private long lastTime;
    public double deltaTime;

    public SceneManager sceneManager;
    public RenderingSystem renderingSystem;
    public PhysicsSystem physicsSystem;

    public Game(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.getContentPane().setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.sceneManager = new SceneManager();
        this.renderingSystem = new RenderingSystem(this);
        this.physicsSystem = new PhysicsSystem(this);
        this.canvas = renderingSystem.canvas;
        this.add(renderingSystem.canvas);

        this.mousePosition = new Vector2D (0, 0);
        this.wasPressed = new KeyMap();
        this.wasReleased = new KeyMap();

        this.timer = new Timer(1, this);
    }

    public void actionPerformed(ActionEvent e) {
        Date date = new Date();
        this.deltaTime = (date.getTime() - this.lastTime) / 1000.0;
        this.lastTime = date.getTime();
        this.sceneManager.updateGameObjects(this);
        this.physicsSystem.stepPhysics();
        this.renderingSystem.render();
        this.wasReleased.reset();
    } 

    public void run() {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
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
        // Get the mouse position relative to the canvas
        Point mousePosition = this.canvas.getMousePosition();
        this.mousePosition.x = mousePosition.x;
        this.mousePosition.y = this.windowHeight - mousePosition.y;
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