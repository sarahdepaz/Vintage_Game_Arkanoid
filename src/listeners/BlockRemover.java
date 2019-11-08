package listeners;

import levels.GameLevel;
import other.Counter;
import elements.Ball;
import elements.Block;

/**
 * Class of a BlockRemover.
 *
 * @author sarah de paz
 */
// a BlockRemover is in charge of removing blocks from the game, as well as
// keeping count
// of the number of blocks that were removed.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;

    /**
     * constructor function that remove a block from the game.
     *
     * @param game
     *            the game we remove the block from him
     * @param removedBlocks
     *            keeps the number of blocks that were removed
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
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
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.removedBlocks.decrease(1);
        }
    }
}