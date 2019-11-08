package elements;

import geometry.Point;
import geometry.Rectangle;
import other.Velocity;

/**
 * Interface of a Collidable.
 *
 * @author sarah de paz
 */
public interface Collidable {
    /**
     * function that return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * function that notify the object that we collided with it at
     * collisionPoint with a given velocity. the return is the new velocity
     * expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter
     *            the ball hitter of the hit
     * @param collisionPoint
     *            the collision point of the hit
     * @param currentVelocity
     *            the current velocity of the object
     * @return the new velocity expected after the hit (based on the force the
     *         object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
