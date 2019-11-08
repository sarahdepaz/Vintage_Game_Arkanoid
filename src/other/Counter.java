package other;

/**
 * Class of a Counter.
 *
 * @author sarah de paz
 */
public class Counter {
    private int value;

    /**
     * constructor function that create a counter and initialize him to 0.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * constructor function that create a counter.
     *
     * @param number
     *            the value of the counter
     */
    public Counter(int number) {
        this.value = number;
    }

    /**
     * function that add number to current count.
     *
     * @param number
     *            the number we add to the current count
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * function subtract number from current count .
     *
     * @param number
     *            the number we subtract from the current count
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * function that get the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.value;
    }

    /**
     * function that set the number of the counter .
     *
     * @param number
     *            the number we subtract from the current count
     */
    public void setVal(int number) {
        this.value = number;
    }
}