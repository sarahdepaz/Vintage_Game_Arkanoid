package animation;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * Class of a PauseScreen.
 * @author sarab de paz
 */
public class PauseScreen implements Animation {

    /**
     * constructor function that pause the screen.
     */
    public PauseScreen() {
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
                            "resources/background_images/pause.jpg")));
        } catch (Exception e) {
            System.out.print("");
        }

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