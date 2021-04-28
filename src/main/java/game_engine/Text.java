package game_engine;

import java.awt.Font;
import java.io.File;

public class Text implements Component {

    public String textValue;
    public Font font;
    public GameObject parent; 

    public Text(GameObject parent, String textValue, int fontSize) {
        this.parent = parent;
        this.textValue = textValue;
        this.font = new Font("TimesRoman", Font.PLAIN, fontSize);
    }

    // Construct Text component with custom font file
    public Text(GameObject parent, String textValue, int fontSize, String fontPath) {
        this.parent = parent;
        this.textValue = textValue;
        try {
            Font defaultFont = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getClassLoader().getResourceAsStream(fontPath)
            );
            this.font = defaultFont.deriveFont(Font.PLAIN, fontSize);
        }
        catch (Exception e) {
            System.out.println("Unable to read font file.");
        }
    }

    public void update() {
        return;
    }
}