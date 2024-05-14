package com.example.casino.model.luckyDiceModel;

public class PayoutCalculator {
    public float calculatePayoutFactor(int total) {
        switch (total) {
            case 2:
                return 20.0f;
            case 12:
                return 20.0f; // Das ist der höchste Faktor, da dieser am seltesten ist
            case 3:
                return 14.0f;
            case 11:
                return 14.0f;
            case 4:
                return 12.0f;
            case 10:
                return 12.0f;
            case 5:
                return 10.0f;
            case 9:
                return 10.0f;
            case 6:
                return 4.0f;
            case 8:
                return 4.0f;
            case 7:
                return 2.0f; // Niedrigster Faktor, da die Wahrscheinlichkeit am höchsten ist für diese Summe
            default:
                return 1.0f; // wird nie gebraucht
        }
    }
}
