package com.example.casino.model.blackjackModel;
public enum Suit {
    CLUBS("♣"),
    DIAMONDS("♦"),
    HEARTS("♥"),
    SPADES("♠");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}