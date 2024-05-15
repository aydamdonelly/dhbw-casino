package com.example.casino;

import com.example.casino.model.CasinoPlayer;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.crashModel.CrashLogik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class CrashController implements Controller{

    @FXML
    public Label crsh_factor_text;

    @FXML
    public Button crsh_toggle_start_button;

    @FXML
    public Pane crsh_pane;

    @FXML
    public TextField crshBetAmount;

    @FXML
    public Label crsh_highscore;

    @FXML
    private Label crashmessager;

    //Anbindung Controller->Model
    private CrashLogik logik = new CrashLogik(this);

    private static CasinoPlayer player = CasinoPlayer.getPlayer();

    public CrashController() throws FileNotFoundException {
        // file not found exception because of the init of a picture in the logic constructor
    }


    public void initialize(){
        // executed after all the fxml is loaded
        // if the constructor was used this would not work
        Float score = logik.scoreHandler.load_num(logik.getScorePath());
        System.out.println(score);
        displayHighScore(score);
        logik.placeInitRocket();
    }

    public void displayHighScore(Float scores){
        String text = "Highscore: " + scores + "x";
        crsh_highscore.setText(text);
        System.out.println(crsh_highscore.getText());
    }

    @FXML
    protected void onCrshButtonClick() {
        // checking the bet
        try{
            float betAmount = Integer.parseInt(crshBetAmount.getText());
            // checking if the user hase enought money
            player.checkBet(betAmount);

            // making it the current bet
            logik.currentBet = betAmount;

            if (logik.gameRunning){
                crsh_toggle_start_button.setText("Start Rocket!");
                System.out.println("crash: round ended");
                logik.endGame();
                logik.gameRunning = false;
                unblockElements();

            }else{
                logik.gameRunning = true;
                System.out.println("crash: round started");
                crsh_toggle_start_button.setText("Stop Rocket!");
                logik.startGame();
                blockElements();
            }

        } catch (InsufficientFundsException e){
            logik.currentBet = 0f;
            System.out.println("The player does not have enought money!");
        } catch (Exception e){
            logik.currentBet = 0f;
            System.out.println("the bet placed is not a number! ");
        }
    }

    // some stuff to implement

    public void unblockElements(){
        // blocking the buttons
        unblockButton(player.controller.blackjack_button);
        unblockButton(player.controller.luckyDice_button);
        unblockButton(player.controller.slots_button);
        unblockButton(player.controller.muenze_button);
        unblockButton(player.controller.crash_button);
        unblockButton(player.controller.gluecksrad_button);
        unblockButton(player.controller.plinko_button);
        crshBetAmount.setDisable(false);
    }

    public void blockElements(){
        // blocking the buttons
        setBlocked(player.controller.blackjack_button);
        setBlocked(player.controller.luckyDice_button);
        setBlocked(player.controller.slots_button);
        setBlocked(player.controller.muenze_button);
        setBlocked(player.controller.crash_button);
        setBlocked(player.controller.gluecksrad_button);
        setBlocked(player.controller.plinko_button);
        crshBetAmount.setDisable(true);
    }

    @Override
    public void onGameEnd(String result) {
        result = result.replace(".", ",");
        if(!result.equals("Crashed")) {
            crsh_factor_text.setText("Du hast "+result+"0€ gewonnen! Spiel weiter!");
        } else {
            crsh_factor_text.setText("Die Rakete ist abgestürzt, probiers nochmal!");
        }
    }

    public void setBlocked(Button button) {
        button.setStyle("-fx-background-color: #a93b3b;");
        button.setDisable(true);
    }

    public void unblockButton(Button button) {
        button.setStyle("-fx-background-color: #864425;");
        button.setDisable(false);
    }

    @Override
    public float convertInput(String inputtext) throws NumberFormatException {
        return 0;
    }

    @Override
    public void betRejected(Exception e) {

    }
}