package snake;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.atomic.AtomicReference;

import static javafx.scene.input.KeyCode.RIGHT;

public class Game {

    private SnakeBody snake;
    private Group root;

    private AtomicReference<KeyCode> lastPressedKey;

    private int delay;


    private  Rectangle[][] field;
    public Game(Group root){
        this.root = root;
        this.snake = new SnakeBody(new SnakeElement(5, 5));
        this.field = new Rectangle[24][16];
        delay = 50;

        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                Rectangle rectangle = new Rectangle();

                field[i][j] = rectangle;

                rectangle.setX(50 * i);
                rectangle.setY(50 * j);
                rectangle.setHeight(50);
                rectangle.setWidth(50);
                rectangle.setFill(Color.LIGHTGREEN);
                rectangle.setStroke(Color.BLACK);
                if (i == snake.getHead().x() && j == snake.getHead().y()) {
                    rectangle.setFill(Color.RED);
                }

                root.getChildren().add(rectangle);
            }
        }

        this.lastPressedKey = new AtomicReference<>(RIGHT);
    }

    public int getDelay() {
        return delay;
    }

    public void movement() {
        field[snake.getHead().x()][snake.getHead().y()].setFill(Color.LIGHTGREEN);
        SnakeElement newHead = snake.getHead();
        switch (lastPressedKey.get()) {
            case RIGHT:
                newHead.setX((newHead.x() + 1) % 24);
                break;
            case LEFT:
                newHead.setX((newHead.x() - 1 + 24) % 24);
                break;
            case UP:
                newHead.setY((newHead.y() - 1 + 16) % 16);
                break;
            case DOWN:
                newHead.setY((newHead.y() + 1) % 16);
                break;
        }
        snake.moveSnake(newHead);
        field[snake.getHead().x()][snake.getHead().y()].setFill(Color.RED);
    }

    public void setEvent() {
        this.root.setOnKeyPressed(event -> {
            this.lastPressedKey.set(event.getCode());
        });
    }
}
