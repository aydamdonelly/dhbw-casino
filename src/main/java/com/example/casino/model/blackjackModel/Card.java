package com.example.casino.model.blackjackModel;


public class Card {
    private Rank rank;
    private Suit suit;
    private boolean faceDown;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.faceDown = false;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public int getValue() {
        if (rank == Rank.ACE) {
            return 11;
        } else {
            return rank.getValue();
        }
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

}
