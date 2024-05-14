package com.example.casino.model.muenzeModel;

import java.io.FileNotFoundException;
import java.util.Random;

import com.example.casino.Controller;
import com.example.casino.MuenzeController;
import com.example.casino.model.CasinoPlayer;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.NegativeBetException;

public class muenzLogik {

    private static String wette = "Heads";
    private static float wetteinsatz = 0;
    private static CasinoPlayer player = CasinoPlayer.getPlayer();
    private MuenzeController controller;
    private Muenze coin = new Muenze();

    public muenzLogik(Controller controller) {
        this.controller = (MuenzeController) controller;
    }

    public static void setBet(String bet, float einsatz) {
        wette = bet;
        wetteinsatz = einsatz;
    }

    public void bet() {
        try {
            player.checkBet(wetteinsatz);
            endGame(coin.toss());

        } catch (InsufficientFundsException | FileNotFoundException | NegativeBetException e) {
            controller.betRejected(e);
            System.out.println(e.getMessage());
            wetteinsatz = 0;
        }

    }

    public void endGame(String result) throws FileNotFoundException {
        if(result.equals(wette))
        {
            player.setKontostand(player.getKontostand()+wetteinsatz);
            wette="";
        }
        else if(wette!="" && !result.equals(wette))
        {
            player.setKontostand(player.getKontostand()-wetteinsatz);
            wette="";
        }
        else
        {
            wette="";
        }
        controller.onGameEnd(result);
    }

}
