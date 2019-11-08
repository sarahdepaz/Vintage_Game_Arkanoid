package levels;

import java.util.ArrayList;
import java.util.List;

import elements.Collidable;
import elements.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * Class of a game environment.
 *
 * @author sarah de paz
 */
public class GameEnvironment {
    private List<Collidable> listOfCollidables;

    /**
     * constructor function that create new GameEnvironment.
     */
    public GameEnvironment() {
        this.listOfCollidables = new ArrayList<Collidable>();
    }

    /**
     * function that add the given collidable to the environment.
     *
     * @param c
     *            a Collidable to add to the list of collidables
     */
    public void addCollidable(Collidable c) {
        this.listOfCollidables.add(c);
    }

    /**
     * function that remove the given collidable to the environment.
     *
     * @param c
     *            a Collidable to remove to the list of collidables
     */
    public void removeCollidable(Collidable c) {
        this.listOfCollidables.remove(c);
    }

    /**
     * function that checks if the point is in any of the collidables objects
     * list.
     *
     * @param p
     *            a point to check if it inside any collidable shape
     * @return true if the point is in one of the collidables, or false
     *         otherwise
     */
    public boolean checkIfInsideCollidable(Point p) {
        for (int i = 0; i < listOfCollidables.size(); i++) {
            if (listOfCollidables.get(i).getCollisionRectangle().isInside(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * function that checks the line from line.start() to line.end(). if this
     * object will not collide with any of the collidables in this collection,
     * return null. else, return the information about the closest collision
     * that is going to occur.
     *
     * @param trajectory
     *            a line from line.start() to line.end()
     * @return the information about the closest collision that is going to
     *         occur, on null if is no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.listOfCollidables.isEmpty()) {
            // if the list is empty return null
            return null;
        }
        double minDest = trajectory.length();
        int indexMin = -1;
        Point p;
        for (int i = 0; i < this.listOfCollidables.size(); i++) {
            p = trajectory
                    .closestIntersectionToStartOfLine(this.listOfCollidables
                            .get(i).getCollisionRectangle());
            if (p != null) {
                if (p.distance(trajectory.start()) <= minDest) {
                    // set new minimum details
                    minDest = p.distance(trajectory.start());
                    indexMin = i;
                }
            }
        }
        // if there is no collidables
        if (indexMin == -1) {
            return null;
        }
        Rectangle minRect = this.listOfCollidables.get(indexMin)
                .getCollisionRectangle();
        return (new CollisionInfo(
                trajectory.closestIntersectionToStartOfLine(minRect),
                this.listOfCollidables.get(indexMin)));
    }
}
