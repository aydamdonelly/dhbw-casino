package com.example.casino.model;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super();
    }
    public InsufficientFundsException(float fehlbetrag) {
        super("Für diese Wette brauchst du noch "+fehlbetrag+"0€! Zahl mehr Geld ein und spiele immer weiter!");
    }
}
