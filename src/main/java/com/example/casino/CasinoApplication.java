package com.example.casino;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.casino.model.CasinoPlayer;

import java.io.IOException;

public class CasinoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CasinoApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 1200);
        String css = this.getClass().getResource("/styling.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("99% hören vor dem Win auf! Bitte weiterspielen!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}