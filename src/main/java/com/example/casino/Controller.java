package com.example.casino;

import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.NegativeBetException;
import com.example.casino.model.luckyDiceModel.LuckyDiceLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public interface Controller {
    void onGameEnd(String result) throws FileNotFoundException;
    float convertInput(String inputtext) throws NumberFormatException;
    void betRejected(Exception e);
}
