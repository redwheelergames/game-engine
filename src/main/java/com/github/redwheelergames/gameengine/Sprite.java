package com.github.redwheelergames.gameengine;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Sprite extends Component {

    public final static BufferedImage DEFAULT_SPRITE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    public BufferedImage sprite;

    public Sprite(GameObject gameObject) {
        super(gameObject);
        this.sprite = Sprite.DEFAULT_SPRITE;
    }

    public Sprite(GameObject gameObject, String imagePath) {
        super(gameObject);
        //attempt to load the image from a file
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath));
        } 
        catch (Exception e) {
            System.out.println("Unable to read in image file.");
        }
    }
}