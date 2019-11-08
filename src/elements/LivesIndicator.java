package elements;

import java.awt.Color;

import levels.GameLevel;
import other.Counter;
import biuoop.DrawSurface;

/**
 * Class of a LivesIndicator.
 *
 * @author sarah de paz
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;

    /**
     * constructor function that create the lives indicator.
     *
     * @param lives
     *            the counter lives of the game
     */
    public LivesIndicator(Counter lives) {
        this.livesCounter = lives;
    }

    /**
     * function that draw the sprite to the screen.
     *
     * @param surface
     *            a DrawSurface to draw on it
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText(60, 16,
                "Lives: " + Integer.toString(this.livesCounter.getValue()), 13);
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
