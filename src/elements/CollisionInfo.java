package elements;

import geometry.Point;

/**
 * Class of a collisionInfo.
 *
 * @author sarah de paz
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor function that create a new collision info.
     *
     * @param collisionPoint
     *            the point of the collision
     * @param collisionObject
     *            the object of the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * function that return the collision point.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * function that return the collision object.
     *
     * @return the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
