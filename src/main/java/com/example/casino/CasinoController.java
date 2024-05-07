package com.example.casino;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class CasinoController {

    @FXML
    private StackPane contentArea;
    @FXML
    public void loadBlackjackView() throws IOException {
        Node view = loadFXML("blackjack-view.fxml");
        contentArea.getChildren().setAll(view);
    }

    @FXML
    public void loadRouletteView() throws IOException {
        Node view = loadFXML("roulette-view.fxml");
        contentArea.getChildren().setAll(view);
    }

    @FXML
    public void loadSlotsView() throws IOException {
        Node view = loadFXML("slots-view.fxml");
        contentArea.getChildren().setAll(view);
    }
    @FXML
    public void loadMuenzeView() throws IOException {
        Node view = loadFXML("muenze-view.fxml");
        contentArea.getChildren().setAll(view);
    }
    public void loadSettings() throws IOException {
        Node view = loadFXML("settings-view.fxml");
        contentArea.getChildren().setAll(view);
    }

    public void submitName() throws IOException {
        System.out.println("test");
    }

    public void increaseKontostand() throws IOException {
        System.out.println("test");
    }

    private Node loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        return loader.load();
    }
}