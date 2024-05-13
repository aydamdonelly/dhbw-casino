package com.example.casino.model;

import com.example.casino.Controller;
import com.example.casino.GluecksradController;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GluecksradLogik {

    private static GluecksradController controller;
    private int randomAngle;

    private static CasinoPlayer player = CasinoPlayer.getPlayer();
    public GluecksradLogik(Controller controller) {
        // quick init of the gewinn array


        GluecksradLogik.controller = (GluecksradController) controller;


    }

    public void dreheRad() {
        // um zufälligen Winkel rotieren
        randomAngle = (int) (Math.random() * 360 + 2880);
        int currentAngle = 0;
        double speed = 1.0;

        while (currentAngle < randomAngle) {
            final int finalCurrentAngle = currentAngle;
            Platform.runLater(() -> controller.getRadGroup().setRotate(finalCurrentAngle));
            currentAngle += 2;
            // Speed mit steigendem currentAngle erhöhen, um slow-down Effekt zu modellieren
            speed += 0.01 * Math.pow(currentAngle*0.001,1.0000000000001f);// 0.01;
            try {
                Thread.sleep((int) speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        getGewinnausgabe(randomAngle);

    }

    public void getGewinnausgabe(int angle) {
        // hardcoded but was faster
        String gewinn_anzeige;

        angle = (angle + 90) % 360; // adjusting to the pointer angle
        if (angle > 0 && angle <= 36){
            gewinn_anzeige = "you get 500€";
            float newKontostand = player.getKontostand() + 500;
            player.setKontostandThread(newKontostand);
        } else if (angle > 36 && angle <= 72) {
            gewinn_anzeige = getKomplimente();
        } else if (angle > 72 && angle <= 108) {
            gewinn_anzeige = "2x Spins";
        } else if (angle > 108 && angle <= 144) {
            gewinn_anzeige = "Free Aris";
        } else if (angle > 144 && angle <= 180) {
            gewinn_anzeige = "XXL Taco for Freee!!!!";
        } else if (angle > 180 && angle <= 216) {
            gewinn_anzeige = "Kontostand 2x";
            float newKontostand = player.getKontostand() * 2;
            player.setKontostandThread(newKontostand);
        } else if (angle > 216 && angle <= 252) {
            gewinn_anzeige = "Vegas babbyyyy";
        } else if (angle > 252 && angle <= 288) {
            gewinn_anzeige = "-200 Cash";
            float newKontostand = player.getKontostand() - 200;
            player.setKontostandThread(newKontostand);
        } else if (angle > 288 && angle <= 324) {
            gewinn_anzeige = "Die Drinks für dich und deine Freunde gehen heute auf's Haus.";
        } else {
            gewinn_anzeige = "Konto Leer :(( Zahle mehr Geld ein.";
            player.setKontostandThread(0);
        }

        Platform.runLater(() -> controller.introText.setText(gewinn_anzeige));
    }

    public String getKomplimente() {
        Random rand = new Random();

        ArrayList<String> komplimente = new ArrayList<>();
        // adding some compliments
        komplimente.add("Du siehst heute zum Anbeißen aus :)");
        komplimente.add("Deine Haare glänzen wie Gold! <3");
        komplimente.add("Du bist ein sehr guter Casinospieler!");
        komplimente.add("Dein Outfit heute sieht super aus!! :))");
        komplimente.add("Du bist sehr klug und weise.");
        komplimente.add("Du bringst die Sonne zum Strahlen, egal wie trüb der Tag ist.");
        komplimente.add("Deine Anwesenheit macht alles besser.");
        komplimente.add("Du machst einen großartigen Job!");
        komplimente.add("Dein Sinn für Humor ist unglaublich.");

        return komplimente.get(rand.nextInt(komplimente.size()));

    }
}
