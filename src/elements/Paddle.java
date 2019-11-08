package elements;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

import levels.GameLevel;
import other.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Class of a Paddle.
 *
 * @author sarah de paz
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRectangle;
    private Color color;
    private double leftRange, rightRange, speed;

    /**
     * constructor function that create new paddle.
     *
     * @param gui
     *            connect between the Keyboard to the paddle
     * @param x
     *            the x point that creates the rectangle
     * @param y
     *            the y point that creates the rectangle
     * @param width
     *            is the width of the board
     * @param height
     *            is the height of the board
     * @param color
     *            the color of the paddle
     * @param speed
     *            is the speed of the paddle
     */
    public Paddle(GUI gui, double x, double y, double width, double height,
            Color color, double speed) {
        this.keyboard = gui.getKeyboardSensor();
        this.paddleRectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.leftRange = 0;
        this.rightRange = 0;
        this.speed = speed;
    }

    /**
     * function that define the range of the paddle.
     *
     * @param left
     *            is the left range that the paddle can move
     * @param right
     *            is the right range that the paddle can move
     */
    public void setRange(double left, double right) {
        this.leftRange = left;
        this.rightRange = right;
    }

    /**
     * function that returns the height of the paddle.
     *
     * @return the height of the paddle
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * function that returns the height of the paddle.
     *
     * @return the height of the paddle
     */
    public double getWidth() {
        return this.paddleRectangle.getWidth();
    }

    /**
     * function that make the paddle move to the left, according to the player
     * key presses.
     *
     * @param dt
     *            frames per second
     */
    public void moveLeft(double dt) {
        double x = this.paddleRectangle.getUpperLeft().getX();
        if (x >= leftRange + speed * dt) {
            this.paddleRectangle.setUpperLeftX(x - speed * dt);
        } else {
            this.paddleRectangle.setUpperLeftX(leftRange);
        }
    }

    /**
     * function that make the paddle move to the right, according to the player
     * key presses.
     *
     * @param dt
     *            frames per second
     */
    public void moveRight(double dt) {
        double x = this.paddleRectangle.getUpperLeft().getX(), blockWidth = this.paddleRectangle
                .getWidth();
        if (x <= rightRange - blockWidth - speed * dt) {
            this.paddleRectangle.setUpperLeftX(x + speed * dt);
        } else {
            this.paddleRectangle.setUpperLeftX(rightRange - blockWidth);
        }
    }

    /**
     * function that notify that time has passed and make the paddle move
     * according to the player key presses.
     *
     * @param dt
     *            frames per second
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * function that draw the paddle on the board.
     *
     * @param d
     *            a paddle to draw to the board
     */
    public void drawOn(DrawSurface d) {
        int x, y, width, height;
        x = (int) this.paddleRectangle.getUpperLeft().getX();
        y = (int) this.paddleRectangle.getUpperLeft().getY();
        width = (int) this.paddleRectangle.getWidth();
        height = (int) this.paddleRectangle.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * function that return the paddleRectangle.
     *
     * @return the paddleRectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * function that return the velocity of the paddle.
     *
     * @param hitter
     *            the ball hitter of the hit
     * @param collisionPoint
     *            the collision point of the paddle
     * @param currentVelocity
     *            the current velocity of the paddle
     * @return the velocity of the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (this.paddleRectangle.getUpperLine().isPointOnLine(collisionPoint)) {
            double dxPow, dyPow, ballSpeed, dist;
            dxPow = currentVelocity.getDx() * currentVelocity.getDx();
            dyPow = currentVelocity.getDy() * currentVelocity.getDy();
            ballSpeed = Math.sqrt(dxPow + dyPow);
            ballSpeed = (double) (int) ballSpeed;
            dist = this.paddleRectangle.getWidth() / 5;
            Point upperLeft = this.paddleRectangle.getUpperLeft();
            if (upperLeft.distance(collisionPoint) < dist) {
                // hit part 1
                return (Velocity.fromAngleAndSpeed(-60, ballSpeed));
            } else if (upperLeft.distance(collisionPoint) < 2 * dist) {
                // hit part 2
                return (Velocity.fromAngleAndSpeed(-30, ballSpeed));
            } else if (upperLeft.distance(collisionPoint) < 3 * dist) {
                // hit part 3
                return (new Velocity(currentVelocity.getDx(), (-1)
                        * currentVelocity.getDy()));
                // we don't know what you meant to do with the middle.. this is
                // the other option:
                // return (new Velocity(currentVelocity.getDx(), (-1) *
                // currentVelocity.getDy()));
            } else if (upperLeft.distance(collisionPoint) < 4 * dist) {
                // hit part 4
                return (Velocity.fromAngleAndSpeed(30, ballSpeed));
            } else {
                // hit part 5
                return (Velocity.fromAngleAndSpeed(60, ballSpeed));
            }
        }
        // hit the sides (left or right)
        return (new Velocity((-1) * currentVelocity.getDx(), (-1)
                * currentVelocity.getDy()));
    }

    /**
     * function that add the paddle to the game.
     *
     * @param g
     *            is the paddle that that we add to the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * function that move the paddle to the middle of the screen.
     */
    public void movePaddleToMiddle() {
        this.paddleRectangle.setUpperLeftX(((this.rightRange + 20) / 2)
                - (this.paddleRectangle.getWidth() / 2));
    }
}