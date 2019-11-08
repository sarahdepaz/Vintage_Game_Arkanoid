package levels;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import elements.Sprite;

/**
 * Class of a Sprite Collection.
 *
 * @author sarah de paz
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites;

    /**
     * constructor function that create new list of sprites.
     */
    public SpriteCollection() {
        this.listOfSprites = new ArrayList<Sprite>();
    }

    /**
     * function that add the given Sprite to the SpriteCollection.
     *
     * @param s
     *            a sprite to add to the SpriteCollection
     */
    public void addSprite(Sprite s) {
        this.listOfSprites.add(s);
    }

    /**
     * function that Remove hl from the list of listeners to hit events.
     *
     * @param s
     *            a sprite to remove from the list of listeners to hit event
     */
    public void removeSprite(Sprite s) {
        this.listOfSprites.remove(s);
    }

    /**
     * function that call timePassed() on all sprites..
     *
     * @param dt
     *            frames per second
     */
    public void notifyAllTimePassed(double dt) {
        if (!this.listOfSprites.isEmpty()) {
            for (int i = 0; i < this.listOfSprites.size(); i++) {
                this.listOfSprites.get(i).timePassed(dt);
            }
        }
    }

    /**
     * function that call drawOn(d) on all sprites.
     *
     * @param d
     *            a sprite to draw
     */
    public void drawAllOn(DrawSurface d) {
        if (!this.listOfSprites.isEmpty()) {
            for (int i = 0; i < this.listOfSprites.size(); i++) {
                this.listOfSprites.get(i).drawOn(d);
            }
        }
    }
}
