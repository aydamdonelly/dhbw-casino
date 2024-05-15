package com.example.casino;

import javafx.fxml.FXML;
import com.example.casino.model.muenzeModel.muenzLogik;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    @FXML
    private Label coinmessager;

    //MVC Implementation
    @FXML
    public void tossCoin() {
        logik.bet();
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
    public void displaySide(String side) throws FileNotFoundException {
        FileInputStream kopfStream = new FileInputStream("src/main/resources/img/muenze/kopf.png");
        FileInputStream zahlStream = new FileInputStream("src/main/resources/img/muenze/zahl.png");

        if(side.equals("Heads")) {
            coinimg.setImage(new Image(kopfStream));
        } else if (side.equals("Tails")) {
            coinimg.setImage(new Image(zahlStream));
        }
    }

    //Controller-Methoden
    @Override
    public void onGameEnd(String result) throws FileNotFoundException {
        displaySide(result);
        tailsbutton.setStyle("-fx-background-color: #864425");
        headsbutton.setStyle("-fx-background-color: #864425");
        coinmessager.setText("Das Ergebnis ist: "+result);
        betAmount.setText("");
        betAmount.setPromptText("Einsatz");
        // outcomemuenze.setText("Die geworfene Seite ist: "+result+"!");
    }

    public void onGameEnd() {
        tailsbutton.setStyle("-fx-background-color: #864425");
        headsbutton.setStyle("-fx-background-color: #864425");
        betAmount.setText("");
        betAmount.setPromptText("Einsatz");
       // outcomemuenze.setText("Deine Wette wurde nicht verarbeitet, wette nochmal.");
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