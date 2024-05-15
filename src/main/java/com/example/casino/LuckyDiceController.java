package com.example.casino;

import com.example.casino.model.NegativeBetException;
import com.example.casino.model.luckyDiceModel.LuckyDiceLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.casino.model.InsufficientFundsException;

import java.io.FileInputStream;
import java.util.HashMap;

public class LuckyDiceController implements Controller {

    private LuckyDiceLogic logic;

    @FXML
    private ImageView dice1Image;

    @FXML
    private ImageView dice2Image;

    @FXML
    private Label luckydicemessager;

    @FXML
    private TextField guessedTotalField;

    @FXML
    private TextField betAmountField;

    @FXML
    private Button rollButton;

    public LuckyDiceController() {
        this.logic = new LuckyDiceLogic(this);
    }

    @FXML
    public void showInfo() {
        String infoText = "Faktoren je nach Augenzahl:\n" +
                "2 -> 20.0\n" +
                "3 -> 14.0\n" +
                "4 -> 12.0\n" +
                "5 -> 10.0\n" +
                "6 -> 4.0\n" +
                "7 -> 2.0\n" +
                "8 -> 4.0\n" +
                "9 -> 10.0\n" +
                "10 -> 12.0\n" +
                "11 -> 14.0\n" +
                "12 -> 20.0";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(infoText);

        alert.showAndWait();
    }

    @FXML
    public void rollDice() {
        try {
            int guessedTotal = Integer.parseInt(guessedTotalField.getText());
            if (guessedTotal < 2 || guessedTotal > 12) {
                // Zeige einen Alert, wenn die Eingabe nicht im Bereich 2-12 liegt
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ungültige Eingabe");
                alert.setHeaderText(null);
                alert.setContentText("Bitte gib eine Zahl zwischen 2 und 12 ein.");
                alert.showAndWait();
                guessedTotalField.setText("");
                return; // Beende die Methode frühzeitig
            }
            float betAmount = convertInput(betAmountField.getText());
            logic.setBet(guessedTotal, betAmount);
            logic.rollDice();
            updateDiceImages();  // Aktualisiere die Bilder nach dem Würfeln
        } catch (NumberFormatException e) {
            betAmountField.setText("");
            betAmountField.setPromptText("Gebe einen gültigen Betrag an!");
        } catch (InsufficientFundsException e) {
            betAmountField.setText("");
            betAmountField.setPromptText(e.getMessage());
        } catch (NegativeBetException e){
            betAmountField.setText("");
            betAmountField.setPromptText(e.getMessage());
        }
    }


    private void updateDiceImages() {
        int dice1Value = logic.getDice1Value();
        int dice2Value = logic.getDice2Value();

        try {
            String text1 = "src/main/resources/img/luckyDice/dice_" + dice1Value + ".png";
            String text2 = "src/main/resources/img/luckyDice/dice_" + dice2Value + ".png";
            System.out.println(text1);
            System.out.println(text2);
            FileInputStream path1 = new FileInputStream(text1);
            FileInputStream path2 = new FileInputStream(text2);

            if (path1 == null || path2 == null) {
                throw new IllegalArgumentException("Ein oder mehrere Bilder konnten nicht gefunden werden.");
            }
            Image image1 = new Image(path1);
            Image image2 = new Image(path2);
            dice1Image.setImage(image1);
            dice2Image.setImage(image2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Fehler beim Laden von: dice_" + dice1Value + " oder dice_" + dice2Value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ein unerwarteter Fehler ist aufgetreten beim Laden der Bilder: " + e);
        }
    }






    @Override
    public void onGameEnd(String result) {
        if(!result.equals("L")){
            result = result.replace(".",",");
            luckydicemessager.setText("Du hast "+result+"0€ gewonnen!");
        } else {
            luckydicemessager.setText("Du hast nichts gewonnen! Spiel weiter!");
        }
    }

    @Override
    public float convertInput(String inputText) throws NumberFormatException {
        inputText = inputText.replace(",", ".");
        return Float.parseFloat(inputText);
    }

    @Override
    public void betRejected(Exception e) {
        betAmountField.setText("");
        betAmountField.setPromptText(e.getMessage());
    }
}
