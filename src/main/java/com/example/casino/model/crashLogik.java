package com.example.casino.model;

import com.example.casino.CrashController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;


public class crashLogik {
    private CrashController controller;

    private static CasinoPlayer player = CasinoPlayer.getPlayer();

    public crashLogik(CrashController controller) throws FileNotFoundException {
        this.controller = (CrashController) controller;
    }

    public List<Float> scores = new ArrayList<>();

    public boolean gameRunning;

    private Thread gameThread;

    private float currentMultiplier;

    // write with a getter and private
    public float currentBet = 0f;

    public MergeSortDec sorting = new MergeSortDec();

    private FileInputStream imginputstream = new FileInputStream("src/main/resources/img/rocket.png");
    private Image img = new Image(imginputstream);
    private ImageView player_img = new ImageView(img);

    private void clearPane(){
        controller.crsh_pane.getChildren().clear();
    }

    private void clearPaneThread(){
        Platform.runLater(() -> controller.crsh_pane.getChildren().clear());
    }

    private void save_score(){
        try{
            Path scoreFile = Paths.get("src/main/resources/saves/crash_score.txt");
            Files.write(scoreFile, new byte[0]);
            if (!Files.exists(scoreFile))
                Files.createFile(scoreFile);


            BufferedWriter meinWriter =
                    Files.newBufferedWriter(scoreFile, StandardOpenOption.APPEND);

            scores = sorting.mergeSort(scores);
            meinWriter.write(Float.toString(scores.getFirst()));
            meinWriter.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public Float load_score(){
        try{
            Path scoreFile = Paths.get("crash_score.txt");

            if (!Files.exists(scoreFile))
                Files.createFile(scoreFile);

            BufferedReader meinReader = Files.newBufferedReader(scoreFile);

            String zeile = meinReader.readLine();

            if (zeile != null){
                scores.add(Float.parseFloat(zeile));
                return Float.parseFloat(zeile);
            }
            return 0f;

        } catch (Exception e){
        }
        return 0f;
    }

    public void displayHighScore(){
        scores = sorting.mergeSort(scores);
        String text = "Highscore: " + scores.get(0) + "x";
        controller.crsh_highscore.setText(text);
    }

    public void startGame(){
        // reset multiplier
        currentMultiplier = 1f;
        load_score();

        /*
        if (!scores.isEmpty()){
            System.out.println("displaying score");
            displayHighScore();
        }
        scores.add(0f);
        displayHighScore();
        */

        // clear the pane
        clearPane();
        // open a thread
        gameThread = new Thread( () -> {
            try {
                runGame();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        gameThread.start();
    }

    private float calcResult(float betAmount, float factor){
        float currentKontostand = player.getKontostand();
        return currentKontostand - betAmount + betAmount * factor;
    }

    public void endGame(){
        // ending the score going up and up
        try{
            // ending the game thread
            gameThread.interrupt();

            // calculating the Account Balance
            player.setKontostand(calcResult(currentBet, currentMultiplier));

            // adding to the scores of the current session
            scores.add(currentMultiplier);
            displayHighScore();
            save_score();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void setNewMultiplier(){
        Random random = new Random();
        float randomNumber = 0.005f + (random.nextFloat() * 0.005f);

        currentMultiplier = currentMultiplier + randomNumber;
        currentMultiplier = Math.round(currentMultiplier * 100) / 100.0f;
    }

    private void explodeRocket(ImageView player_img) throws FileNotFoundException{
        // getting the image of the explosion
        FileInputStream explosionInputStream = new FileInputStream("src/main/resources/img/explosion.png");
        Image explosionImg = new Image(explosionInputStream);
        ImageView explosion_view = new ImageView(explosionImg);

        // setting the dim for the explosion
        explosion_view.setFitHeight(120);
        explosion_view.setFitWidth(120);

        // placing the explosion where the rocket is
        explosion_view.setTranslateY(player_img.getTranslateY());
        explosion_view.setTranslateX(player_img.getTranslateX());

        // deleting the rocket
        clearPaneThread();

        // showing the explosion on the pane (within the thread function)
        Platform.runLater(() -> controller.crsh_pane.getChildren().add(explosion_view));
    }

    private boolean checkForCrash(){
        // letting the rocket crash at random
        // if the multiplier is higher than 2.5 the rocket crashes also
        Random random = new Random();
        int bound = 100; // random chance 50 = 1:50 it crashes
        int randomNumber = random.nextInt(bound);
        return ((randomNumber == 1) || (currentMultiplier > 2.5f));
    }

    private void doCrashing() throws FileNotFoundException {
        currentMultiplier = 0;
        String shown_text = currentMultiplier + "x";
        Platform.runLater(() -> controller.crsh_factor_text.setText(shown_text));
        Platform.runLater(() -> controller.crsh_toggle_start_button.setText("Start Rocket!"));
        player.setKontostandThread(calcResult(currentBet, currentMultiplier));
        explodeRocket(player_img);
    }

    public void placeInitRocket(){
        // setting an placing the img
        player_img.setFitHeight(120);
        player_img.setFitWidth(120);
        player_img.setTranslateY(580);
        player_img.setTranslateX(525);

        // show the initial rocket on the pane
        Platform.runLater(() -> controller.crsh_pane.getChildren().add(player_img));
    }

    private void tickGame(){
        // moving multiplier and moving the rocket
        setNewMultiplier();
        String shown_text = currentMultiplier + "x";
        Platform.runLater(() -> controller.crsh_factor_text.setText(shown_text));

        // moving the rocket
        Platform.runLater(() -> player_img.setTranslateY(player_img.getTranslateY() - 2 * currentMultiplier));
    }

    private void runGame() throws RuntimeException, FileNotFoundException {

        // placing the rocket on init
        placeInitRocket();

        try {
            while (true) {
                // do random rocket crashing
                // guard clause
                if  (checkForCrash()){
                    doCrashing();
                    // ending the thread for a new game round
                    throw new InterruptedException();
                }
                // new multiplier and moves the rocket up
                tickGame();

                // setting the time between each tick
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            gameRunning = false;
        }
    }

}
