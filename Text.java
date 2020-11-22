package game_engine;

import java.awt.Font;

public class Text implements Component {

    public String textValue;
    public Font font;
    public GameObject parent; 

    public Text(GameObject parent, String textValue, int fontSize) {
        this.parent = parent;
        this.textValue = textValue;
        this.font = new Font("TimesRoman", Font.PLAIN, fontSize);
    }

    public void update() {
        this.parent.game.canvas.drawText(this.textValue, this.font, this.parent.position);
    }
}