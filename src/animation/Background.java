package animation;

import java.awt.Color;
import java.awt.Image;

import levels.GameLevel;
import biuoop.DrawSurface;
import elements.Sprite;

/**
 * Class of a Background.
 *
 * @author sarah de paz
 */
public class Background implements Sprite {
    private Image image;
    private Color color;

    /**
     * constructor function that create the background by color.
     *
     * @param color
     *            the color of the background
     */
    public Background(Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * constructor function that create the background cy image.
     *
     * @param image
     *            the image background
     */
    public Background(Image image) {
        this.color = null;
        this.image = image;
    }

    /**
     * function that draw the sprite to the screen.
     *
     * @param d
     *            a DrawSurface to draw on it
     */
    public void drawOn(DrawSurface d) {
        if (color != null) {
            d.setColor(color);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        } else if (image != null) {
            d.drawImage(0, 0, image);
        }
    }

    /**
     * function that notify the sprite that time has passed.
     *
     * @param dt
     *            frames per seconds
     */
    public void timePassed(double dt) {
    }

    /**
     * function that add the sprite to the game.
     *
     * @param g
     *            a game to add the sprite to it
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
