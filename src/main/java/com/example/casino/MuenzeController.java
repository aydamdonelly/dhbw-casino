package com.example.casino;

import com.example.casino.model.GameResultListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import com.example.casino.model.muenzLogik;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MuenzeController implements GameResultListener {

    private static muenzLogik logik = new muenzLogik();

    @FXML
    private ImageView coinimg;
    @FXML
    private Button headsbutton;
    @FXML
    private Button tailsbutton;

    @FXML
    public void tossCoin() {
        logik.toss();
    }
    public MuenzeController() {
        logik.setGameResultListener(this);
    }
    @FXML
    public void headsBet() {
        muenzLogik.setBet("Heads");
        headsbutton.setStyle("-fx-background-color: #864425");
        tailsbutton.setStyle("-fx-background-color: grey");
    }
    @FXML
    public void tailsBet() {
        muenzLogik.setBet("Tails");
        tailsbutton.setStyle("-fx-background-color: #864425");
        headsbutton.setStyle("-fx-background-color: grey");
    }

    public void reset() {
        tailsbutton.setStyle("-fx-background-color: #864425");
        headsbutton.setStyle("-fx-background-color: #864425");
    }

    public void displaySide(String side) {
        if(side.equals("Heads")) {
            coinimg.setImage(new Image("kopf.png"));
        } else if (side.equals("Tails")) {
            coinimg.setImage(new Image("zahl.png"));
        }
    }

    private Node loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        return loader.load();
    }

    public void onGameEnd(String result) {
        displaySide(result);
        reset();
    }
}
