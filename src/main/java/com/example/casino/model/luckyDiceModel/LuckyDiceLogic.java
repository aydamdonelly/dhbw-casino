package com.example.casino.model.luckyDiceModel;

import com.example.casino.Controller;
import com.example.casino.LuckyDiceController;
import com.example.casino.model.CasinoPlayer;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.NegativeBetException;

public class LuckyDiceLogic {

    private static int guessedTotal = 7; // Standardannahme für die Summe von zwei Würfeln
    private static float betAmount = 0;
    private static CasinoPlayer player = CasinoPlayer.getPlayer();
    private Controller.LuckyDiceController controller;
    private PayoutCalculator payoutCalculator = new PayoutCalculator(); // Verwendung der PayoutCalculator Klasse

    private Dice dice1 = new Dice(); // Erzeugen eines Objektes der Würfelklasse
    private Dice dice2 = new Dice();
    private int dice1Value;
    private int dice2Value;

    public LuckyDiceLogic(Controller.LuckyDiceController controller) {
        this.controller = controller;
    }

    public LuckyDiceLogic(LuckyDiceController luckyDiceController) {
    }

    public static void setBet(int total, float amount) {
        guessedTotal = total;
        betAmount = amount;
    }

    public void rollDice() throws InsufficientFundsException, NegativeBetException {
        player.checkBet(betAmount);
        dice1Value = dice1.roll();
        dice2Value = dice2.roll();
        int total = dice1Value + dice2Value;
        if (total == guessedTotal) {
            endGame("Win", payoutCalculator.calculatePayoutFactor(guessedTotal));
        } else {
            endGame("Lose", 0);
        }
    }

    public int getDice1Value() {
        return dice1Value;
    }

    public int getDice2Value() {
        return dice2Value;
    }

    public void endGame(String result, float factor) {
        if ("Win".equals(result)) {
            player.setKontostand(player.getKontostand() + betAmount * factor);
        } else {
            player.setKontostand(player.getKontostand() - betAmount);
        }
        if (controller != null) {
            controller.onGameEnd(result);
        }
    }
}
