package levels;

import java.util.Map;
import java.util.TreeMap;

import other.BlockCreator;
import elements.Block;

/**
 * Class of an BlocksFromSymbolsFactory.
 *
 * @author sarah de paz
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor function that create block to any definition in the block
     * definition.
     *
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<String, Integer>();
        this.blockCreators = new TreeMap<String, BlockCreator>();
    }

    /**
     * function that returns true if 's' is a valid space symbol.
     *
     * @param s
     *            a space symbol
     * @return true if 's' is a valid space symbol
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * function that returns true if 's' is a valid block symbol.
     *
     * @param s
     *            a space symbol
     * @return true if 's' is a valid block symbol
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * function that return a block according to the definitions associated with
     * symbol s The block will be located at position (xpos, ypos).
     *
     * @param s
     *            a space symbol
     * @param x
     *            the x location of the block
     * @param y
     *            the y location of the block
     * @return a block according to the definitions associated with symbol s
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * function that return the width in pixels associated with the given
     * spacer-symbol.
     *
     * @param s
     *            a space symbol
     * @return the width in pixels associated with the given spacer-symbol
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * function that add block creators.
     *
     * @param key
     *            associated with the block
     * @param blockCreator
     *            block in a specify location
     */
    public void addBlockCreators(String key, BlockCreator blockCreator) {
        this.blockCreators.put(key, blockCreator);
    }

    /**
     * function that add spacer widths.
     *
     * @param key
     *            key from the user
     * @param spaceWidth
     *            the width of the space in the screen
     */
    public void addspacerWidths(String key, int spaceWidth) {
        this.spacerWidths.put(key, spaceWidth);
    }

}