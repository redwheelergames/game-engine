import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;

class SpriteComponent implements Component {
    
    BufferedImage sprite;
    GameObject parent;
    Scene scene;

    public SpriteComponent(Scene scene, GameObject parent, String imagePath) {
        this.scene = scene;
        this.parent = parent;

        //attempt to load the image from a file
        try {
            this.sprite = ImageIO.read(new File(imagePath));
        } catch (Exception e){
            System.out.println("Unable to read in image file.");
        }
    }

    private void draw() {
        Graphics g = this.scene.buffer;
        g.drawImage(this.sprite, this.parent.x, this.parent.y, this.sprite.getWidth(), this.sprite.getHeight(), null);
    }

    public void update() {
        draw();
    }
}