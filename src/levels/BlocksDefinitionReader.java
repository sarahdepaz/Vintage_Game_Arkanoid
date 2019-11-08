package levels;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.Map;
import java.util.TreeMap;

import other.BlockCreator;
import elements.Block;

/**
 * Class of an BlocksDefinitionReader.
 *
 * @author sarah de paz
 */
public class BlocksDefinitionReader {
    /**
     * function that charge of reading a block-definitions file and returning a
     * BlocksFromSymbolsFactory object.
     *
     * @param reader
     *            the file we read from
     * @return a BlocksFromSymbolsFactory object
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory bfsf = new BlocksFromSymbolsFactory();
        BufferedReader buffer = new BufferedReader(reader);
        Map<String, String> defaultParams = new TreeMap<String, String>();
        try {
            String newLine = buffer.readLine();
            while (newLine != null) {
                if ((newLine.length() > 0) && (!newLine.startsWith("#"))) {
                    if (newLine.startsWith("default")) {
                        String[] valueDefault = newLine.split(" ");
                        for (int i = 1; i < valueDefault.length; i++) {
                            String[] param = valueDefault[i].split(":");
                            defaultParams.put(param[0], param[1]);
                        }
                    } else if (newLine.startsWith("bdef")) {
                        addBlock(bfsf, defaultParams, newLine);
                    } else if (newLine.startsWith("sdef")) {
                        addSpacer(bfsf, defaultParams, newLine);
                    } else {
                        System.out.println("blocks file format error");
                    }
                }
                newLine = buffer.readLine();
            }
            return bfsf;
        } catch (Exception e) {
            System.out.println("blocks file read error");
            return null;
        } finally {
            try {
                buffer.close();
            } catch (Exception e) {
                System.out.print("");
            }
        }
    }

    /**
     * function that add a spacer.
     *
     * @param bfsf
     *            the block definition
     * @param defaultParams
     *            the default spacer
     * @param newLine
     *            the new spacer
     */
    private static void addSpacer(BlocksFromSymbolsFactory bfsf,
            Map<String, String> defaultParams, String newLine) {
        String symbol = null;
        int width = -1;
        if (defaultParams.containsKey("Symbol")) {
            symbol = defaultParams.get("Symbol");
        }
        if (defaultParams.containsKey("width")) {
            width = Integer.parseInt(defaultParams.get("width"));
        }
        String[] valueSpacer = newLine.split(" ");
        for (int i = 1; i < valueSpacer.length; i++) {
            String[] param = valueSpacer[i].split(":");
            if (param[0].equals("symbol")) {
                symbol = param[1];
            } else if (param[0].equals("width")) {
                width = Integer.parseInt(param[1]);
            }
        }
        if ((symbol != null) && (width != (-1))) {
            bfsf.addspacerWidths(symbol, width);
        } else {
            System.out.println("blocks file format error");
        }
    }

    /**
     * function that add a block.
     *
     * @param bfsf
     *            the block definition
     * @param defaultParams
     *            the default spacer
     * @param newLine
     *            the new spacer
     */
    private static void addBlock(BlocksFromSymbolsFactory bfsf,
            Map<String, String> defaultParams, String newLine) {
        int width = -1, height = -1, hitPoints = 1;
        Color stroke = null;
        String symbol = null;
        String[] fillParams = new String[1];
        fillParams[0] = null;
        if (defaultParams.containsKey("Symbol")) {
            symbol = defaultParams.get("Symbol");
        }
        if (defaultParams.containsKey("width")) {
            width = Integer.parseInt(defaultParams.get("width"));
        }
        if (defaultParams.containsKey("height")) {
            height = Integer.parseInt(defaultParams.get("height"));
        }
        if (defaultParams.containsKey("hit_points")) {
            hitPoints = Integer.parseInt(defaultParams.get("hit_points"));
            if (hitPoints > 1) {
                fillParams = new String[hitPoints];
            }
        }
        if (defaultParams.containsKey("stroke")) {
            stroke = Block.getColorFromString(defaultParams.get("stroke"));
        }
        if (defaultParams.containsKey("fill")) {
            fillParams[0] = defaultParams.get("fill");
        }
        String[] valueSpacer = newLine.split(" ");
        for (int i = 1; i < valueSpacer.length; i++) {
            String[] param = valueSpacer[i].split(":");
            if (param[0].equals("symbol")) {
                symbol = param[1];
            } else if (param[0].equals("width")) {
                width = Integer.parseInt(param[1]);
            } else if (param[0].equals("height")) {
                height = Integer.parseInt(param[1]);
            } else if (param[0].equals("hit_points")) {
                hitPoints = Integer.parseInt(param[1]);
                if (hitPoints > 1) {
                    String temp = fillParams[0];
                    fillParams = new String[hitPoints];
                    fillParams[0] = temp;
                }
            } else if (param[0].equals("stroke")) {
                stroke = Block.getColorFromString(param[1]);
            } else if (param[0].startsWith("fill")) {
                if (param[0].length() == 4) {
                    fillParams[0] = param[1];
                } else {
                    try {
                        fillParams[Integer.parseInt(param[0].split("-")[1]) - 1] = param[1];
                    } catch (Exception e) {
                        System.out.print("");
                    }
                }
            }
        }

        final int widthF = width, heightF = height, hitPointsF = hitPoints;
        final Color strokeF = stroke;
        final String[] fillParamsF = fillParams;

        // create block creator
        BlockCreator blockCreator = new BlockCreator() {
            @Override
            public Block create(int x, int y) {
                return new Block(x, y, widthF, heightF, fillParamsF, strokeF,
                        hitPointsF);
            }
        };
        if ((symbol != null) && (width != -1) && (height != -1)
                && (hitPoints != -1)) {
            bfsf.addBlockCreators(symbol, blockCreator);
        } else {
            System.out.println("blocks file format error");
        }
    }
}