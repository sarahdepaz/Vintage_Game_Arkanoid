package listeners;

/**
 * Interface of a HitNotifier.
 *
 * @author sarah de paz
 */
public interface HitNotifier {
    /**
     * function that add hl as a listener to hit events.
     *
     * @param hl
     *            to add to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     * function that remove hl from hit events.
     *
     * @param hl
     *            to remove from hit events.
     */
    void removeHitListener(HitListener hl);
}