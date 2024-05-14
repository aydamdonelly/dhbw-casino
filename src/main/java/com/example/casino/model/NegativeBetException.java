package com.example.casino.model;

public class NegativeBetException extends Exception {

    public NegativeBetException() {
        super("Der Wettbetrag darf nicht unter 0 liegen!");
    }
}
