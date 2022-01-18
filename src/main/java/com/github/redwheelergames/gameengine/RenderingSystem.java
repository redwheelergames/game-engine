package com.github.redwheelergames.gameengine;

import java.util.ArrayList;

public class RenderingSystem {

    public Canvas canvas;
    private Game game;

    public RenderingSystem (Game game) {
        this.game = game;
        this.canvas = new Canvas(game.windowWidth, game.windowHeight);
    }

    private void renderText () {
        ArrayList<Text> textComponents = this.game.sceneManager.getComponents(Text.class);
        for (Text textComponent: textComponents) {
            if (textComponent.gameObject.active) {
                this.canvas.drawText(
                                    textComponent.textValue,
                                    textComponent.font,
                                    textComponent.gameObject.transform.position);    
            }
        }
    }

    private void renderSprites () {
        ArrayList<Sprite> spriteComponents = this.game.sceneManager.getComponents(Sprite.class);
        for (Sprite spriteComponent: spriteComponents) {
            if (spriteComponent.gameObject.active) {
                this.canvas.drawSprite(
                        spriteComponent.sprite,
                        spriteComponent.gameObject.transform.position,
                        spriteComponent.gameObject.transform.scale,
                        spriteComponent.gameObject.transform.rotation);
            }
        }
    }

    public void render () {
        this.renderSprites();
        this.renderText();
        this.canvas.repaint();
    }
}
