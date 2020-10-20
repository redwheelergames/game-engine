import java.util.ArrayList;

class GameObject {
    public int x;
    public int y;
    private int rotation;
    private Scene scene;
    //private List<GameObject> children;
    private ArrayList<Component> components;

    public GameObject(int x, int y, int rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        //children = new ArrayList<GameObject>();
        components = new ArrayList<Component>();
    }
    
    public void addComponent(Component component) {
        this.components.add(component);
    }

    /*
    public Component getComponent(String componentType) {

    }*/

    // 
    public void update() {
        for (Component component : components) {
            component.update();
        }
        /*
        for (GameObject gameObject: children) {
            gameObject.update();
        }*/
    }
}