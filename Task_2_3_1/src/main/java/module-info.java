module ru.voshkanov.snake {
    requires javafx.controls;
    requires javafx.fxml;


    opens snake to javafx.fxml;
    exports snake;
}