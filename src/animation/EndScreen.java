package animation;

import java.awt.Color;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * Class of an EndScreen.
 *
 * @author sarah de paz
 */
public class EndScreen implements Animation {
    private boolean isWin;
    private int score;

    /**
     * constructor function that create the end screen.
     *
     * @param isWin
     *            return if the player win or not
     * @param score
     *            the score of the game
     */
    public EndScreen(boolean isWin, int score) {
        this.isWin = isWin;
        this.score = score;
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
        String winOrLose;
        if (isWin) {
            winOrLose = "You Win!";
            try {
                d.drawImage(0, 0, ImageIO.read(ClassLoader
                        .getSystemClassLoader().getResourceAsStream(
                                "resources/background_images/win.jpg")));
            } catch (Exception e) {
                System.out.print("");
            }
            d.setColor(Color.BLACK);
            d.drawText(
                    78,
                    98,
                    winOrLose + " Your score is "
                            + Integer.toString(this.score), 48);
            d.setColor(Color.ORANGE);
            d.drawText(
                    80,
                    100,
                    winOrLose + " Your score is "
                            + Integer.toString(this.score), 48);
        } else {
            winOrLose = "Game Over.";
            try {
                d.drawImage(0, 0, ImageIO.read(ClassLoader
                        .getSystemClassLoader().getResourceAsStream(
                                "resources/background_images/lose.jpg")));
            } catch (Exception e) {
                System.out.print("");
            }
            d.setColor(Color.BLACK);
            d.drawText(
                    18,
                    248,
                    winOrLose + " Your score is "
                            + Integer.toString(this.score), 32);
            d.setColor(Color.ORANGE);
            d.drawText(
                    20,
                    250,
                    winOrLose + " Your score is "
                            + Integer.toString(this.score), 32);
        }
        d.setColor(Color.BLACK);
        d.drawText(219, 579, "Press space to see high scores", 22);
        d.setColor(Color.ORANGE);
        d.drawText(220, 580, "Press space to see high scores", 22);
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