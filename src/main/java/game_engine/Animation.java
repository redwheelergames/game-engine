package game_engine;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;

public class Animation implements Component {

    public GameObject parent;

    public ArrayList<BufferedImage> frames;
    public int frameCount;  // Number of frames
    public int frameIndex;  // Index of current frame
    public int frameLength; // Amount of frames each sprite should be played for

    public boolean repeat;
    public boolean finished;
    private int lastChange; // How many frames have passed since last sprite change

    public Animation (GameObject parent, List<String> imagePaths, int frameLength, boolean repeat) {
        this.parent = parent;
        this.frameLength = frameLength;
        this.repeat = repeat;
        this.frames = new ArrayList<BufferedImage> ();
        // attempt to read all image paths 
        for (String imagePath: imagePaths) {
            try {
                var frame = getClass().getClassLoader().getResourceAsStream(imagePath);
                frames.add(ImageIO.read(frame));
            } 
            catch (Exception e) {
                System.out.println("Unable to read in image file: " + imagePath);
            }
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
                if (this.frameIndex == this.frames.size()-1) {
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
            this.parent.game.canvas.drawSprite(this.frames.get(this.frameIndex), this.parent.position, this.parent.scale, this.parent.rotation);
        }
    }
}