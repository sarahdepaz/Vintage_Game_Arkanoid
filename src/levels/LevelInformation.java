package levels;

import java.util.List;

import other.Velocity;
import elements.Block;
import elements.Sprite;

/**
 * Interface of a LevelInformation.
 *
 * @author sarah de paz
 */
public interface LevelInformation {
    /**
     * function that return the number of the balls.
     *
     * @return the number of the balls
     */
    int numberOfBalls();

    /**
     * function that return the initial velocity of each ball.
     *
     * @return the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * function that return the paddle speed.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * function that return the paddle width.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * function that return the level name and displayed him at the top of the
     * screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * function that return a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * function that return the Blocks that make up this level, each block
     * contains its size, color and location.
     *
     * @return a sprite with the Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * function that return the number of blocks to remove before the level is
     * considered to be "cleared"..
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}