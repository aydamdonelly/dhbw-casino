package com.example.casino.model;

import java.util.Random;
import com.example.casino.*;

public class muenzLogik {
    private static String wette = "Heads";
    private static casinoPlayer player = casinoPlayer.getPlayer();
    private GameResultListener listener;

    public void setGameResultListener(GameResultListener listener) {
        this.listener = listener;
    }
    public static void setBet(String bet) {
        wette = bet;
    }

    public void toss() {
        Random rand = new Random();
        int side = rand.nextInt(0,2);
        if(side==1)
            {
                endGame("Heads");
            }
        else if(side==0)
            {
                endGame("Tails");
            }
    }

    public void endGame(String result) {
        if(result.equals(wette)) {
            player.setKontostand(player.getKontostand()+2);
            System.out.println("Gewonnen! "+player.getKontostand());
            wette="";
        }
        else if(wette!="" && !result.equals(wette)){
            player.setKontostand(player.getKontostand()-2);
            System.out.println("Verloren! "+player.getKontostand());
            wette="";
        }
        else{
            wette="";
        }

        if (listener != null) {
            listener.onGameEnd(result);
        }
    }

}
