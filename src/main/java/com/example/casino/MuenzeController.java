package com.example.casino;

import javafx.fxml.FXML;
import com.example.casino.model.muenzLogik;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class MuenzeController implements Controller {

    //Anbindung Controller->Model
    private muenzLogik logik = new muenzLogik(this);

    //Images u. Animationen
    @FXML
    private ImageView coinimg;

    //Spielbuttons und andere Elemente aus View
    @FXML
    private Button headsbutton;
    @FXML
    private Button tailsbutton;
    @FXML
    private TextField betAmount;

    //Nicht verfügbare Buttons
    private HashMap<Button, Boolean> blockedButtons = new HashMap<>();

    //MVC Implementation
    @FXML
    public void tossCoin() {
        logik.toss();
    }
    @FXML
    public void headsBet() {
        try {
            muenzLogik.setBet("Heads", convertInput(betAmount.getText()));
            headsbutton.setStyle("-fx-background-color: #864425");
            tailsbutton.setStyle("-fx-background-color: grey");
        } catch(NumberFormatException e) {
            betAmount.setText("");
            betAmount.setPromptText("Gültige Zahl eingeben!");
        }

    }
    @FXML
    public void tailsBet() {
        try {
            muenzLogik.setBet("Tails", convertInput(betAmount.getText()));
            headsbutton.setStyle("-fx-background-color: grey");
            tailsbutton.setStyle("-fx-background-color: #864425");
        } catch(NumberFormatException e) {
            betAmount.setText("");
            betAmount.setPromptText("Gültige Zahl eingeben!");
        }
    }

    //restliche Spiellogik
    public void displaySide(String side) {
        if(side.equals("Heads")) {
            coinimg.setImage(new Image("kopf.png"));
        } else if (side.equals("Tails")) {
            coinimg.setImage(new Image("zahl.png"));
        }
    }

    //Controller-Methoden
    @Override
    public void onGameEnd(String result) {
        displaySide(result);
        tailsbutton.setStyle("-fx-background-color: #864425");
        headsbutton.setStyle("-fx-background-color: #864425");
        betAmount.setText("");
        betAmount.setPromptText("Einsatz");
    }

    public void onGameEnd() {
        tailsbutton.setStyle("-fx-background-color: #864425");
        headsbutton.setStyle("-fx-background-color: #864425");
        betAmount.setText("");
        betAmount.setPromptText("Einsatz");
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
    public float convertInput(String inputtext) throws NumberFormatException{
        inputtext = inputtext.replace(",",".");
        return Float.parseFloat(inputtext);
    }

    @Override
    public void betRejected(Exception e) {
        betAmount.setText("");
        onGameEnd();
        betAmount.setPromptText(e.getMessage());
    }
}
