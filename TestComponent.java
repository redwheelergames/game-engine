import java.io.*;

class TestComponent implements Component {

    private GameObject parent;

    public TestComponent(GameObject parent) {
        this.parent = parent;
    }

    public void update() {
        Vector2D forward = this.parent.getForwardVector();
        System.out.println(this.parent.position.x+", "+this.parent.position.y);
        //System.out.println (this.parent.rotation);
    }
}