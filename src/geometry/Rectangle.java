package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of a rectangle.
 *
 * @author sarah de paz
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;

    /**
     * constructor function that create new rectangle.
     *
     * @param upperLeft
     *            a point where the line start
     * @param width
     *            a point where the line end
     * @param height
     *            a point where the line end
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * constructor function that create new rectangle.
     *
     * @param x
     *            the x value of the upper left point
     * @param y
     *            the y value of the upper left point
     * @param width
     *            a point where the line end
     * @param height
     *            a point where the line end
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * function that create a (possibly empty) list of intersection points with
     * the specified line.
     *
     * @param line
     *            the line that we check if intersect the rectangle
     * @return the list of intersection points with a line
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> listPoints = new ArrayList<Point>();
        // the upper line
        if (line.isIntersecting(getUpperLine())) {
            listPoints.add(line.intersectionWith(getUpperLine()));
        }
        // the left line
        if (line.isIntersecting(getLeftLine())) {
            listPoints.add(line.intersectionWith(getLeftLine()));
        }
        // the right line
        if (line.isIntersecting(getRightLine())) {
            listPoints.add(line.intersectionWith(getRightLine()));
        }
        // the down line
        if (line.isIntersecting(getDownLine())) {
            listPoints.add(line.intersectionWith(getDownLine()));
        }
        return listPoints;
    }

    /**
     * function that returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * function that returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * function that returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * function that returns the upper line of the rectangle.
     *
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        return new Line(upperLeft, new Point(this.upperLeft.getX() + width,
                this.upperLeft.getY()));
    }

    /**
     * function that returns the down line of the rectangle.
     *
     * @return the down line of the rectangle
     */
    public Line getDownLine() {
        return new Line(new Point(this.upperLeft.getX(), this.upperLeft.getY()
                + this.height), new Point(this.upperLeft.getX() + width,
                this.upperLeft.getY() + this.height));
    }

    /**
     * function that returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        return new Line(upperLeft, new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height));
    }

    /**
     * function that returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return new Line(new Point(this.upperLeft.getX() + width,
                this.upperLeft.getY()), new Point(
                this.upperLeft.getX() + width, this.upperLeft.getY()
                        + this.height));
    }

    /**
     * function that set a new x value to the upper left point.
     *
     * @param x
     *            double for the x value
     */
    public void setUpperLeftX(double x) {
        this.upperLeft.setX(x);
    }

    /**
     * function that set a new y value to the upper left point.
     *
     * @param y
     *            double for the y value
     */
    public void setUpperLeftY(double y) {
        this.upperLeft.setY(y);
    }

    /**
     * function that check if a point is inside the rectangle.
     *
     * @param p
     *            the point which we check if inside
     * @return true if the point is in the rectangle, false otherwise
     */
    public boolean isInside(Point p) {
        double upperLeftX = this.upperLeft.getX(), upperLeftY = this.upperLeft
                .getY();
        return (((p.getX() > upperLeftX) || (Math.abs(p.getX() - upperLeftX) < 0.00001))
                && ((p.getX() < upperLeftX + this.width) || (Math.abs(p.getX()
                        - upperLeftX - this.width) < 0.00001))
                && ((p.getY() > upperLeftY) || (Math.abs(p.getY() - upperLeftY) < 0.00001)) && ((p
                .getY() < upperLeftY + this.height) || (Math.abs(p.getY()
                - upperLeftY - this.height) < 0.00001)));
    }
}