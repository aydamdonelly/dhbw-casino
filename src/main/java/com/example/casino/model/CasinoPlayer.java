package com.example.casino.model;

import com.example.casino.CasinoController;
import com.example.casino.Controller;

public class CasinoPlayer {

    //Standardspecs
    private String name = "Martin";
    private float kontostand = 1000; //startkapital

    //Anbindung Model->Controller
    private CasinoController controller;

    //Singleton
    private static CasinoPlayer player = null;

    private CasinoPlayer(Controller controller) {
        this.controller = (CasinoController) controller;
    }

    private CasinoPlayer() {
        System.out.println("Spieler ohne Params erstellt");
    }

    public static CasinoPlayer getPlayer(Controller controller) {
        if(player == null) {
            player = new CasinoPlayer(controller);
        }
        return player;
    }

    public static CasinoPlayer getPlayer() {
        if(player == null) {
            player = new CasinoPlayer();
        }
        return player;
    }

    //Spiellogik
    public boolean checkBet(float angefragterEinsatz) throws InsufficientFundsException {
        if(angefragterEinsatz > kontostand) {
            throw new InsufficientFundsException(kontostand-angefragterEinsatz);
        }
        return true;
    }

    //Getter u. Setter
    public float getKontostand() {
        return kontostand;
    }

    public void setKontostand(float newkontostand) {
        kontostand = newkontostand;
        controller.updateKonto(newkontostand);
    }
}
