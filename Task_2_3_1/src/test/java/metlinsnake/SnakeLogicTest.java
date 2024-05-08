package metlinsnake;

import metlinsnake.model.Apple;
import metlinsnake.model.SnakeBody;
import metlinsnake.model.SnakeElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SnakeLogicTest {

    @Test
    public void SnakeElemTest() {
        SnakeElement elem = new SnakeElement(0, 0);
        elem.setX(12);
        elem.setY(13);
        Assertions.assertEquals(elem.getY(), 13);
        Assertions.assertEquals(elem.getX(), 12);
    }

    @Test
    public void AppleTest() {
        Apple apple = new Apple(11, 13);
        Assertions.assertEquals(apple.getX(), 11);
        Assertions.assertEquals(apple.y(), 13);
    }

    @Test
    public void SnakeBodyTest1() {
        SnakeBody snake = new SnakeBody(new SnakeElement(0, 0));
        snake.increaseSnake(new SnakeElement(0, 1));
        snake.moveSnake(new SnakeElement(0, 2));
        Assertions.assertTrue(snake.isSnake(0, 2));
        Assertions.assertEquals(snake.getLen(), 2);
    }
}
