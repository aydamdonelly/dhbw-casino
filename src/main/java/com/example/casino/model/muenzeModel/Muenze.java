package com.example.casino.model.muenzeModel;

import java.util.Random;

public class Muenze {

    private String seite = "";

    public String toss() {
        Random rand = new Random();
        int side = rand.nextInt(0,2);
        if(side==1) {
            seite = "Heads";
            return "Heads";
        }
        else {
            seite = "Tails";
            return "Tails";
        }
    }

    public String getSeite() {
        return seite;
    }
}
