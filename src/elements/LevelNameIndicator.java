package elements;

import java.awt.Color;

import levels.GameLevel;
import biuoop.DrawSurface;

/**
 * Class of a LevelNameIndicator.
 *
 * @author sarah de paz
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;

    /**
     * constructor function that create the level name indicator.
     *
     * @param levelName
     *            the the name of the level
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * function that draw the sprite to the screen.
     *
     * @param surface
     *            a DrawSurface to draw on it
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText(520, 16, "Level Name: " + levelName, 13);
    }

    /**
     * function that notify the sprites that time has passed.
     *
     * @param dt
     *            frames per second
     */
    public void timePassed(double dt) {

    }

    /**
     * function that add a sprite to the game.
     *
     * @param g
     *            a sprite to add to the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
