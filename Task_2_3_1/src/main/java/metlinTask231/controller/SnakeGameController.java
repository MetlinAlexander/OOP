package metlinTask231.controller;

import javafx.scene.Group;
import metlinTask231.model.SnakeGameModel;
import metlinTask231.view.Colors;
import metlinTask231.view.SnakeGameView;

/**
 * controller class for snake game.
 */
public class SnakeGameController {
    private SnakeGameModel model;
    private SnakeGameView view;

    /**
     * consructor for controler.
     *
     * @param model model of snake
     * @param view view of game
     */
    public SnakeGameController(SnakeGameModel model, SnakeGameView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * method to update screen,
     * using info from model.
     */
    public void updateView() {
        int snakeHeadX = model.getSnake().getHead().x();
        int snakeHeadY = model.getSnake().getHead().y();

        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == snakeHeadX && j == snakeHeadY) {
                    view.getField()[i][j].setFill(Colors.SNAKE_HEAD_COLOR);
                } else if (model.getSnake().isSnake(i, j)) {
                    view.getField()[i][j].setFill(Colors.SNAKE_BODY_COLOR);
                } else if (model.isApple(i, j)) {
                    view.getField()[i][j].setFill(Colors.APPLE_COLOR);
                } else {
                    // Используем два разных цвета для поля игры
                    if ((i + j) % 2 == 0) {
                        view.getField()[i][j].
                                setFill(Colors.GAME_FIELD_COLOR_1);
                    } else {
                        view.getField()[i][j].
                                setFill(Colors.GAME_FIELD_COLOR_2);
                    }
                }
            }
        }
    }

    /**
     * set event handler.
     *
     * @param root root where set.
     */
    public void setEventHandlers(Group root) {
        root.setOnKeyPressed(event -> {
            model.setLastPressedKey(event.getCode());
        });
    }
}

