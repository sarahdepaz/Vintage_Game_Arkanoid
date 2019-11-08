package animation;

import java.awt.Color;
import java.util.List;

import javax.imageio.ImageIO;

import listeners.ScoreInfo;
import biuoop.DrawSurface;

/**
 * Class of a HighScoresAnimation.
 *
 * @author sarah de paz
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;

    /**
     * constructor function that create the high score animation .
     *
     * @param scores
     *            the scores of the players
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    /**
     * function that charge of the logic.
     *
     * @param d
     *            the sprites to draw to the screen
     * @param dt
     *            frames per second
     */
    public void doOneFrame(DrawSurface d, double dt) {
        try {
            d.drawImage(0, 0, ImageIO.read(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(
                            "resources/background_images/highscores.jpg")));
        } catch (Exception e) {
            System.out.print("");
        }
        d.setColor(Color.BLACK);
        d.drawText(148, 98, "Name", 50);
        d.drawText(498, 98, "Score", 50);
        d.setColor(Color.ORANGE);
        d.drawText(150, 100, "Name", 50);
        d.drawText(500, 100, "Score", 50);
        List<ScoreInfo> highScores = this.scores.getHighScores();
        for (int i = 0; i < highScores.size(); i++) {
            d.setColor(Color.BLACK);
            d.drawText(148, 118 + ((i + 1) * (scores.size() + 50)), highScores
                    .get(i).getName(), 32);
            d.drawText(498, 118 + ((i + 1) * (scores.size() + 50)),
                    String.valueOf(highScores.get(i).getScore()), 32);
            d.setColor(Color.ORANGE);
            d.drawText(150, 120 + ((i + 1) * (scores.size() + 50)), highScores
                    .get(i).getName(), 32);
            d.drawText(500, 120 + ((i + 1) * (scores.size() + 50)),
                    String.valueOf(highScores.get(i).getScore()), 32);
        }
        d.setColor(Color.BLACK);
        d.drawText(219, 579, "Press space to return main menu", 22);
        d.setColor(Color.ORANGE);
        d.drawText(220, 580, "Press space to return main menu", 22);
    }

    /**
     * function that charge of stopping condition.
     *
     * @return if the game should stop
     */
    public boolean shouldStop() {
        return false;
    }
}
