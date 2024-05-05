package metlin.task_2_3_1.model;

/**
 * class represent snake element.
 */
public class SnakeElement {
    private int x;

    private int y;

    public SnakeElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}