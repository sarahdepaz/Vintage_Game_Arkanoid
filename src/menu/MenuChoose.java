package menu;

/**
 * Class of an MenuChoose<T>.
 *
 * @author sarah de paz
 *
 * @param <T>
 *            a generic type of object to get into the menu animation
 */
public class MenuChoose<T> {
    private String key, title;
    private T choose;

    /**
     * constructor function that create the menu choose.
     *
     * @param key
     *            to wait for
     * @param title
     *            the title of the menu
     * @param choose
     *            the chosen task
     */
    public MenuChoose(String key, String title, T choose) {
        this.key = key;
        this.title = title;
        this.choose = choose;
    }

    /**
     * function that return the key.
     *
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * function that return the title of the menu.
     *
     * @return the title of the menu
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * function that return the choose of the user.
     *
     * @return the choose of the user
     */
    public T getChoose() {
        return this.choose;
    }
}