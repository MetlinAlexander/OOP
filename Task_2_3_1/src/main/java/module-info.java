module metlin.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens metlinTask231 to javafx.fxml;
    exports metlinTask231;
    exports metlinTask231.model;
    opens metlinTask231.model to javafx.fxml;
}