package animation;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * Class of an AnimationRunner.
 *
 * @author sarah de paz
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * constructor function that create the animation runner.
     *
     * @param g
     *            the gui to show the animation
     * @param frames
     *            the number of the frames
     */
    public AnimationRunner(GUI g, int frames) {
        this.sleeper = new biuoop.Sleeper();
        this.framesPerSecond = frames;
        this.gui = g;
    }

    /**
     * function that run the animation.
     *
     * @param animation
     *            the animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, 1 / (double) this.framesPerSecond);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}