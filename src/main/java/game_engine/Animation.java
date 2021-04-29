package game_engine;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;

public class Animation implements Component {

    public GameObject parent;
    public Sprite spriteComponent; // Sprite component that will draw animation frames
    private ArrayList<BufferedImage> frames;
    private int frameCount;  // Number of frames
    private int frameIndex;  // Index of current frame
    private int frameLength; // Amount of frames each sprite should be played for

    private boolean repeat;
    private boolean finished;
    private int lastChange; // How many frames have passed since last sprite change

    public Animation (GameObject parent, List<String> imagePaths, int frameLength, boolean repeat) {
        this.parent = parent;
        // Create a new sprite component that frames will be drawn from
        this.spriteComponent = new Sprite (this.parent);
        this.parent.addComponent(this.spriteComponent);
        this.frameLength = frameLength;
        this.repeat = repeat;
        this.frames = new ArrayList<BufferedImage> ();
        // Attempt to read all image paths
        for (String imagePath: imagePaths) {
            try {
                var frame = getClass().getClassLoader().getResourceAsStream(imagePath);
                frames.add(ImageIO.read(frame));
            } 
            catch (Exception e) {
                System.out.println("Unable to read in image file: " + imagePath);
            }
        }
        if (this.repeat) {

        }
        this.frameIndex = 0;
        this.frameCount = this.frames.size();
    }

    public void update() {
        if (!this.finished) {
            this.lastChange++;
            if (this.lastChange == this.frameLength) {
                this.lastChange = 0;
                // If the last frame is reached
                if (this.frameIndex == this.frames.size() - 1) {
                    if (this.repeat) {
                        this.frameIndex = 0;
                    }
                    else {
                        this.finished = true;
                    }
                }
                else {
                    this.frameIndex++;
                }
            }
            if (this.finished) {
                // Empty image - display nothing
                this.spriteComponent.sprite = Sprite.DEFAULT_SPRITE;
            }
            else {
                this.spriteComponent.sprite = this.frames.get(this.frameIndex);
            }
        }
    }
}