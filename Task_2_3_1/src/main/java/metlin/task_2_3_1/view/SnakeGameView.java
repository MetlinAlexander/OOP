package metlin.task_2_3_1.view;

// SnakeGameView.java
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeGameView {
    private Group root;
    private Rectangle[][] field;

    public SnakeGameView(Group root) {
        this.root = root;
        this.field = new Rectangle[24][16];

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
                root.getChildren().add(rectangle);
            }
        }
    }

    public Rectangle[][] getField() {
        return field;
    }
}

