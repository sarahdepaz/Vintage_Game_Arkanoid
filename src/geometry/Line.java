package geometry;

import java.util.List;

/**
 * Class of a line.
 *
 * @author sarah de paz
 */
public class Line {
    private Point start, end;

    /**
     * constructor function that create new line.
     *
     * @param start
     *            a point where the line start
     * @param end
     *            a point where the line end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor function that create new line.
     *
     * @param x1
     *            the x value of the point where the line start
     * @param y1
     *            the y value of the point where the line start
     * @param x2
     *            the x value of the point where the line end
     * @param y2
     *            the y value of the point where the line end
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point temptStartPoint = new Point(x1, y1);
        Point tempEndPoint = new Point(x2, y2);
        this.start = temptStartPoint;
        this.end = tempEndPoint;
    }

    /**
     * function that calculate the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * function that find and return the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        Point middle;
        double middleX, middleY;
        middleX = (this.start.getX() + this.end.getX()) / 2;
        middleY = (this.start.getY() + this.end.getY()) / 2;
        middle = new Point(middleX, middleY);
        return middle;
    }

    /**
     * function that returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * function that returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * function that check if lines intersect.
     *
     * @param other
     *            the line that we check if intersect
     * @return true if the lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point intersectionPoint = calculateIntersectingPoint(this, other);
        if (intersectionPoint == null) {
            return false;
        }
        return true;
    }

    /**
     * function that find and return the intersection point of 2 lines.
     *
     * @param other
     *            the line that we looking for the the intersect point
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersectionPoint = calculateIntersectingPoint(this, other);
        if (intersectionPoint == null) {
            return null;
        }
        return intersectionPoint;
    }

    /**
     * function that check if lines intersect.
     *
     * @param current
     *            the current line that we check if intersect
     * @param other
     *            the line that we check if intersect
     * @return the intersection point if the lines intersect and null otherwise
     */
    private static Point calculateIntersectingPoint(Line current, Line other) {
        double det, a1, b1, c1, a2, b2, c2, x, y;
        double xThisS, xThisE, xOtherS, xOtherE, yThisS, yThisE, yOtherS, yOtherE;
        // calculate the point
        xThisS = current.start.getX();
        xThisE = current.end.getX();
        xOtherS = other.start.getX();
        xOtherE = other.end.getX();
        yThisS = current.start.getY();
        yThisE = current.end.getY();
        yOtherS = other.start.getY();
        yOtherE = other.end.getY();
        a1 = yThisE - yThisS;
        b1 = xThisS - xThisE;
        c1 = (a1 * xThisS) + (b1 * yThisS);
        a2 = yOtherE - yOtherS;
        b2 = xOtherS - xOtherE;
        c2 = (a2 * xOtherS) + (b2 * yOtherS);
        det = (a1 * b2) - (a2 * b1);
        if (Math.abs(det) < 0.00001) {
            // Lines are parallel
            return null;
        }
        x = ((b2 * c1) - (b1 * c2)) / det;
        y = ((a1 * c2) - (a2 * c1)) / det;
        // check if the point is in range in the this line
        if (!checkRange(x, Math.min(xThisS, xThisE), Math.max(xThisS, xThisE))) {
            return null;
        }
        if (!checkRange(y, Math.min(yThisS, yThisE), Math.max(yThisS, yThisE))) {
            return null;
        }
        if (!checkRange(x, Math.min(xOtherS, xOtherE),
                Math.max(xOtherS, xOtherE))) {
            return null;
        }
        if (!checkRange(y, Math.min(yOtherS, yOtherE),
                Math.max(yOtherS, yOtherE))) {
            return null;
        }
        return (new Point(x, y));
    }

    /**
     * function that check if target is in range.
     *
     * @param target
     *            the number (double) that we check if it is in range
     * @param range1
     *            the number (double) for the left range side
     * @param range2
     *            the number (double) for the right range side
     * @return true if the number is in the range, false otherwise
     */
    private static boolean checkRange(double target, double range1,
            double range2) {
        return (((range1 < target) || (Math.abs(range1 - target) < 0.00001)) && ((Math
                .abs(range2 - target) < 0.00001) || (target < range2)));
    }

    /**
     * function that check if 2 lines are equals.
     *
     * @param other
     *            the line that we check if equals
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        } else if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * function that return the closest intersection point to the start of the
     * line. if this line does not intersect with the rectangle, return null.
     *
     * @param rect
     *            the rectangle for the intersection
     * @return the closest intersection point to the start of the line, null
     *         otherwise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listPoints = rect.intersectionPoints(new Line(this.start,
                this.end));
        if (listPoints.isEmpty()) {
            return null;
        } else if (listPoints.size() == 1) {
            return listPoints.get(0);
        } else {
            if (this.start.distance(listPoints.get(0)) <= this.start
                    .distance(listPoints.get(1))) {
                return listPoints.get(0);
            } else {
                return listPoints.get(1);
            }
        }
    }

    /**
     * function that check if a point is on a line.
     *
     * @param p
     *            the point to check if on the line
     * @return true if the point is on the line, false otherwise
     */
    public boolean isPointOnLine(Point p) {
        if (Math.abs(p.distance(this.start) + p.distance(this.end)
                - this.length()) < 0.0001) {
            return true;
        }
        return false;
    }
}