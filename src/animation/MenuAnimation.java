package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import menu.Menu;
import menu.MenuChoose;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class of an MenuAnimation<T>.
 *
 * @author sarab de paz
 *
 * @param <T>
 *            a generic type of object to get into the menu animation
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private Background background;
    private KeyboardSensor ks;
    private Map<String, MenuChoose<T>> menuChoose;
    private boolean shouldStop;
    private String keyPressed;
    private Map<String, MenuChoose<Menu<T>>> subMenu;

    /**
     * constructor function that create the menu animation.
     *
     * @param title
     *            the title of the menu
     * @param ks
     *            connect between the keyboard and the game
     */
    public MenuAnimation(String title, KeyboardSensor ks) {
        this.title = title;
        this.background = new Background(Color.cyan);
        try {
            this.background = new Background(ImageIO.read(ClassLoader
                    .getSystemClassLoader().getResourceAsStream(
                            "resources/background_images/menu.jpg")));
        } catch (Exception e) {
            System.out.print("");
        }
        this.ks = ks;
        this.menuChoose = new TreeMap<String, MenuChoose<T>>();
        this.shouldStop = false;
        this.keyPressed = null;
        this.subMenu = new TreeMap<String, MenuChoose<Menu<T>>>();
    }

    /**
     * function that return the menu choose.
     *
     * @return the menu choose
     */
    public Map<String, MenuChoose<T>> getMenuChoose() {
        return this.menuChoose;
    }

    /**
     * function that charge of the logic.
     *
     * @param d
     *            the sprites to draw to the screen
     * @param dt
     *            frames per second
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int index = 0;
        List<String> keys = new ArrayList<String>();
        this.background.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(88, 148, this.title, 90);
        d.setColor(Color.ORANGE);
        d.drawText(90, 150, this.title, 90);
        keys.clear();
        keys.addAll(this.menuChoose.keySet());
        keys.addAll(this.subMenu.keySet());
        String titleMenu = null;
        for (String key : keys) {
            if (menuChoose.containsKey(key)) {
                titleMenu = this.menuChoose.get(key).getTitle();
            } else if (subMenu.containsKey(key)) {
                titleMenu = this.subMenu.get(key).getTitle();
            }
            d.setColor(Color.BLACK);
            d.drawText(138, 138 + ((index + 1) * 70), "(" + key + ") "
                    + titleMenu, 40);
            d.setColor(Color.ORANGE);
            d.drawText(140, 140 + ((index + 1) * 70), "(" + key + ") "
                    + titleMenu, 40);
            index++;
        }
        for (String key : keys) {
            if (this.ks.isPressed(key)) {
                this.keyPressed = key;
                this.shouldStop = true;
                return;
            }
        }
    }

    /**
     * function that charge of stopping condition.
     *
     * @return if the game should stop
     */
    public boolean shouldStop() {
        return this.shouldStop;
    }

    /**
     * function that add the selection of the user.
     *
     * @param key
     *            key to wait for
     * @param message
     *            line to print
     * @param returnVal
     *            what to return
     */
    public void addSelection(String key, String message, T returnVal) {
        this.menuChoose.put(key, new MenuChoose<T>(key, message, returnVal));
    }

    /**
     * function that return the status of the game.
     *
     * @return the status of the game
     */
    public T getStatus() {
        if (this.keyPressed != null) {
            if (this.menuChoose.containsKey(this.keyPressed)) {
                this.shouldStop = false;
                return this.menuChoose.get(this.keyPressed).getChoose();
            } else if (this.getSubMenu().getMenuChoose()
                    .containsKey(this.keyPressed)) {
                this.shouldStop = false;
                return this.getSubMenu().getMenuChoose().get(this.keyPressed)
                        .getChoose();

            }
        }
        return null;
    }

    /**
     * function that add a sub menu.
     *
     * @param key
     *            key to wait for
     * @param message
     *            line to print
     * @param subMenuNew
     *            a sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenuNew) {
        this.subMenu
                .put(key, new MenuChoose<Menu<T>>(key, message, subMenuNew));
    }

    /**
     * function that get a sub menu.
     *
     * @return the sub menu
     */
    public Menu<T> getSubMenu() {
        if (this.keyPressed != null) {
            if (this.subMenu.containsKey(this.keyPressed)) {
                this.shouldStop = false;
                return this.subMenu.get(this.keyPressed).getChoose();
            }
        }
        return null;
    }
}
