package listeners;

import other.Counter;
import elements.Ball;
import elements.Block;

/**
 * Class of a ScoreTrackingListener.
 *
 * @author sarah de paz
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor function that update this counter when blocks are being hit
     * and removed..
     *
     * @param scoreCounter
     *            the updated score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the
     * game.
     *
     * @param beingHit
     *            the being hit object (blocks)
     * @param hitter
     *            the hitter object (ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(15);
        } else {
            this.currentScore.increase(5);
        }
    }
}