package metlin.task_2_3_1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import metlin.task_2_3_1.controller.SnakeGameController;
import metlin.task_2_3_1.model.SnakeGameModel;
import metlin.task_2_3_1.view.SnakeGameView;

// SnakeApp.java
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SnakeApp extends Application {
    @Override
    public void start(Stage stage) throws InterruptedException {
        Group root = new Group();
        SnakeGameView view = new SnakeGameView(root);
        SnakeGameModel model = new SnakeGameModel();
        SnakeGameController controller = new SnakeGameController(model, view);

        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();

        Text snakeLengthText = new Text("Length: " + model.getSnake().getLen());
        snakeLengthText.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Установка шрифта размером 20
        snakeLengthText.setX(10); // Устанавливаем координаты текста
        snakeLengthText.setY(20);

        root.getChildren().add(snakeLengthText); // Добавляем текстовый элемент к корневому узлу

        controller.setEventHandlers(root);

        root.requestFocus();

        Thread move = new Thread(() -> {
            try {
                while (!model.getIsLose().getAcquire() && !model.getIsWin().getAcquire()) {
                    Thread.sleep(model.getDelay());
                    model.moveSnake();
                    controller.updateView();
                    // Обновляем текст длины змеи на каждом шаге
                    snakeLengthText.setText("Length: " + model.getSnake().getLen());
                }
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    if(model.getIsLose().getAcquire()) {
                        alert.setTitle("You Lose");
                    }else{
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

    public static void main(String[] args) {
        launch();
    }
}
