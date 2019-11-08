package animation;

import java.awt.Color;

import levels.SpriteCollection;
import biuoop.DrawSurface;

/**
 * Class of a CountdownAnimation.
 *
 * @author sarah de paz
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom, leftTime;
    private SpriteCollection gameScreen;
    private boolean isFirstTime;

    /**
     * constructor function that create the count down animation.
     *
     * @param numOfSeconds
     *            the (seconds ) time each number appear on the screen
     * @param countFrom
     *            the number that the count down will start from
     * @param gameScreen
     *            the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.leftTime = 0;
        this.isFirstTime = true;
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
        double lt;
        if (this.isFirstTime) {
            lt = (1 / dt) * this.numOfSeconds;
            this.leftTime = (int) lt;
            this.isFirstTime = false;
        }
        if (this.leftTime <= 0) {
            lt = (1 / dt) * this.numOfSeconds;
            this.leftTime = (int) lt;
            this.countFrom--;
        }
        if (!this.shouldStop()) {
            this.gameScreen.drawAllOn(d);
            d.setColor(Color.BLACK);
            d.fillRectangle(d.getWidth() / 2 - 15, d.getHeight() / 2 - 50, 37,
                    60);
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2 - 15, d.getHeight() / 2,
                    Integer.toString(this.countFrom), 60);
        }
        this.leftTime--;
    }

    /**
     * function that charge of stopping condition.
     *
     * @return if the game should stop
     */
    public boolean shouldStop() {
        if (this.countFrom == 0) {
            return true;
        }
        return false;
    }
}