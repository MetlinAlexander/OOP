package metlinsnake.model;

/**
 * class represent snake element.
 */
public class SnakeElement {
    private int x;

    private int y;

    /**
     * constructor for element.
     *
     * @param x x coord of element
     * @param y y coord of element
     */
    public SnakeElement(final int x, final int y) {
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
     * setter for x.
     *
     * @param x new x coord
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * getter for y.
     *
     * @return y coord
     */
    public int y() {
        return y;
    }

    /**
     * setter for y.
     *
     * @param y new y coord
     */
    public void setY(final int y) {
        this.y = y;
    }
}