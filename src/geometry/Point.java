package geometry;

/**
 * Class of a Point.
 *
 * @author sarah de paz
 */
public class Point {
    private double x, y;

    /**
     * constructor function that create a new point.
     *
     * @param x
     *            the x value of the point
     * @param y
     *            the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * function that calculate the distance of this point to the other point.
     *
     * @param other
     *            a point for the distance
     * @return the distance between the points
     */
    public double distance(Point other) {
        double distX, distY;
        distX = this.x - other.getX();
        distY = this.y - other.getY();
        return Math.sqrt((distX * distX) + (distY * distY));
    }

    /**
     * function that return true is the points are equal, false otherwise.
     *
     * @param other
     *            a point to check if they equal
     * @return true is the points are equal and false otherwise
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * function that return the x value of this point.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * function that return the y value of this point.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * function that set a new x value to a point.
     *
     * @param newX
     *            double for the new x value
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * function that set a new y value to a point.
     *
     * @param newY
     *            double for the new x value
     */
    public void setY(double newY) {
        this.y = newY;
    }
}