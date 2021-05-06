package game_engine;

import java.awt.Font;
import java.io.File;

public class Text extends Component {

    public String textValue;
    public Font font;

    public Text(GameObject gameObject, String textValue, int fontSize) {
        super(gameObject);
        this.textValue = textValue;
        this.font = new Font("TimesRoman", Font.PLAIN, fontSize);
    }

    // Construct Text component with custom font file
    public Text(GameObject gameObject, String textValue, int fontSize, String fontPath) {
        super(gameObject);
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

}