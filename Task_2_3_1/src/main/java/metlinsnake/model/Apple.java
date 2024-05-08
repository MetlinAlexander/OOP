package metlinsnake.model;

/**
 * class for representing apple.
 */
public class Apple {
    private final int x;

    private final int y;

    /**
     * constructor fot apple.
     *
     * @param x init coord x
     * @param y init coord y
     */
    public Apple(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for x.
     *
     * @return x coord
     */
    public int x() {
        return x;
    }

    /**
     * getter for y.
     *
     * @return y coord
     */
    public int y() {
        return y;
    }
}