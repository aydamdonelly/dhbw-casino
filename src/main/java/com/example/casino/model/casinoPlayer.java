package com.example.casino.model;

public class casinoPlayer {
    private static String name = "Martin";
    private static int kontostand = 0;
    public casinoPlayer(String name, int kontostand) {
        this.kontostand = kontostand;
        this.name = name;
    }

    public int getKontostand() {
        return kontostand;
    }

    public void setKontostand(int newkontostand) {
        kontostand = newkontostand;
    }
}
