module com.example.casino {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.casino to javafx.fxml;
    exports com.example.casino;
    exports com.example.casino.model;
    opens com.example.casino.model to javafx.fxml;
    exports com.example.casino.model.gluecksradModel;
    opens com.example.casino.model.gluecksradModel to javafx.fxml;
    exports com.example.casino.model.slotsModel;
    opens com.example.casino.model.slotsModel to javafx.fxml;
    exports com.example.casino.model.luckyDiceModel;
    opens com.example.casino.model.luckyDiceModel to javafx.fxml;
    exports com.example.casino.model.blackjackModel;
    opens com.example.casino.model.blackjackModel to javafx.fxml;
    exports com.example.casino.model.plinkoModel;
    opens com.example.casino.model.plinkoModel to javafx.fxml;
    exports com.example.casino.model.crashModel;
    opens com.example.casino.model.crashModel to javafx.fxml;
    exports com.example.casino.model.muenzeModel;
    opens com.example.casino.model.muenzeModel to javafx.fxml;
}