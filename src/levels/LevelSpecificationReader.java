package levels;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import other.Velocity;
import animation.Background;
import elements.Block;

/**
 * Class of an LevelSpecificationReader.
 *
 * @author sarah de paz
 */
public class LevelSpecificationReader {
    private boolean inLevel, inBlocks;
    private Map<String, String> info;
    private List<Block> blocks;
    private int blockRow;

    /**
     * constructor function that create the level by reading a file (get a file
     * name and returns a list of LevelInformation objects).
     *
     * @param reader
     *            the file that contain the information of the level
     * @return a List of LevelInformation with the level to run
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        this.inLevel = false;
        this.inBlocks = false;
        this.info = new TreeMap<String, String>();
        this.blocks = new ArrayList<Block>();
        this.blockRow = 0;
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        BufferedReader buffer = new BufferedReader(reader);
        try {
            String newLine = buffer.readLine();
            while (newLine != null) {
                LevelInformation newLevel = this.initializeLevel(newLine);
                if (newLevel != null) {
                    levels.add(newLevel);
                    this.resetLevel();
                }
                newLine = buffer.readLine();
            }
        } catch (Exception e) {
            System.out.println("file read error");
        }
        try {
            buffer.close();
        } catch (Exception e) {
            System.out.print("");
        }
        return levels;
    }

    /**
     * function that reset the level.
     *
     */
    public void resetLevel() {
        this.blockRow = 0;
        this.blocks.clear();
        this.info.clear();
    }

    /**
     * function that initialize the level.
     *
     * @param newLine
     *            a line from the file
     * @return the level
     */
    public LevelInformation initializeLevel(String newLine) {
        if ((newLine.length() > 0) && (!newLine.startsWith("#"))) {
            if (newLine.equals("START_LEVEL")) {
                // new level start
                this.inLevel = true;
            } else if (newLine.equals("END_LEVEL")) {
                // the end of this level
                this.inLevel = false;
                // create the new level to return
                return (this.createLevel());
            } else if (inLevel) {
                if (newLine.equals("START_BLOCKS")) {
                    // blocks define start
                    this.inBlocks = true;
                } else if (newLine.equals("END_BLOCKS")) {
                    // blocks define end
                    this.inBlocks = false;
                } else if (inBlocks) {
                    initializeBlock(newLine);
                } else {
                    // add to level info
                    String[] splitString = newLine.split(":");
                    this.info.put(splitString[0], splitString[1]);
                }
            }
        }
        return null;
    }

    /**
     * function that initialize the block.
     *
     * @param newLine
     *            a line from the file
     */
    public void initializeBlock(String newLine) {
        // set position
        int rowHeight = Integer.parseInt(this.info.get("row_height"));
        int x = Integer.parseInt(this.info.get("blocks_start_x"));
        int y = Integer.parseInt(this.info.get("blocks_start_y"))
                + (rowHeight * this.blockRow);
        this.blockRow++;
        BlocksFromSymbolsFactory bfsf = BlocksDefinitionReader
                .fromReader(new InputStreamReader(ClassLoader
                        .getSystemClassLoader().getResourceAsStream(
                                this.info.get("block_definitions"))));
        // add blocks
        for (int i = 0; i < newLine.length(); i++) {
            String s = String.valueOf(newLine.charAt(i));
            if (bfsf.isBlockSymbol(s)) {
                Block block = bfsf.getBlock(s, x, y);
                this.blocks.add(block);
                x += block.getCollisionRectangle().getWidth();
            } else if (bfsf.isSpaceSymbol(s)) {
                x += bfsf.getSpaceWidth(s);
            }
        }
    }

    /**
     * function that create the level.
     *
     * @return the level
     */
    public LevelInformation createLevel() {
        // level name
        String levelName = this.info.get("level_name");
        // balls
        String[] d, v = this.info.get("ball_velocities").split(" ");
        int numberOfBalls = v.length;
        // velocities
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls; i++) {
            d = v[i].split(",");
            velocities.add(Velocity.fromAngleAndSpeed(Integer.parseInt(d[0]),
                    Integer.parseInt(d[1])));
        }
        // paddle
        int paddleSpeed = Integer.parseInt(this.info.get("paddle_speed"));
        int paddleWidth = Integer.parseInt(this.info.get("paddle_width"));

        // blocks
        int numberOfBlocksToRemove = Integer.parseInt(this.info
                .get("num_blocks"));

        // background
        Background bg = null;
        String propertiesBG = this.info.get("background");
        if (propertiesBG.startsWith("color")) {
            bg = new Background(Block.getColorFromString(propertiesBG));
        } else if (propertiesBG.startsWith("image")) {
            bg = new Background(Block.getImageFromString(propertiesBG));
        }

        // create new blocks list
        List<Block> newBlocks = new ArrayList<Block>();
        newBlocks.addAll(this.blocks);

        // create the new level
        if ((bg != null) && (this.blocks.size() > 0) && (velocities.size() > 0)) {
            Level newLevel = new Level(numberOfBalls, paddleSpeed, paddleWidth,
                    numberOfBlocksToRemove, velocities, levelName, newBlocks);
            newLevel.setBackground(bg);
            return newLevel;
        } else {
            System.out.println("level create error");
        }
        return null;

    }
}