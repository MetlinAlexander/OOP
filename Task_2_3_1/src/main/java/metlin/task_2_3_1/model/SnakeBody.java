package metlin.task_2_3_1.model;

import java.util.ArrayList;
import java.util.List;

public class SnakeBody {
    private List<SnakeElement> snake;
    int len;

    public SnakeBody(SnakeElement head){
        snake = new ArrayList<>();
        snake.add(head);
        len = 1;
    }

    public SnakeElement getHead() {
        return snake.get(0);
    }

    public SnakeElement getTail() {
        return snake.get(this.len - 1);
    }

    public void moveSnake(SnakeElement newHead){
        this.snake.remove(this.len - 1);
        this.snake.add(0, newHead);
    }

    public void increaseSnake(SnakeElement newElem){
        this.snake.add(0, newElem);
        this.len += 1;
    }

    public boolean isSnake(int x, int y){
        for(int i=0; i<getLen(); i++) {
            if(snake.get(i).x() == x && snake.get(i).y() == y){
                return true;
            }
        }
        return false;
    }

    public int getLen() {
        return snake.size();
    }
}