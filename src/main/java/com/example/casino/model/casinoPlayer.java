package com.example.casino.model;

public class casinoPlayer {
    private String name = "Martin";
    private int kontostand = 1000; //startkapital

    private static casinoPlayer player = null;
    private casinoPlayer() {
    }

    public static casinoPlayer getPlayer() {
        if(player == null) {
            player = new casinoPlayer();
        }
        return player;
    }

    public int getKontostand() {
        return kontostand;
    }

    public void setKontostand(int newkontostand) {
        kontostand = newkontostand;
    }
}
