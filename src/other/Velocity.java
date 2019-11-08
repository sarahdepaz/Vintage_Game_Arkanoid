package other;

import geometry.Point;

/**
 * Class of a velocity.
 *
 * @author sarah de paz
 */
public class Velocity {
    private double dx, dy;

    /**
     * constructor function that create new velocity.
     *
     * @param dx
     *            a double of the change in the movement of the x
     * @param dy
     *            a double of the change in the movement of the y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * function that calculate the next point to move regarding the dx and dy.
     * the function get point in position (x,y), and return a new point in
     * position (x+dx,y+dy).
     *
     * @param p
     *            a point to change its position
     * @return the new point with the new position
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPoint;
    }

    /**
     * function that returns the dx of the velocity.
     *
     * @return the dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * function that returns the dy of the velocity.
     *
     * @return the dy of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * function that convert angle and speed to dx and dy into velocity.
     *
     * @param angle
     *            a double for the angel of the movement
     * @param speed
     *            a double for the speed of the movement
     * @return velocity with dx and dy regarding to the angel and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianDegree, dx, dy;
        radianDegree = (angle / 180) * Math.PI;
        dx = speed * Math.cos(radianDegree - (Math.PI / 2));
        dy = speed * Math.sin(radianDegree - (Math.PI / 2));
        return new Velocity(dx, dy);
    }
}
