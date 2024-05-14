package com.example.casino;

import com.example.casino.model.gluecksradModel.GluecksradLogik;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;


public class GluecksradController implements Controller {

    //Anbindung Controller->Model
    private GluecksradLogik logik = new GluecksradLogik(this);

    // Rad das sich drehen soll (Circle mit Arcs drauf)
    // Kreis und Kreisausschnitte (Arcs) sind in Group gemeinsam als Einheit gespeichert
    @FXML
    private Group radGroup;

    // buttons und andere Spielelemente
    @FXML
    public Button startRad;
    @FXML
    public Label introText;

    //Nicht verfügbare Buttons
    private HashMap<Button, Boolean> blockedButtons = new HashMap<>();

    //MVC Implementierung
    @FXML
    public void onStartRadPressed () {

        if (!logik.canSpin()){

            return;
        }



        introText.setText("Viel Glück...");

        logik.player.controller.blockButtonsTop();
        startRad.setDisable(true);
        new Thread( () -> logik.dreheRad()).start();
    }

    //Controller-Methoden
    @Override
    public void onGameEnd(String result) {
        startRad.setStyle("-fx-background-color: #864425");
        // introText.setText(logik.winErmitteln());
    }

    public void onGameEnd() {
        startRad.setStyle("-fx-background-color: #864425");
        // introText.setText(logik.winErmitteln());
    }

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
    public float convertInput(String inputtext) throws NumberFormatException {
        return 0.0f;
    }

    @Override
    public void betRejected(Exception e) {
    }
    // getter für radGroup, um in Logikklasse Methoden darauf anzuwenden
    public Group getRadGroup() {
        return radGroup;
    }
}
