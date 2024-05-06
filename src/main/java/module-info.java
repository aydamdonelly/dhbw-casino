module com.example.casino {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.casino to javafx.fxml;
    exports com.example.casino;
    exports com.example.casino.model;
    opens com.example.casino.model to javafx.fxml;
}