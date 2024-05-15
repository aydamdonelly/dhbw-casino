package com.example.casino.model.plinkoModel;

import com.example.casino.Controller;
import com.example.casino.PlinkoController;
import com.example.casino.model.CasinoPlayer;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.NegativeBetException;
import javafx.scene.control.Label;

import java.util.*;


public class plinkoLogik {




    private static float wetteinsatz = 0;
    public static CasinoPlayer player = CasinoPlayer.getPlayer();
    private static PlinkoController controller;
    private static LinkedList<Tree> list;
    private static float gewinn;
    private static String multiplikator = "";
    private static float einsatz_temp = 0;

    public plinkoLogik(Controller controller) {
        this.controller = (PlinkoController) controller;
    }



    public static void spawnBall (Float einsatz,BinaryTree binaryTree) {
        try {
            player.checkBet(einsatz);

            Label endLabel = binaryTree.getRandomLeafNode(binaryTree.getNode());

            wetteinsatz= einsatz;
            gewinn = gewinn(endLabel.getText(),einsatz);
            einsatz_temp = einsatz;
            multiplikator = endLabel.getText().replace(",",".").replace("x","");
            //endGame(gewinn);
            list = binaryTree.getListe();

        } catch(InsufficientFundsException e) {

            controller.makeInterrupt();
            controller.betRejected(e);

        } catch (NegativeBetException e){
            controller.makeInterrupt();
            controller.betRejected(e);
        }




    }
    public static LinkedList<Tree> getListe(){
        return list;
    }


    public static void endGame() {

         float ergebnis = (player.getKontostand() -wetteinsatz);
            ergebnis += gewinn;
            controller.onGameEnd(String.valueOf(einsatz_temp*Float.parseFloat(multiplikator)));
            player.setKontostandThread(ergebnis);
    }
    private static float gewinn(String txt, float einsatz){
        switch (txt){
            case "5x":
                return (einsatz*5f);
            case "2x":
                return (einsatz *2f);
            case "0,8x":
                return (einsatz * 0.8f) ;
            case "0,1x":
                return (einsatz*0.1f);
                default:
                    System.out.println("Es konnte kein gewinn ermittelt werden");
                    return einsatz;
        }
    }

}
