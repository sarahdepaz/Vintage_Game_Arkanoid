package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class of an KeyPressStoppableAnimation.
 *
 * @author sarah de paz
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private boolean isAlreadyPressed, shouldStop;
    private Animation animation;

    /**
     * constructor function that create the key press stoppable animation.
     *
     * @param sensor
     *            connect between the keyboard and the game
     * @param key
     *            the key that the user pressed
     * @param animation
     *            the animation to stop
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
            Animation animation) {
        this.isAlreadyPressed = true;
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.shouldStop = false;
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
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.shouldStop = true;
        }
        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * function that charge of stopping condition.
     *
     * @return if the game should stop
     */
    public boolean shouldStop() {
        return this.shouldStop;
    }
}
