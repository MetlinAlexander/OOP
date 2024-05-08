module metlin.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens metlinsnake to javafx.fxml;
    exports metlinsnake;
    exports metlinsnake.model;
    opens metlinsnake.model to javafx.fxml;
}