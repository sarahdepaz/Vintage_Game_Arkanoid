package other;

import elements.Block;

/**
 * Interface of a BlockCreator.
 *
 * @author sarah de paz
 */
public interface BlockCreator {
    /**
     * function that create a block at the specified location.
     *
     * @param ypos
     *            the y location of the block
     * @param xpos
     *            the x location of the block
     * @return a block at the specified location
     */
    Block create(int xpos, int ypos);
}