package com.example.casino.model.blackjackModel;

import java.util.ArrayList;

public class Hand {

    private String hand;

    private int gesamtwert;

    private int aceCount;

    private int kartenanzahl = -1;

    private ArrayList<Card> karten = new ArrayList<Card>();
    public Hand(String hand) {
        this.hand = hand;
    }

    public Card searchFaceDown() {
        for(Card card : karten) {
            if(card.isFaceDown()) {
                card.setFaceDown(false);
                updateSum(card);
                return card;
            }
        }
        return null;
    }

    public void drawCard(Card card) {
        karten.add(card);
        kartenanzahl++;
    }

    public void resetHand() {
        karten.clear();
        kartenanzahl=-1;
        gesamtwert = 0;
        aceCount = 0;
    }

    public String getHandName() {
        return hand;
    }

    public void updateSum(Card card) {
        int value = card.getValue();
        if (card.isAce()) {
            aceCount++;
        }
        if (!card.isFaceDown()) {
            gesamtwert += value;
            gesamtwert = adjustForAce(gesamtwert);
        }
    }

    private int adjustForAce(int sum) {
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount --;
        }
        return sum;
    }

    public int getGesamtwert() {
        return gesamtwert;
    }

    public int getKartenanzahl() {
        return kartenanzahl;
    }

    public ArrayList<Card> getKarten() {
        return karten;
    }
}