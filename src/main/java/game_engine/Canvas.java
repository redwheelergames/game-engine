package game_engine;

import javax.swing.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontMetrics;

// Double buffered drawing area to be used internally
class Canvas extends JPanel {
    private int height;
    private int width;
    private AffineTransform transformDefault;
    private Image offscreen;
    private Graphics2D buffer;

    public Canvas(int windowWidth, int windowHeight) {
        this.width = windowWidth;
        this.height = windowHeight;
        this.offscreen = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.buffer = (Graphics2D)this.offscreen.getGraphics();
        this.transformDefault = this.buffer.getTransform();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(this.offscreen, 0, 0, this);
        // Clear buffer
        this.buffer.setColor(getBackground());
        this.buffer.fillRect(0, 0, this.width, this.height);
    }

    // Draw Text centered at position
    public void drawText(String text, Font font, Vector2D position) {
        FontMetrics metrics = this.buffer.getFontMetrics(font);
        int x = (int)Math.rint(position.x - metrics.stringWidth(text)/2);
        int y = (int)Math.rint(this.height - position.y + metrics.getHeight()/2);
        this.buffer.setFont(font);
        this.buffer.drawString(text, x, y);
    }

    public void drawSprite(BufferedImage sprite, Vector2D position, Vector2D scale, int rotation) {
        int width = (int)(sprite.getWidth() * scale.x);
        int height = (int)(sprite.getHeight() * scale.y);
        int x = (int)Math.rint(position.x - width/2);
        int y = (int)Math.rint(this.height - position.y - height/2);
        // Rotate the given rotation offset by 90 (90 means no rotation)
        int translateX = (int)Math.rint(position.x);
        int translateY = (int)Math.rint(this.height - position.y);
        this.buffer.rotate(Math.toRadians(-1*(rotation-90)), translateX, translateY);
        this.buffer.drawImage(sprite, x, y, width, height, null);
        this.buffer.setTransform(this.transformDefault);
    }
}
