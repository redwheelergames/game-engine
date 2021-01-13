package game_engine;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Sprite implements Component {
    
    public BufferedImage sprite;
    public GameObject parent;

    public Sprite(GameObject parent, String imagePath) {
        this.parent = parent;
        //attempt to load the image from a file
        try {
            this.sprite = ImageIO.read(new File(imagePath));
        } 
        catch (Exception e) {
            System.out.println("Unable to read in image file.");
        }
    }

    public void update() {
        this.parent.game.canvas.drawSprite(this.sprite, this.parent.getPosition(), this.parent.scale, this.parent.getRotation());
    }
}