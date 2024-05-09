package com.example.casino.model;

import java.util.Random;

import com.example.casino.Controller;
import com.example.casino.MuenzeController;

public class muenzLogik {

    private static String wette = "Heads";
    private static float wetteinsatz = 0;
    private static CasinoPlayer player = CasinoPlayer.getPlayer();
    private MuenzeController controller;

    public muenzLogik(Controller controller) {
        this.controller = (MuenzeController) controller;
    }

    public static void setBet(String bet, float einsatz) {
        wette = bet;
        wetteinsatz = einsatz;
    }

    public void toss() {
        try {
            player.checkBet(wetteinsatz);
            Random rand = new Random();
            int side = rand.nextInt(0,2);
            if(side==1) {
                endGame("Heads");
            }
            else if(side==0) {
                endGame("Tails");
            }
        } catch (InsufficientFundsException e) {
            controller.betRejected(e);
            System.out.println(e.getMessage());
            wetteinsatz = 0;
        }

    }

    public void endGame(String result) {
        if(result.equals(wette)) {
            player.setKontostand(player.getKontostand()+wetteinsatz);
            wette="";
        }
        else if(wette!="" && !result.equals(wette)){
            player.setKontostand(player.getKontostand()-wetteinsatz);
            wette="";
        }
        else{
            wette="";
        }

        if (controller != null) {
            controller.onGameEnd(result);
        }
    }

}
