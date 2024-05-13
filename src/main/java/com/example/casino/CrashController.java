package com.example.casino;

import com.example.casino.model.CasinoPlayer;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.crashLogik;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

    //Nicht verfügbare Buttons
    private HashMap<Button, Boolean> blockedButtons = new HashMap<>();

    //Anbindung Controller->Model
    private crashLogik logik = new crashLogik(this);

    private static CasinoPlayer player = CasinoPlayer.getPlayer();

    public CrashController() throws FileNotFoundException {
        // file not found exception because of the init of a picture in the logic constructor
    }


    public void initialize(){
        // executed after all the fxml is loaded
        // if the constructor was used this would not work
        Float score = logik.load_score();
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



            }else{
                logik.gameRunning = true;
                System.out.println("crash: round started");
                crsh_toggle_start_button.setText("Stop Rocket!");
                logik.startGame();

                player.controller.blackjack_button.setDisable(true);
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

    public void unblockButtons(){
        // blocking the buttons
        unblockButton(player.controller.blackjack_button);
        unblockButton(player.controller.roulette_button);
        unblockButton(player.controller.slots_button);
        unblockButton(player.controller.muenze_button);
        unblockButton(player.controller.crash_button);
    }

    public void blockButtons(){
        // blocking the buttons
        setBlocked(player.controller.blackjack_button);
        setBlocked(player.controller.roulette_button);
        setBlocked(player.controller.slots_button);
        setBlocked(player.controller.muenze_button);
        setBlocked(player.controller.crash_button);
    }

    @Override
    public void onGameEnd(String result) {

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
        return 0;
    }

    @Override
    public void betRejected(Exception e) {

    }
}
