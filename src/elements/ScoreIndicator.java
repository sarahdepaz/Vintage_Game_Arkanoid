package elements;

import geometry.Rectangle;

import java.awt.Color;

import levels.GameLevel;
import other.Counter;
import biuoop.DrawSurface;

/**
 * Class of a ScoreIndicator.
 *
 * @author sarah de paz
 */
public class ScoreIndicator implements Sprite {
    private Rectangle scoreRectangle;
    private Color color;
    private Counter scoreCounter;

    /**
     * constructor function that create the score indicator.
     *
     * @param rec
     *            the block that the score will appear on him
     * @param color
     *            the color of the block
     * @param score
     *            the counter score of the game
     */
    public ScoreIndicator(Rectangle rec, Color color, Counter score) {
        this.scoreRectangle = rec;
        this.color = color;
        this.scoreCounter = score;
    }

    /**
     * function that draw the sprite to the screen.
     *
     * @param surface
     *            a DrawSurface to draw on it
     */
    public void drawOn(DrawSurface surface) {
        int x, y, width, height;
        x = (int) this.scoreRectangle.getUpperLeft().getX();
        y = (int) this.scoreRectangle.getUpperLeft().getY();
        width = (int) this.scoreRectangle.getWidth();
        height = (int) this.scoreRectangle.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawText(330, 16,
                "Score: " + Integer.toString(this.scoreCounter.getValue()), 13);
    }

    /**
     * function that notify the sprites that time has passed.
     *
     * @param dt
     *            frames per second
     */
    public void timePassed(double dt) {

    }

    /**
     * function that add a sprite to the game.
     *
     * @param g
     *            a sprite to add to the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}