package com.example.casino;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.example.casino.model.*;

public class CasinoController implements Controller {

    //dynamisches Fenster
    @FXML
    private StackPane contentArea;

    @FXML
    private BorderPane root;

    //kontostand
    @FXML
    public Label kontostand;

    // buttons for blocking
    @FXML
    public Button blackjack_button;

    @FXML
    public Button roulette_button;

    @FXML
    public Button slots_button;

    @FXML
    public Button muenze_button;

    @FXML
    public Button crash_button;



    //Anbindung Controller->Model
    private static CasinoPlayer player;

    //Nicht verfügbare Buttons
    private HashMap<Button, Boolean> blockedButtons = new HashMap<>();


    public CasinoController(){
        player = CasinoPlayer.getPlayer(this);
    }

    private void rootStandardStyle(){
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #47b266, #4e863d);");
    }

    //Spielauswahl
    @FXML
    public void loadBlackjackView() throws IOException {
        Node view = loadFXML("blackjack-view.fxml");
        rootStandardStyle();
        contentArea.getChildren().setAll(view);
    }

    @FXML
    public void loadRouletteView() throws IOException {
        Node view = loadFXML("roulette-view.fxml");
        rootStandardStyle();
        contentArea.getChildren().setAll(view);
    }

    @FXML
    public void loadSlotsView() throws IOException {
        Node view = loadFXML("slots-view.fxml");
        rootStandardStyle();
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
        rootStandardStyle();
        contentArea.getChildren().setAll(view);
    }

    @FXML
    public void loadCrashView() throws IOException {
        Node view = loadFXML("crash-view.fxml");
        root.setStyle("-fx-background-color: linear-gradient(to bottom,  #081844FF 3.6%, #153772 87.6%);");
        // root.setStyle("-fx-background-color: #212544;");
        contentArea.getChildren().setAll(view);
    }
    // background-image: linear-gradient( 86.3deg,  rgba(0,119,182,1) , rgba(8,24,68,1)  );
    @FXML
    public void loadSettings() throws IOException {
        Node view = loadFXML("settings-view.fxml");
        rootStandardStyle();
        contentArea.getChildren().setAll(view);
    }

    private Node loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        return loader.load();
    }

    //Kontodaten
    public void updateKonto(float newkontostand) {
        System.out.println(newkontostand);
        kontostand.setText("Kontostand: €"+newkontostand);
    }

    //Kontodaten
    public void updateKontoThread(float newkontostand) {
        System.out.println(newkontostand);
        Platform.runLater(() -> kontostand.setText("Kontostand: €"+newkontostand));
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