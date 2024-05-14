package com.example.casino.model.slotsModel;

import com.example.casino.Controller;
import com.example.casino.SlotController;
import com.example.casino.model.CasinoPlayer;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.NegativeBetException;
import javafx.scene.Group;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SlotsLogik {
    private static float wetteinsatz = 0;

    private static CasinoPlayer player = CasinoPlayer.getPlayer();
    private SlotController controller;
    public Group n;

    public static final List<Integer> l1 = List.of(1, 2, 2, 3, 3, 4, 4);
    public static final List<Integer> l2 = List.of(1, 2, 2, 3, 3, 4, 4);
    public static final List<Integer> l3 = List.of(1, 2, 2, 3, 3, 4, 4);

    public SlotsLogik(Controller controller) {
        this.controller = (SlotController) controller;
    }

    public void spin(float wetteinsatz) {
        try {
            player.checkBet(wetteinsatz);
            player.setKontostand(player.getKontostand() - wetteinsatz);
            Random random = new Random();
            int random1 = l1.get(random.nextInt(7));
            int random2 = l2.get(random.nextInt(7));
            int random3 = l3.get(random.nextInt(7));
            controller.symboleBerechnet(random1, random2, random3);

            if (random1 == 1 && random2 == 1 && random3 == 1) {
                player.setKontostand(player.getKontostand()+wetteinsatz*2);
            } else if (random1 == random2 && random2 == random3) { // 3 equal
                player.setKontostand((float) (player.getKontostand()+wetteinsatz*1.6));
            } else if (random1 == random2 || random2 == random3 || random1 == random3) { // 2 equal
                player.setKontostand((float) (player.getKontostand()+wetteinsatz*1.1));
            }
        } catch (InsufficientFundsException e) {
            controller.betRejected(e);
        } catch (NegativeBetException e) {
            controller.betRejected(e);
        }
    }

    public static float modifyBet(float change) {
        wetteinsatz += change;
        return wetteinsatz;
    }

}
