package metlinTask231;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import metlinTask231.controller.SnakeGameController;
import metlinTask231.model.SnakeGameModel;
import metlinTask231.view.SnakeGameView;

/**
 * snake game app.
 */
public class SnakeApp extends Application {

    private int width = 1200;
    private int height = 800;
    private int fontSize = 20;


    /**
     * start of game.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws InterruptedException
     */
    @Override
    public void start(Stage stage) throws InterruptedException {
        Group root = new Group();
        SnakeGameView view = new SnakeGameView(root);
        SnakeGameModel model = new SnakeGameModel();
        SnakeGameController controller = new SnakeGameController(model, view);

        Scene scene = new Scene(root, this.width, this.height);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();

        Text snakeLengthText = new Text("Length: " + model.getSnake().getLen());
        snakeLengthText.setFont(Font.
                font("Arial", FontWeight.BOLD, this.fontSize));
        snakeLengthText.setX(10);
        snakeLengthText.setY(20);

        root.getChildren().add(snakeLengthText);

        controller.setEventHandlers(root);

        root.requestFocus();

        Thread move = new Thread(() -> {
            try {
                while (!model.getIsLose().getAcquire()
                        && !model.getIsWin().getAcquire()) {
                    Thread.sleep(model.getDelay());
                    model.moveSnake();
                    controller.updateView();
                    // Обновляем текст длины змеи на каждом шаге
                    snakeLengthText.setText("Length: " + model.getSnake().
                            getLen());
                }
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    if (model.getIsLose().getAcquire()) {
                        alert.setTitle("You Lose");
                    } else {
                        alert.setTitle("You Win");
                    }
                    alert.setHeaderText(null);
                    alert.setContentText("Score: " + model.getSnake().getLen());

                    alert.showAndWait();
                    Platform.exit();
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        move.start();
    }

    /**
     * starting point of app.
     *
     * @param args any useful args
     */
    public static void main(String[] args) {
        launch();
    }
}
