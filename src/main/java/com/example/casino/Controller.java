package com.example.casino;

import javafx.scene.control.Button;

import java.util.HashMap;

public interface Controller {
    HashMap<Button, Boolean> blockedButtons = new HashMap<>();


    void onGameEnd(String result);
    void setBlocked(Button button);
    void unblockButton(Button button);
    float convertInput(String inputtext) throws NumberFormatException;
    void betRejected(Exception e);
}
