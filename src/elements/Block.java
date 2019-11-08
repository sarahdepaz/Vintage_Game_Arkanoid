package elements;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import levels.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import other.ColorsParser;
import other.Velocity;
import biuoop.DrawSurface;

/**
 * Class of a Block.
 *
 * @author sarah de paz
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle blockRectangle;
    private Color color, stroke;
    private int hitPoints;
    private boolean printBorder;
    private Image image;
    private String[] fillParams;

    /**
     * constructor function that create new block.
     *
     * @param x
     *            the x value of the upper left point of the rectangle
     * @param y
     *            the y value of the upper left point of the rectangle
     * @param width
     *            a point where the line end of the rectangle
     * @param height
     *            a point where the line end of the rectangle
     * @param color
     *            a color for the block
     * @param hitPoints
     *            an it for the hit points of the block
     */
    public Block(double x, double y, double width, double height, Color color,
            int hitPoints) {
        this.blockRectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.hitPoints = hitPoints;
        if (hitPoints == -1) {
            this.printBorder = false;
        } else {
            this.printBorder = true;
        }
        this.hitListeners = new ArrayList<HitListener>();
        this.image = null;
        this.stroke = Color.BLACK;
        this.fillParams = null;
    }

    /**
     * constructor function that create new block.
     *
     * @param x
     *            the x value of the upper left point of the rectangle
     * @param y
     *            the y value of the upper left point of the rectangle
     * @param width
     *            a point where the line end of the rectangle
     * @param height
     *            a point where the line end of the rectangle
     * @param color
     *            a color for the block
     * @param stroke
     *            the color of the stroke
     * @param hitPoints
     *            an it for the hit points of the block
     */
    public Block(double x, double y, double width, double height, Color color,
            Color stroke, int hitPoints) {
        this.blockRectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.image = null;
        this.stroke = stroke;
        this.hitPoints = hitPoints;
        this.printBorder = true;
        this.hitListeners = new ArrayList<HitListener>();
        this.fillParams = null;
    }

    /**
     * constructor function that create new block.
     *
     * @param x
     *            the x value of the upper left point of the rectangle
     * @param y
     *            the y value of the upper left point of the rectangle
     * @param width
     *            a point where the line end of the rectangle
     * @param height
     *            a point where the line end of the rectangle
     * @param image
     *            image that used as a block
     * @param stroke
     *            the color of the stroke
     * @param hitPoints
     *            an it for the hit points of the block
     */
    public Block(double x, double y, double width, double height, Image image,
            Color stroke, int hitPoints) {
        this.blockRectangle = new Rectangle(x, y, width, height);
        this.color = null;
        this.image = image;
        this.stroke = stroke;
        this.hitPoints = hitPoints;
        this.printBorder = true;
        this.hitListeners = new ArrayList<HitListener>();
        this.fillParams = null;
    }

    /**
     * constructor function that create new block.
     *
     * @param x
     *            the x value of the upper left point of the rectangle
     * @param y
     *            the y value of the upper left point of the rectangle
     * @param width
     *            a point where the line end of the rectangle
     * @param height
     *            a point where the line end of the rectangle
     * @param fillP
     *            a string we get the color from
     * @param stroke
     *            the color of the stroke
     * @param hitPoints
     *            an it for the hit points of the block
     */
    public Block(double x, double y, double width, double height,
            String[] fillP, Color stroke, int hitPoints) {
        this.blockRectangle = new Rectangle(x, y, width, height);
        this.color = null;
        this.image = null;
        this.stroke = stroke;
        this.hitPoints = hitPoints;
        this.printBorder = true;
        this.hitListeners = new ArrayList<HitListener>();
        this.fillParams = new String[hitPoints];
        this.fillParams = fillP;
        String fill = fillP[0];
        if (fillP.length >= hitPoints - 1) {
            if (fillP[hitPoints - 1] != null) {
                fill = fillP[hitPoints - 1];
            }
        }
        if (fill.startsWith("color")) {
            this.color = getColorFromString(fill);
        } else if (fill.startsWith("image")) {
            this.image = getImageFromString(fill);
        }
    }

    /**
     * constructor function that create new block.
     *
     * @param b
     *            block to create
     */
    public Block(Block b) {
        this.blockRectangle = b.getCollisionRectangle();
        this.color = b.getColor();
        this.image = b.getImage();
        this.stroke = b.getStroke();
        this.hitPoints = b.getHitPoints();
        this.printBorder = b.getPrintBorder();
        this.hitListeners = b.getHitListeners();
        this.fillParams = b.getFillParams();
    }

    /**
     * function that copy the block.
     *
     * @return copy of the block
     */
    public Block copy() {
        return new Block(this);
    }

    /**
     * function that checks whether we need to print the border or not.
     *
     * @return whether we need to print the border or not
     */
    public boolean getPrintBorder() {
        return this.printBorder;
    }

    /**
     * function that get image .
     *
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * function color the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * function that set the color of the stroke .
     *
     * @return the color of the stroke
     */
    public Color getStroke() {
        return this.stroke;
    }

    /**
     * function that get a string color .
     *
     * @return a string color
     */
    public String[] getFillParams() {
        return this.fillParams;
    }

    /**
     * function that copy the block.
     *
     * @return copy of the block
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * function that fill all the other blocks.
     */
    public void resetFill() {
        String fill = this.fillParams[0];
        if (this.hitPoints > 0) {
            if (fillParams != null) {
                if (this.fillParams.length >= this.hitPoints - 1) {
                    if (this.fillParams[hitPoints - 1] != null) {
                        fill = this.fillParams[hitPoints - 1];
                    }
                }
                if (fill.startsWith("color")) {
                    this.color = getColorFromString(fill);
                } else if (fill.startsWith("image")) {
                    this.image = getImageFromString(fill);
                }
            }
        }
    }

    /**
     * function that return the "collision shape" of the object.
     *
     * @return the rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.blockRectangle;
    }

    /**
     * function that notify the object that we collided with it at
     * collisionPoint with a given velocity.
     *
     * @param hitter
     *            the ball hitter of the hit
     * @param collisionPoint
     *            the collision point of the hit
     * @param currentVelocity
     *            the current velocity of the ball
     * @return the new velocity expected after the hit (based on the force the
     *         object inflicted on us)
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (this.hitPoints > 0) {
            this.hitPoints--;
            this.resetFill();
        }
        this.notifyHit(hitter);
        if ((this.blockRectangle.getUpperLine().isPointOnLine(collisionPoint))
                || (this.blockRectangle.getDownLine()
                        .isPointOnLine(collisionPoint))) {
            // check if the hit is on the corners
            if ((this.blockRectangle.getLeftLine()
                    .isPointOnLine(collisionPoint))
                    || (this.blockRectangle.getRightLine()
                            .isPointOnLine(collisionPoint))) {
                // hit the corners
                return (new Velocity((-1) * currentVelocity.getDx(), (-1)
                        * currentVelocity.getDy()));
            }
            // hit the up or down
            return (new Velocity(currentVelocity.getDx(), (-1)
                    * currentVelocity.getDy()));
        }
        // hit the sides (left or right)
        return (new Velocity((-1) * currentVelocity.getDx(),
                currentVelocity.getDy()));
    }

    /**
     * function that draw the block on the surface.
     *
     * @param surface
     *            a DrawSurface to draw the block on it
     */
    public void drawOn(DrawSurface surface) {
        int x, y, width, height;
        x = (int) this.blockRectangle.getUpperLeft().getX();
        y = (int) this.blockRectangle.getUpperLeft().getY();
        width = (int) this.blockRectangle.getWidth();
        height = (int) this.blockRectangle.getHeight();
        if (this.color != null) {
            surface.setColor(this.color);
            surface.fillRectangle(x, y, width, height);
        } else if (this.image != null) {
            surface.drawImage(x, y, image);
        }
        if ((printBorder) && (this.stroke != null)) {
            surface.setColor(this.stroke);
            surface.drawRectangle(x, y, width, height);
        }
    }

    /**
     * function that notify that time has passed.
     *
     * @param dt
     *            frames per second
     */
    public void timePassed(double dt) {
    }

    /**
     * function that add the block to the game.
     *
     * @param g
     *            is the block that that we add to the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * function that remove the block from the game.
     *
     * @param game
     *            is the game that we remove the block from him
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * function that notify all listeners about a hit event.
     *
     * @param hitter
     *            is the ball (the hitter object)
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * function that return the hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * function that add hl as a listener to hit events.
     *
     * @param hl
     *            the hl to add to hit events
     */
    public void addHitListener(HitListener hl) {
        if (!this.hitListeners.contains(hl)) {
            this.hitListeners.add(hl);
        }
    }

    /**
     * function that remove hl from the list of listeners to hit events.
     *
     * @param hl
     *            the hl to remove from the hit events
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * function that get the color of the block from a string .
     *
     * @param s
     *            the string we get the color from
     * @return the color
     */
    public static Color getColorFromString(String s) {
        ColorsParser parser = new ColorsParser();
        if (s.startsWith("color(RGB")) {
            String[] rgb = s.substring(10, s.indexOf("))")).split(",");
            return parser.colorFromRGB(Integer.parseInt(rgb[0]),
                    Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
        } else {
            return parser.colorFromString(s.substring(6, s.indexOf(")")));
        }
    }

    /**
     * function that get the image of the block from a string .
     *
     * @param s
     *            the string we get the image from
     * @return the images
     */
    public static Image getImageFromString(String s) {
        try {
            return ImageIO.read(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(s.substring(6, s.indexOf(")"))));
        } catch (Exception e) {
            System.out.print("");
        }
        return null;
    }
}