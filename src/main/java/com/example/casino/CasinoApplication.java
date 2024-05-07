package com.example.casino;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import com.example.casino.model.casinoPlayer;

import java.io.IOException;
// testtete
public class CasinoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CasinoApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        String css = this.getClass().getResource("/styling.css").toExternalForm();
        casinoPlayer player = casinoPlayer.getPlayer("Martin", 0);
        scene.getStylesheets().add(css);
        stage.setTitle("99% hören vor dem Win auf! Bitte weiterspielen!");
        stage.setScene(scene);
        stage.show();
    }

    public void openSettings() throws IOException {
        // FXML-Datei laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
        Parent root = loader.load();

        // Neue Stage erstellen
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Einstellungen");

        // Szene für die neue Stage setzen
        Scene scene = new Scene(root);
        settingsStage.setScene(scene);

        // Stage anzeigen
        settingsStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}