package menu;

/**
 * Interface of a Task<T>.
 *
 * @author sarah de paz
 *
 * @param <T>
 *            a generic type of object to get into the menu animation
 */
public interface Task<T> {
    /**
     * function that run the task and return a value.
     *
     * @return a value
     */
    T run();
}