package listeners;

import levels.GameLevel;
import other.Counter;
import elements.Ball;
import elements.Block;

/**
 * Class of a BallRemover.
 *
 * @author sarah de paz
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBall;

    /**
     * constructor function that remove a ball from the game.
     *
     * @param game
     *            the game we remove the block from him
     * @param removedBall
     *            keeps the number of balls that were removed
     */
    public BallRemover(GameLevel game, Counter removedBall) {
        this.game = game;
        this.removedBall = removedBall;
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
        hitter.removeFromGame(this.game);
        this.removedBall.decrease(1);
    }
}