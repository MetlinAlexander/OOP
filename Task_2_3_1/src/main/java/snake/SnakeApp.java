package snake;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static javafx.scene.input.KeyCode.RIGHT;

public class SnakeApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Game game = new Game(root);

        Thread move = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(game.getDelay());
                    game.movement();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        move.start();


        Scene scene = new Scene(root, 1200, 800);

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();

        root.requestFocus();
        game.setEvent();
    }

    public static void main(String[] args) {
        launch();
    }
}