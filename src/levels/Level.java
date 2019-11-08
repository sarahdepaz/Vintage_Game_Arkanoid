package levels;

import java.util.ArrayList;
import java.util.List;

import other.Velocity;
import elements.Block;
import elements.Sprite;

/**
 * Class of a Level.
 *
 * @author sarah de paz
 */
public class Level implements LevelInformation {
    private int numberOfBalls, paddleSpeed, paddleWidth,
            numberOfBlocksToRemove;
    private List<Velocity> velocities;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;

    /**
     * constructor function that create level.
     *
     * @param numberOfBalls
     *            number of the balls in the level
     * @param paddleSpeed
     *            the paddle speed
     * @param paddleWidth
     *            the paddle width
     * @param numberOfBlocksToRemove
     *            the number of the balls to remove
     * @param velocities
     *            the velocity
     * @param levelName
     *            the name of the level
     * @param blocks
     *            list of the blocks that in the level
     */
    public Level(int numberOfBalls, int paddleSpeed, int paddleWidth,
            int numberOfBlocksToRemove, List<Velocity> velocities,
            String levelName, List<Block> blocks) {
        this.numberOfBalls = numberOfBalls;
        this.velocities = velocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = null;
        this.blocks = blocks;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
    }

    /**
     * function that set the background of the level.
     *
     * @param bg
     *            sprite that use as a background
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }

    /**
     * function that return the number of the balls.
     *
     * @return the number of the balls
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * function that return the initial velocity of each ball.
     *
     * @return the initial velocity of each ball
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * function that return the paddle speed.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * function that return the paddle width.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * function that return the level name and displayed him at the top of the
     * screen.
     *
     * @return the level name
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * function that return a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * function that return the Blocks that make up this level, each block
     * contains its size, color and location.
     *
     * @return a sprite with the Blocks that make up this level
     */
    public List<Block> blocks() {
        List<Block> b = new ArrayList<Block>();
        for (int i = 0; i < this.blocks.size(); i++) {
            b.add(this.blocks.get(i).copy());
        }
        return b;
    }

    /**
     * function that return the number of blocks to remove before the level is
     * considered to be "cleared"..
     *
     * @return the number of blocks to remove
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

}
