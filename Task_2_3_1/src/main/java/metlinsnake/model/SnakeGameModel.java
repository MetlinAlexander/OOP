package metlinsnake.model;

import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * game model class.
 */
public class SnakeGameModel {
    private SnakeBody snake;
    private AtomicReference<KeyCode> lastPressedKey;
    private int delay;
    private Direction curDir;
    private AtomicBoolean isLose;
    private ArrayList<Apple> foods;
    private int maxLen = 15;
    private  AtomicBoolean isWin;

    /**
     * constuctor for model.
     */
    public SnakeGameModel() {
        this.snake = new SnakeBody(new SnakeElement(5, 5));
        this.delay = 100;
        this.lastPressedKey = new AtomicReference<>(KeyCode.RIGHT);
        this.curDir = Direction.RIGHT;
        this.foods = new ArrayList<>();
        this.foods.add(generateApple());
        this.isLose = new AtomicBoolean(false);
        this.isWin = new AtomicBoolean(false);
    }

    /**
     * getter for delay.
     *
     * @return int delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * method to check if cur cell is apple.
     *
     * @param x x coord
     * @param y y coord
     * @return true/false
     */
    public boolean isApple(int x, int y) {
        for (Apple elem : this.foods) {
            if (elem.x() == x && elem.y() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * method to delete apple.
     *
     * @param x x of apple
     * @param y y of apple
     */
    private void eatApple(int x, int y) {
        for (Apple elem : this.foods) {
            if (elem.x() == x && elem.y() == y) {
                foods.remove(elem);
                break;
            }
        }
    }

    /**
     * getter for snake.
     *
     * @return snake
     */
    public SnakeBody getSnake() {
        return snake;
    }

    /**
     * set last pressed key.
     *
     * @param keyCode key code
     */
    public void setLastPressedKey(KeyCode keyCode) {
        this.lastPressedKey.set(keyCode);
    }

    /**
     * method to update dir, using cur key.
     */
    private void updateDir() {
        switch (lastPressedKey.get()) {
            case RIGHT:
                if (this.curDir == Direction.LEFT) {
                    break;
                }
                this.curDir = Direction.RIGHT;
                break;
            case LEFT:
                if (this.curDir == Direction.RIGHT) {
                    break;
                }
                this.curDir = Direction.LEFT;
                break;
            case UP:
                if (this.curDir == Direction.DOWN) {
                    break;
                }
                this.curDir = Direction.UP;
                break;
            case DOWN:
                if (this.curDir == Direction.UP) {
                    break;
                }
                this.curDir = Direction.DOWN;
                break;
        }
    }

    /**
     * method to move or increase snake.
     */
    public void moveSnake() {
        SnakeElement currentHead = snake.getHead();
        SnakeElement newHead =
                new SnakeElement(currentHead.x(), currentHead.y());
        updateDir();
        if (this.curDir == Direction.RIGHT) {
            newHead.setX((newHead.x() + 1) % 24);
        } else if (this.curDir == Direction.LEFT) {
            newHead.setX((newHead.x() - 1 + 24) % 24);
        } else if (this.curDir == Direction.UP) {
            newHead.setY((newHead.y() - 1 + 16) % 16);
        } else if (this.curDir == Direction.DOWN) {
            newHead.setY((newHead.y() + 1) % 16);
        }
        if (checkCollision(newHead)) {
            this.snake.increaseSnake(newHead);
        } else {
            this.snake.moveSnake(newHead);
        }

        if (this.maxLen == this.snake.getLen()) {
            this.isWin.set(true);
        }
    }

    /**
     * method to generate new apple.
     *
     * @return new apple.
     */
    public Apple generateApple() {
        int xCoord;
        int yCoord;

        do {
            // Генерируем случайные координаты x и y
            xCoord = (int) (Math.random() * 24);
            yCoord = (int) (Math.random() * 16);
        } while (snake.isSnake(xCoord, yCoord));

        return new Apple(xCoord, yCoord);
    }

    /**
     * method to check collisions.
     *
     * @param newhead new head
     * @return true/false
     */
    private boolean checkCollision(SnakeElement newhead) {
        if (isApple(newhead.x(), newhead.y())) {
            eatApple(newhead.x(), newhead.y());
            this.foods.add(generateApple());
            return true;
        } else if (snake.isSnake(newhead.x(), newhead.y())) {
            this.isLose.set(true);
        }
        return false;
    }

    /**
     * getter for lose flag.
     *
     * @return lose flag
     */
    public AtomicBoolean getIsLose() {
        return isLose;
    }

    /**
     * getter for win flag.
     *
     * @return win flag
     */
    public AtomicBoolean getIsWin() {
        return isWin;
    }
}
