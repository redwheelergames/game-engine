package game_engine;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Sprite implements Component {

    public final static BufferedImage DEFAULT_SPRITE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    public BufferedImage sprite;
    public GameObject parent;

    public Sprite(GameObject parent) {
        this.parent = parent;
        this.sprite = this.DEFAULT_SPRITE;
    }

    public Sprite(GameObject parent, String imagePath) {
        this.parent = parent;
        //attempt to load the image from a file
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath));
        } 
        catch (Exception e) {
            System.out.println("Unable to read in image file.");
        }
    }

    public void update() {
        return;
    }
}