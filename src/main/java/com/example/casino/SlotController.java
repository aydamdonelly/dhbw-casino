package com.example.casino;

import javafx.fxml.FXML;
import com.example.casino.model.slotsModel.SlotsLogik;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SlotController implements Controller {
    @FXML
    private Button spinbutton;
    @FXML
    private Button betPlus;
    @FXML
    private Button betMinus;
    @FXML
    private TextField betMoney;
    @FXML
    private Label winningmessageslots;

    @FXML
    private ImageView symbol1ImageView;
    @FXML
    private ImageView symbol2ImageView;
    @FXML
    private ImageView symbol3ImageView;

    private Image[] symbols; // Array to store symbol images

    private SlotsLogik logic = new SlotsLogik(this);
    // Input streams for slot images
    FileInputStream sevenStream = new FileInputStream("src/main/resources/img/slots/seven.png");
    FileInputStream cherryStream = new FileInputStream("src/main/resources/img/slots/cherry.png");
    FileInputStream lemonStream = new FileInputStream("src/main/resources/img/slots/lemon.png");
    FileInputStream grapesStream = new FileInputStream("src/main/resources/img/slots/grapes.png");

    public SlotController() throws FileNotFoundException {
    }


    @FXML
    public void spinButton() {
        try {
            //implement the animation
            logic.spin(convertInput(betMoney.getText()));

        } catch (NumberFormatException e) {
            winningmessageslots.setText("Gebe einen gültigen Betrag ein!");
        }
    }

    @FXML
    public void showInfo() {
        String infoText = "Dein Kontostand erhöht sich um den folgenden Faktor:\n" +
                "\n"+
                "3 mal gleiches Symbol -> +60% vom Einsatz\n" +
                "3 mal Symbol '7' -> +100% vom Einsatz\n" +
                "2 mal gleiche Symbole -> +10% vom Einsatz\n" +
                "\nTrifft nichts davon ein, verlierst du den Einsatz. Pech gehabt..";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(infoText);
        //driving on the right side road he says im pretty wearing his clothews and hes got hands that make hell seem cold feet on the dashboard hes like a poem i wish i wrote i wish i wrote and he laughs at all my jokes and he says im so amerivcan
        alert.showAndWait();
    }

    @FXML
    public void plusButton(){
        betMoney.setText(Float.toString(logic.modifyBet(5)));
    }
    @FXML
    public void minusButton(){
        betMoney.setText(Float.toString(logic.modifyBet(-5)));
    }

    @Override
    public void onGameEnd(String result) {
        winningmessageslots.setText(result);
    }

    public void initialize() throws FileNotFoundException {
        // Initialize symbols array with images

        symbols = new Image[]{
                new Image(sevenStream),
                new Image(cherryStream),
                new Image(lemonStream),
                new Image(grapesStream)
        };
    }

//    private final Image[] symbolsChain = {
//                new Image("seven.png"),
//                new Image("cherry.png"),^
//                new Image("grapes.png"),
//                new Image("lemon.png")
//        };


    public void symboleBerechnet(int symbol1, int symbol2, int symbol3) {
        symbol1ImageView.setImage(symbols[symbol1 - 1]); //assuming symbol[0] is number 1
        symbol2ImageView.setImage(symbols[symbol2 - 1]);
        symbol3ImageView.setImage(symbols[symbol3 - 1]);

    }

    @Override
    public float convertInput(String inputtext) throws NumberFormatException{
        inputtext = inputtext.replace(",",".");
        return Float.parseFloat(inputtext);
    }

    @Override
    public void betRejected(Exception e) {
        winningmessageslots.setText(e.getMessage());
    }
}


