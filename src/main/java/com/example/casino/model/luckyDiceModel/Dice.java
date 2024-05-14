package com.example.casino.model.luckyDiceModel;

import java.util.Random;

public class Dice {
    private Random rand;

    public Dice() {
        this.rand = new Random();
    }

    public int roll() {
        return rand.nextInt(1,7);
    }
}
