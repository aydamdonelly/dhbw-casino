package com.example.casino;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.HashMap;

import com.example.casino.model.*;

public class CasinoController implements Controller {

    //dynamisches Fenster
    @FXML
    private StackPane contentArea;

    //kontostand
    @FXML
    private Label kontostand;

    //Anbindung Controller->Model
    private static CasinoPlayer player;

    //Nicht verfügbare Buttons
    private HashMap<Button, Boolean> blockedButtons = new HashMap<>();


    public CasinoController(){
        player = CasinoPlayer.getPlayer(this);
    }

    //Spielauswahl
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
    public void loadCrashView() throws IOException {
        Node view = loadFXML("crash-view.fxml");
        contentArea.getChildren().setAll(view);
    }
    @FXML
    public void loadPlinkoView() throws IOException {
        Node view = loadFXML("plinko-view.fxml");
        contentArea.getChildren().setAll(view);
    }
    @FXML
    public void loadGlücksradView() throws IOException {
        Node view = loadFXML("glücksrad-view.fxml");
        contentArea.getChildren().setAll(view);
    }
    @FXML
    public void loadMuenzeView() throws IOException {
        Node view = loadFXML("muenze-view.fxml");
        contentArea.getChildren().setAll(view);
    }
    @FXML
    public void loadSettings() throws IOException {
        Node view = loadFXML("settings-view.fxml");
        contentArea.getChildren().setAll(view);
    }

    private Node loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        return loader.load();
    }

    //Kontodaten
    public void updateKonto(float newkontostand) {
        kontostand.setText("Kontostand: €"+newkontostand);
    }

    public void updateKontoThread(float newKontostand) {
        Platform.runLater(() -> kontostand.setText("Kontostand: €" + newKontostand));
    }

    //Controller-Methoden
    @Override
    public void onGameEnd(String result) {}

    @Override
    public void setBlocked(Button button) {
        button.setStyle("-fx-background-color: #a93b3b;");
        blockedButtons.put(button, true);
    }

    @Override
    public void unblockButton(Button button) {
        button.setStyle("-fx-background-color: #864425;");
        blockedButtons.remove(button);
    }

    @Override
    public void betRejected(Exception e) {}

    @Override
    public float convertInput(String inputtext) throws NumberFormatException{
        inputtext = inputtext.replace(",",".");
        return Float.parseFloat(inputtext);
    }
}