package menu;

import java.util.Map;

import animation.Animation;

/**
 * Interface of a Menu<T>.
 *
 * @author sarah de paz
 *
 * @param <T>
 *            a generic type of object to get into the menu animation
 */
public interface Menu<T> extends Animation {
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
    void addSelection(String key, String message, T returnVal);

    /**
     * function that return the status of the game.
     *
     * @return the status of the game
     */
    T getStatus();

    /**
     * function that add a sub menu.
     *
     * @param key
     *            key to wait for
     * @param message
     *            line to print
     * @param subMenu
     *            a sub menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * function that get a sub menu.
     *
     * @return the sub menu
     */
    Menu<T> getSubMenu();

    /**
     * function that get the menu chosen task.
     *
     * @return the menu chosen task
     */
    Map<String, MenuChoose<T>> getMenuChoose();
}