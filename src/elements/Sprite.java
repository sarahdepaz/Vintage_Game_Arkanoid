package elements;

import levels.GameLevel;
import biuoop.DrawSurface;

/**
 * Interface of a Sprite.
 *
 * @author sarah de paz
 */
public interface Sprite {
    /**
     * function that draw the sprite to the screen.
     *
     * @param d
     *            a DrawSurface to draw on it
     */
    void drawOn(DrawSurface d);

    /**
     * function that notify the sprite that time has passed.
     *
     * @param dt
     *            frames per second
     */
    void timePassed(double dt);

    /**
     * function that add the sprite to the game.
     *
     * @param g
     *            a game to add the sprite to it
     */
    void addToGame(GameLevel g);
}