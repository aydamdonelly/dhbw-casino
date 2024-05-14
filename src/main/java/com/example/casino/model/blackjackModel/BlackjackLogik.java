package com.example.casino.model.blackjackModel;

import com.example.casino.*;
import com.example.casino.model.CasinoPlayer;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.NegativeBetException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BlackjackLogik {
    private ArrayList<Card> deck = new ArrayList<>();
    private Hand playerHand = new Hand("Spieler");
    private Hand dealerHand = new Hand("Dealer");

    private static CasinoPlayer player = CasinoPlayer.getPlayer();

    private BlackjackController controller;

    private float wetteinsatz = 0;

    public BlackjackLogik(Controller controller) {
        this.controller = (BlackjackController) controller;
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() {
        deck = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, suit)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public void startGame(float einsatz) {
        try {
            player.checkBet(einsatz);
            if (playerHand.getKartenanzahl() != -1) {
                controller.clearboard();
            }
            playerHand.resetHand();
            dealerHand.resetHand();
            wetteinsatz = einsatz;
            dealCard(playerHand, false);
            dealCard(playerHand, false);
            dealCard(dealerHand, false);
            dealCard(dealerHand, true); // Face-down card for the dealer
        } catch (InsufficientFundsException e) {
            controller.betRejected(e);
            controller.setActiveGame(false);
        } catch (NegativeBetException e){
            controller.betRejected(e);
            controller.setActiveGame(false);
        }
    }

    public void hit() {
        dealCard(playerHand, false);
    }

    public void playDealerHand() {
        aufdecken();
        while (dealerHand.getGesamtwert() < 17) {
            dealCard(dealerHand, false);
        }
        endGame();
    }

    private void aufdecken() {
        Card faceDownCard = dealerHand.searchFaceDown();
        if (faceDownCard != null) {
            controller.drawHand(faceDownCard.getSuit().getSymbol(), faceDownCard.getRank().getRankSymbol(), 1, "Dealer");
        }
    }

    public void dealCard(Hand hand, boolean faceDown) {
        renewDeckIfNeeded();
        Card card = deck.remove(0);
        card.setFaceDown(faceDown);
        hand.drawCard(card);
        hand.updateSum(card);
        if (playerHand.getGesamtwert() > 21) {
            endGame();
        }
        if (!faceDown) {
            controller.drawHand(card.getSuit().getSymbol(), card.getRank().getRankSymbol(), hand.getKartenanzahl(), hand.getHandName());
        }
    }

    private void renewDeckIfNeeded() {
        if (deck.isEmpty()) {
            initializeDeck();
            shuffleDeck();
        }
    }

    public void endGame() {
        kontostand();
        controller.onGameEnd(determineWinner());
    }

    public String determineWinner() {
        if (playerHand.getGesamtwert() == 21 && playerHand.getKartenanzahl() == 1) {
            return "Blackjack by Player";
        }
        if (playerHand.getGesamtwert() > 21) {
            return "Dealer wins";
        }
        if (dealerHand.getGesamtwert() > 21) {
            return "Player wins";
        }
        if (playerHand.getGesamtwert() == dealerHand.getGesamtwert()) {
            return "Push";
        }
        return (playerHand.getGesamtwert() > dealerHand.getGesamtwert()) ? "Player wins" : "Dealer wins";
    }

    public void kontostand() {
        String result = determineWinner();
        switch (result) {
            case "Dealer wins":
                player.setKontostand(player.getKontostand() - wetteinsatz);
                break;
            case "Player wins":
                player.setKontostand(player.getKontostand() + wetteinsatz);
                break;
            case "Blackjack by Player":
                player.setKontostand((float) (player.getKontostand() + wetteinsatz * 1.5));
                break;
        }
    }
}
