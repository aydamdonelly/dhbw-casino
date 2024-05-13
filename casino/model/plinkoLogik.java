package com.example.casino.model;

import com.example.casino.Controller;
import com.example.casino.PlinkoController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.util.*;


public class plinkoLogik {




    private static float wetteinsatz = 0;
    public static CasinoPlayer player = CasinoPlayer.getPlayer();
    private static PlinkoController controller;
    private static LinkedList<Tree> list;

    public plinkoLogik(Controller controller) {
        this.controller = (PlinkoController) controller;
    }



    public static void spawnBall (Float einsatz,BinaryTree binaryTree) {
        try {
            player.checkBet(einsatz);
            //controller.clearMessag‚e();

       /* try {
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
        }*/
            System.out.print("BinaryTRee:  ");
            System.out.println(binaryTree.getNode().getObj());
            Label endLabel = binaryTree.getRandomLeafNode(binaryTree.getNode());
            System.out.println(binaryTree.getNode().getRight().getObj());
            wetteinsatz= einsatz;
            float gewinn = gewinn(endLabel.getText(),einsatz);
            endGame(gewinn);
            System.out.println(gewinn);
            System.out.println(wetteinsatz);
            System.out.println(endLabel);
            System.out.println(endLabel.getClass());
            System.out.println(endLabel.getText());
            System.out.println(einsatz);
            list = binaryTree.getListe();
            for (Tree t : list){
                System.out.println(t.getObj());
            }
        } catch(InsufficientFundsException e) {

            controller.makeInterrupt();
            controller.betRejected(e);

        }




    }
    public static LinkedList<Tree> getListe(){
        return list;
    }


    public static void endGame(float gewinn) {
         float ergebnis = (player.getKontostand() -wetteinsatz);
            ergebnis += gewinn;

            player.setKontostandThread(ergebnis);
        if (controller != null) {
            controller.onGameEnd(String.valueOf(ergebnis));
        }
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
                    System.out.println("es konnte kein gewinn ermittelt werden");
                    return einsatz;
        }
    }

}
