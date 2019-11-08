package listeners;

import java.io.Serializable;

/**
 * Class of a ScoreInfo.
 *
 * @author sarah de paz
 */
public class ScoreInfo implements Serializable {

    private static final long serialVersionUID = 482847284127287284L;
    private String name;
    private int score;

    /**
     * constructor function that create a score.
     *
     * @param name
     *            the name of the player
     * @param score
     *            the score of the player
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * function that return the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * function that return the score of the player.
     *
     * @return the score of the player
     */
    public int getScore() {
        return this.score;
    }
}
