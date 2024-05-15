package com.example.casino;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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

    // import buttons from top
    @FXML
    Button luckyDice_button;

    @FXML
    Button blackjack_button;

    @FXML
    Button slots_button;

    @FXML
    Button muenze_button;

    @FXML
    Button crash_button;

    @FXML
    Button gluecksrad_button;

    @FXML
    Button plinko_button;

    @FXML
    private BorderPane root;

//    public CasinoController(){
//        // player = CasinoPlayer.getPlayer(this);
//    }

    public void initialize(){
        player = CasinoPlayer.getPlayer(this);
    }


    private void rootStandardStyle(){
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #47b266, #4e863d);");
    }

    //Spielauswahl
    @FXML
    public void loadBlackjackView() throws IOException {
        Node view = loadFXML("blackjack-view.fxml");
        contentArea.getChildren().setAll(view);
        root.setStyle("-fx-background-color: linear-gradient(to bottom,  #36772c 3.6%, #68b22a 87.6%);");
    }

    @FXML
    public void loadLuckyDiceView() throws IOException {
        Node view = loadFXML("luckyDice-view.fxml");
        contentArea.getChildren().setAll(view);
        root.setStyle("-fx-background-color: linear-gradient(to bottom,  #7a4423 3.6%, #a64f20 87.6%);");
    }

    @FXML
    public void loadSlotsView() throws IOException {
        Node view = loadFXML("slots-view.fxml");
        contentArea.getChildren().setAll(view);
        root.setStyle("-fx-background-color: linear-gradient(to bottom,  #9f2929 3.6%, #c91414 87.6%);");
    }
    @FXML
    public void loadCrashView() throws IOException {
        Node view = loadFXML("crash-view.fxml");
        contentArea.getChildren().setAll(view);
        root.setStyle("-fx-background-color: linear-gradient(to bottom,  #081844FF 3.6%, #153772 87.6%);");
    }
    @FXML
    public void loadPlinkoView() throws IOException {
        Node view = loadFXML("plinko-view.fxml");
        contentArea.getChildren().setAll(view);
        root.setStyle("-fx-background-color: linear-gradient(to bottom,  #2b8a79 3.6%, #2bb284 87.6%);");
    }
    @FXML
    public void loadGlücksradView() throws IOException {
        Node view = loadFXML("glücksrad-view.fxml");
        contentArea.getChildren().setAll(view);
        root.setStyle("-fx-background-color: linear-gradient(to bottom,  #917e34 3.6%, #bbb81c 87.6%);");
    }
    @FXML
    public void loadMuenzeView() throws IOException {
        Node view = loadFXML("muenze-view.fxml");
        contentArea.getChildren().setAll(view);
        rootStandardStyle();
    }

    private Node loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        return loader.load();
    }

    public void blockButtonsTop(){
        blackjack_button.setDisable(true);
        luckyDice_button.setDisable(true);
        slots_button.setDisable(true);
        muenze_button.setDisable(true);
        crash_button.setDisable(true);
        gluecksrad_button.setDisable(true);
        plinko_button.setDisable(true);
    }

    public void unblockButtonsTop(){
        blackjack_button.setDisable(false);
        luckyDice_button.setDisable(false);
        slots_button.setDisable(false);
        muenze_button.setDisable(false);
        crash_button.setDisable(false);
        gluecksrad_button.setDisable(false);
        plinko_button.setDisable(false);
    }


    //Kontodaten
    public void updateKonto(String newkontostand) {
        kontostand.setText("Kontostand: €"+newkontostand);
    }

    public void updateKontoThread(String newKontostand) {
        Platform.runLater(() -> kontostand.setText("Kontostand: €" + newKontostand));
    }

    //Controller-Methoden
    @Override
    public void onGameEnd(String result) {}

    @Override
    public void betRejected(Exception e) {}

    @Override
    public float convertInput(String inputtext) throws NumberFormatException{
        inputtext = inputtext.replace(",",".");
        return Float.parseFloat(inputtext);
    }
}