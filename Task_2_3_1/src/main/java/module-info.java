module metlin.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens metlin.task_2_3_1 to javafx.fxml;
    exports metlin.task_2_3_1;
    exports metlin.task_2_3_1.model;
    opens metlin.task_2_3_1.model to javafx.fxml;
}