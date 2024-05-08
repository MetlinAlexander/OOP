package metlinsnake.model;

import java.util.ArrayList;
import java.util.List;

/**
 * class for all snake.
 */
public class SnakeBody {
    private List<SnakeElement> snake;
    private int len;

    /**
     * constructor for snake.
     *
     * @param head head of snake
     */
    public SnakeBody(SnakeElement head) {
        snake = new ArrayList<>();
        snake.add(head);
        len = 1;
    }

    /**
     * getter for head.
     *
     * @return cur head.
     */
    public SnakeElement getHead() {
        return snake.get(0);
    }

    /**
     * method to move snake.
     *
     * @param newHead newhead of snake
     */
    public void moveSnake(SnakeElement newHead) {
        this.snake.remove(this.len - 1);
        this.snake.add(0, newHead);
    }

    /**
     * method to increase len of snake.
     *
     * @param newElem newhead
     */
    public void increaseSnake(SnakeElement newElem) {
        this.snake.add(0, newElem);
        this.len += 1;
    }

    /**
     * method to check if cuurent element is snake.
     *
     * @param x x coord
     * @param y y coord
     * @return true/false
     */
    public boolean isSnake(int x, int y) {
        for (int i = 0; i < getLen(); i++) {
            if (snake.get(i).x() == x && snake.get(i).y() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * getter for len of snake.
     *
     * @return len of snake.
     */
    public int getLen() {
        return snake.size();
    }
}