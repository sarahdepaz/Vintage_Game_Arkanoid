package listeners;

import elements.Ball;
import elements.Block;

/**
 * Interface of a HitListener.
 *
 * @author sarah de paz
 */
public interface HitListener {
    /**
     * function that called whenever the beingHit object is hit the hitter
     * parameter is the Ball that's doing the hitting.
     *
     * @param beingHit
     *            the beingHit object
     * @param hitter
     *            the hitter parameter
     */
    void hitEvent(Block beingHit, Ball hitter);
}