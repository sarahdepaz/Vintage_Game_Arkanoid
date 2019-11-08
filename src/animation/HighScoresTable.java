package animation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import listeners.ScoreInfo;

/**
 * Class of a HighScoresTable.
 *
 * @author sarah de paz
 */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = -8324825923856892356L;
    private int size;
    private List<ScoreInfo> highScores;

    /**
     * constructor function that create an empty high-scores table with the
     * specified size.
     *
     * @param size
     *            the size means that the table holds up to size top scores
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<ScoreInfo>();
    }

    /**
     * function that add a high-score.
     *
     * @param score
     *            score to add
     */
    public void add(ScoreInfo score) {
        int index = getListIndex(score.getScore());
        if (index != this.highScores.size() + 1) {
            this.highScores.add(index - 1, score);
            if (this.highScores.size() >= this.size) {
                this.highScores = new ArrayList<ScoreInfo>(
                        this.highScores.subList(0, this.size));
            }
        } else {
            this.highScores.add(score);
        }
    }

    /**
     * function that return table maximum size.
     *
     * @return the table maximum size
     */
    public static int tableMaxSize() {
        return 5;
    }

    /**
     * function that return the file name.
     *
     * @return the file name
     */
    public static String fileName() {
        return "highscores.ser";
    }

    /**
     * function that return table size.
     *
     * @return the table size
     */
    public int size() {
        return this.size;
    }

    /**
     * function that return the current high scores ,the list is sorted such
     * that the highest scores come first.
     *
     * @return the current high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * function that return the rank of the current score.
     *
     * @param score
     *            the score of the player
     * @return the rank of the current score
     */
    public int getRank(int score) {
        return getListIndex(score);
    }

    /**
     * function that return the list index.
     *
     * @param score
     *            the score to find his index
     * @return the list index
     */
    public int getListIndex(int score) {
        if (this.highScores.size() == 0 && this.size >= 1) {
            return 1;
        }
        for (int i = 0; i < this.highScores.size(); i++) {
            if (score > this.highScores.get(i).getScore()) {
                return i + 1;
            }
        }
        return this.highScores.size() + 1;
    }

    /**
     * function that clears the table.
     */
    public void clear() {
        this.highScores.clear();
    }

    /**
     * function that load table data from file.
     *
     * @param filename
     *            the file to load
     * @throws IOException
     *             when there is any error in file load
     */
    public void load(File filename) throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable temp = (HighScoresTable) in.readObject();
            this.size = temp.size();
            this.highScores = temp.getHighScores();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * function that save table data to the specified file.
     *
     * @param filename
     *            the file to save
     * @throws IOException
     *             when there is any error in file save
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * function that read a table from file and return it,if the file does not
     * exist, or there is a problem with reading it, an empty table is returned.
     *
     * @param filename
     *            the file to read
     * @return the score table
     */
    public static HighScoresTable loadFromFile(File filename) {
        try {
            HighScoresTable scoreTable = new HighScoresTable(0);
            scoreTable.load(filename);
            return scoreTable;
        } catch (Exception e) {
            return null;
        }
    }
}
