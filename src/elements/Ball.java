package elements;

import geometry.Line;
import geometry.Point;

import java.awt.Color;

import levels.GameEnvironment;
import levels.GameLevel;
import other.Velocity;
import biuoop.DrawSurface;

/**
 * Class of a Ball.
 *
 * @author sarah de paz
 */
public class Ball implements Sprite {
    private Velocity velocity;
    private Point center;
    private int radius;
    private Color color;
    private GameEnvironment gameEnv;

    /**
     * constructor function that create new ball.
     *
     * @param center
     *            a point of the center of the ball
     * @param r
     *            an integer for the radius of the ball
     * @param color
     *            the color of the ball
     * @param gameEnv
     *            the game environment of the ball
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnv) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.gameEnv = gameEnv;
    }

    /**
     * constructor function that create new ball.
     *
     * @param x
     *            a double for the x value of the center of the ball
     * @param y
     *            a double for the y value of the center of the ball
     * @param r
     *            an integer for the radius of the ball
     * @param color
     *            the color of the ball
     * @param gameEnv
     *            the game environment of the ball
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnv) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnv = gameEnv;
    }

    /**
     * function that return the x value of the center of the ball.
     *
     * @return the x value of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * function that return the center of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * function that return the y value of the center of the ball.
     *
     * @return the y value of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * function that return the radius of the ball.
     *
     * @return the int of radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * function that return the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * function that set the ball's velocity.
     *
     * @param v
     *            a Velocity to add to the ball
     * @param dt
     *            frames per second
     */
    public void setVelocity(Velocity v, double dt) {
        this.velocity = new Velocity(v.getDx() * dt, v.getDy() * dt);
        ;
    }

    /**
     * function that set the ball's velocity by dx and dy.
     *
     * @param dx
     *            the dx value of the velocity
     * @param dy
     *            the dy value of the velocity
     * @param dt
     *            frames per second
     */
    public void setVelocity(double dx, double dy, double dt) {
        this.velocity = new Velocity(dx * dt, dy * dt);
    }

    /**
     * function that return the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * function that set a new velocity to the ball.
     *
     * @param dt
     *            frames per second
     */
    public void moveOneStep(double dt) {
        // check if the ball stuck in the paddle movement, and take it out
        if (this.gameEnv.checkIfInsideCollidable(this.center)) {
            this.center = new Point(this.center.getX(), this.center.getY() - 10);
            return;
        }
        double dx = this.velocity.getDx(), dy = this.velocity.getDy();
        Point p = new Point(this.center.getX() + dx, this.center.getY() + dy);
        // set trajectory line
        Line trajectory = new Line(this.center, p);
        CollisionInfo info = this.gameEnv.getClosestCollision(trajectory);
        if (info != null) {
            // if there is a hit
            Point c = info.collisionPoint();
            Point m = (new Line(this.center, c)).middle();
            if ((new Line(c, m)).length() > 0.0001) {
                this.center = m;
            }
            // set new velocity
            this.setVelocity(
                    info.collisionObject().hit(this, c, this.velocity), 1);
            return;
        }
        // if no hit, go to next point
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * function that draw the ball on the surface.
     *
     * @param surface
     *            a DrawSurface to draw the ball on it
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(),
                this.radius);
    }

    /**
     * function that notify that time has passed and make the ball move to the
     * next step.
     *
     * @param dt
     *            frames per second
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * function that add the ball to the game.
     *
     * @param g
     *            is the ball that that we add to the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * function that remove the ball from the game.
     *
     * @param game
     *            the game we remove the ball from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}