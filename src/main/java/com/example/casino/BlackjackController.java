package com.example.casino;

import com.example.casino.model.blackjackModel.BlackjackLogik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class BlackjackController implements Controller{

    private BlackjackLogik game = new BlackjackLogik(this);

    @FXML
    private BorderPane gameCanvas;

    @FXML
    private TextField einsatzfeldblackjack;
    @FXML
    private Button hitButton;
    @FXML
    private Button stayButton;

    @FXML
    private Button dealButton;

    @FXML
    private Label winningmessage;


    @FXML
    private Label erstekartespieler;

    @FXML
    private Label zweitekartespieler;

    @FXML
    private Label drittekartespieler;

    @FXML
    private Label viertekartespieler;

    @FXML
    private Label fünftekartespieler;

    @FXML
    private Label erstekartedealer;

    @FXML
    private Label zweitekartedealer;

    @FXML
    private Label drittekartedealer;

    @FXML
    private Label viertekartedealer;

    @FXML
    private Label fünftekartedealer;
    private boolean activeGame = true;
    @FXML
    public void initialize() {
        pregameButtons();
    }
    @FXML
    public void hit() {
        game.hit();
    }

    @FXML
    public void stand() {
        game.playDealerHand();
    }

    @FXML
    public void startgame() {
        try {
            game.startGame(convertInput(einsatzfeldblackjack.getText()));
            if(activeGame) {
                ingameButtons();
            }
            setActiveGame(true);
        } catch (NumberFormatException e) {
            einsatzfeldblackjack.setText("");
            einsatzfeldblackjack.setPromptText("Ungültige Eingabe");
        }
    }

    public void drawHand(String symbol, String number, int index, String hand) {
        if(hand == "Dealer") {
            switch(index) {
                case 0:
                    erstekartedealer.setText(symbol+number);
                    erstekartedealer.getStyleClass().add("blackjackcard");
                    break;
                case 1:
                    zweitekartedealer.setText(symbol+number);
                    zweitekartedealer.getStyleClass().add("blackjackcard");
                    break;
                case 2:
                    drittekartedealer.setText(symbol+number);
                    drittekartedealer.getStyleClass().add("blackjackcard");
                    break;
                case 3:
                    viertekartedealer.setText(symbol+number);
                    viertekartedealer.getStyleClass().add("blackjackcard");
                    break;
                case 4:
                    fünftekartedealer.setText(symbol+number);
                    fünftekartedealer.getStyleClass().add("blackjackcard");
                    break;
            }
        }
        else if (hand == "Spieler") {
            switch(index) {
                case 0:
                    erstekartespieler.setText(symbol+number);
                    erstekartespieler.getStyleClass().add("blackjackcard");
                    break;
                case 1:
                    zweitekartespieler.setText(symbol+number);
                    zweitekartespieler.getStyleClass().add("blackjackcard");
                    break;
                case 2:
                    drittekartespieler.setText(symbol+number);
                    drittekartespieler.getStyleClass().add("blackjackcard");
                    break;
                case 3:
                    viertekartespieler.setText(symbol+number);
                    viertekartespieler.getStyleClass().add("blackjackcard");
                    break;
                case 4:
                    fünftekartespieler.setText(symbol+number);
                    fünftekartespieler.getStyleClass().add("blackjackcard");
                    break;
            }

        }
    }

    public void onGameEnd(String result) {
        einsatzfeldblackjack.setText(einsatzfeldblackjack.getText());
        switch(result){
            case "Dealer wins":
                winningmessage.setText("Der Dealer hat gewonnen");
                break;
            case "Player wins":
                winningmessage.setText("Du hast gewonnen");
                break;
            case "Blackjack by Player":
                winningmessage.setText("Blackjack - du hast viel gewonnen");
                break;
            case "Push":
                winningmessage.setText("Pushin P");
                break;
        }
        pregameButtons();
    }

    public void clearboard() {
        winningmessage.setText("");

        erstekartedealer.setText("");
        zweitekartedealer.setText("");
        drittekartedealer.setText("");
        viertekartedealer.setText("");
        fünftekartedealer.setText("");
        erstekartespieler.setText("");
        zweitekartespieler.setText("");
        drittekartespieler.setText("");
        viertekartespieler.setText("");
        fünftekartespieler.setText("");
        //
        erstekartedealer.getStyleClass().remove("blackjackcard");
        zweitekartedealer.getStyleClass().remove("blackjackcard");
        drittekartedealer.getStyleClass().remove("blackjackcard");
        viertekartedealer.getStyleClass().remove("blackjackcard");
        fünftekartedealer.getStyleClass().remove("blackjackcard");
        erstekartespieler.getStyleClass().remove("blackjackcard");
        zweitekartespieler.getStyleClass().remove("blackjackcard");
        drittekartespieler.getStyleClass().remove("blackjackcard");
        viertekartespieler.getStyleClass().remove("blackjackcard");
        fünftekartespieler.getStyleClass().remove("blackjackcard");
    }

    public float convertInput(String inputtext) throws NumberFormatException {
        inputtext = inputtext.replace(",",".");
        return Float.parseFloat(inputtext);
    }

    public void betRejected(Exception e) {
        einsatzfeldblackjack.setText("");
        einsatzfeldblackjack.setPromptText("Gebe so viel an, wie du auch hast.");
        winningmessage.setText(e.getMessage());
    }

    public void pregameButtons() {
        dealButton.setDisable(false);
        hitButton.setDisable(true);
        stayButton.setDisable(true);
    }

    public void ingameButtons() {
        dealButton.setDisable(true);
        hitButton.setDisable(false);
        stayButton.setDisable(false);
    }

    public void setActiveGame (boolean game){
        activeGame = game;
    }
}