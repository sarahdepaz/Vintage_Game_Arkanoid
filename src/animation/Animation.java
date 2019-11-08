package animation;

import biuoop.DrawSurface;

/**
 * Interface of a Animation.
 *
 * @author sarah de paz
 */
public interface Animation {
    /**
     * function that charge of the logic.
     *
     * @param d
     *            the sprites to draw to the screen
     * @param dt
     *            frames per second
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * function that charge of stopping condition.
     *
     * @return if the game should stop
     */
    boolean shouldStop();
}