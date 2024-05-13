package com.example.casino;


import com.example.casino.model.BinaryTree;
import com.example.casino.model.InsufficientFundsException;
import com.example.casino.model.Tree;
import com.example.casino.model.plinkoLogik;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.LinkedList;

public class PlinkoController implements Controller{
    private plinkoLogik logik = new plinkoLogik(this);
    private Thread startThread;
    @FXML
    private Pane p;
    @FXML
    private Circle c_1_1;


    @FXML
    private Circle c_2_1;
    @FXML
    private Circle c_2_2;
    @FXML
    private Circle c_3_1;
    @FXML
    private Circle c_3_2;
    @FXML
    private Circle c_3_3;
    @FXML
    private Circle c_4_1;
    @FXML
    private Circle c_4_2;
    @FXML
    private Circle c_4_3;
    @FXML
    private Circle c_4_4;
    @FXML
    private Circle c_5_1;
    @FXML
    private Circle c_5_2;
    @FXML
    private Circle c_5_3;
    @FXML
    private Circle c_5_4;
    @FXML
    private Circle c_5_5;
    @FXML
    private Circle c_6_1;
    @FXML
    private Circle c_6_2;
    @FXML
    private Circle c_6_3;
    @FXML
    private Circle c_6_4;
    @FXML
    private Circle c_6_5;
    @FXML
    private Circle c_6_6;

    @FXML
    private Label mulitply2v1;
    @FXML
    private Label mulitply08v1;
    @FXML
    private Label mulitply01;
    @FXML
    private Label mulitply08v2;
    @FXML
    private Label mulitply2v2;
    @FXML
    private Label mulitply0v1;
    @FXML
    private Label mulitply0v2;
    @FXML
    private Button spawnBall;
    @FXML
    private TextField betAmount;

    private HashMap<Button, Boolean> blockedButtons = new HashMap<>();

    /*private Tree t_2_1 = new Tree(c_2_1);
    private Tree t_2_2 = new Tree(c_2_2);
    private Tree t_3_1 = new Tree(c_3_1);
    private Tree t_3_2 = new Tree(c_3_2);
    private Tree t_3_3 = new Tree(c_3_3);
    private Tree t_4_1 = new Tree(c_4_1);
    private Tree t_4_2 = new Tree(c_4_2);
    private Tree t_4_3 = new Tree(c_4_3);
    private Tree t_4_4 = new Tree(c_4_4);
    private Tree t_5_1 = new Tree(c_5_1);
    private Tree t_5_2 = new Tree(c_5_2);
    private Tree t_5_3 = new Tree(c_5_3);
    private Tree t_5_4 = new Tree(c_5_4);
    private Tree t_5_5 = new Tree(c_5_5);
    private Tree t_6_1 = new Tree(c_6_1);
    private Tree t_6_2 = new Tree(c_6_2);
    private Tree t_6_3 = new Tree(c_6_3);
    private Tree t_6_4 = new Tree(c_6_4);
    private Tree t_6_5 = new Tree(c_6_5);
    private Tree t_6_6 = new Tree(c_6_6);*/



public BinaryTree  init(){
    Tree t_1_1  = new Tree(c_1_1);
    BinaryTree binaryTree = new BinaryTree(t_1_1);
    Tree t_2_1 = new Tree(c_2_1);
    Tree t_2_2 = new Tree(c_2_2);
    binaryTree.addNode(binaryTree.getNode(), "left", t_2_1);
    binaryTree.addNode(binaryTree.getNode(), "right", t_2_2);

    Tree t_3_1 = new Tree(c_3_1);
    Tree t_3_2 = new Tree(c_3_2);
    Tree t_3_3 = new Tree(c_3_3);

    binaryTree.addNode(t_2_1, "left", t_3_1);
    binaryTree.addNode(t_2_1, "right", t_3_2);
    binaryTree.addNode(t_2_2, "left", t_3_2);
    binaryTree.addNode(t_2_2, "right", t_3_3);

    Tree t_4_1 = new Tree(c_4_1);
    Tree t_4_2 = new Tree(c_4_2);
    Tree t_4_3 = new Tree(c_4_3);
    Tree t_4_4 = new Tree(c_4_4);

    binaryTree.addNode(t_3_1, "left",t_4_1);
    binaryTree.addNode(t_3_1,"right",t_4_2);
    binaryTree.addNode(t_3_2, "left",t_4_2);
    binaryTree.addNode(t_3_2,"right",t_4_3);
    binaryTree.addNode(t_3_3, "left",t_4_3);
    binaryTree.addNode(t_3_3,"right",t_4_4);

    Tree t_5_1 = new Tree(c_5_1);
    Tree t_5_2 = new Tree(c_5_2);
    Tree t_5_3 = new Tree(c_5_3);
    Tree t_5_4 = new Tree(c_5_4);
    Tree t_5_5 = new Tree(c_5_5);

    binaryTree.addNode(t_4_1, "left",t_5_1);
    binaryTree.addNode(t_4_1,"right",t_5_2);
    binaryTree.addNode(t_4_2, "left",t_5_2);
    binaryTree.addNode(t_4_2,"right",t_5_3);
    binaryTree.addNode(t_4_3, "left",t_5_3);
    binaryTree.addNode(t_4_3,"right",t_5_4);
    binaryTree.addNode(t_4_4, "left",t_5_4);
    binaryTree.addNode(t_4_4,"right",t_5_5);

    Tree t_6_1 = new Tree(c_6_1);
    Tree t_6_2 = new Tree(c_6_2);
    Tree t_6_3 = new Tree(c_6_3);
    Tree t_6_4 = new Tree(c_6_4);
    Tree t_6_5 = new Tree(c_6_5);
    Tree t_6_6 = new Tree(c_6_6);

    binaryTree.addNode(t_5_1, "left",t_6_1);
    binaryTree.addNode(t_5_1,"right",t_6_2);
    binaryTree.addNode(t_5_2, "left",t_6_2);
    binaryTree.addNode(t_5_2,"right",t_6_3);
    binaryTree.addNode(t_5_3, "left",t_6_3);
    binaryTree.addNode(t_5_3,"right",t_6_4);
    binaryTree.addNode(t_5_4, "left",t_6_4);
    binaryTree.addNode(t_5_4,"right",t_6_5);
    binaryTree.addNode(t_5_5, "left",t_6_5);
    binaryTree.addNode(t_5_5,"right",t_6_6);

    Tree multby2v1 = new Tree(mulitply2v1);
    Tree multby08v1 = new Tree(mulitply08v1);
    Tree multby01 = new Tree(mulitply01);
    Tree multby08v2 = new Tree(mulitply08v2);
    Tree multby2v2 = new Tree(mulitply2v2);
    Tree multby0v1 = new Tree(mulitply0v1);
    Tree multby0v2 = new Tree(mulitply0v2);

    binaryTree.addNode(t_6_1,"left",multby0v1);
    binaryTree.addNode(t_6_1,"right",multby2v1);
    binaryTree.addNode(t_6_2,"left",multby2v1);
    binaryTree.addNode(t_6_2,"right",multby08v1);
    binaryTree.addNode(t_6_3,"left",multby08v1);
    binaryTree.addNode(t_6_3,"right",multby01);
    binaryTree.addNode(t_6_4,"left",multby01);
    binaryTree.addNode(t_6_4,"right",multby08v2);
    binaryTree.addNode(t_6_5,"left",multby08v2);
    binaryTree.addNode(t_6_5,"right",multby2v2);
    binaryTree.addNode(t_6_6,"left",multby2v2);
    binaryTree.addNode(t_6_6,"right",multby0v2);


return  binaryTree;

}
    @FXML
    protected void onSpawnBall(){
    try{
        boolean b = logik.player.checkBet(Float.parseFloat(betAmount.getText()));
    } catch (InsufficientFundsException e){
        betRejected(e);
    return;
    }


    Circle circle= new Circle(300,0,10, Color.ORANGE);
    p.getChildren().add(circle);

    try {

            startThread = new Thread(() -> {



               plinkoLogik.spawnBall(convertInput(betAmount.getText()) , init());
                LinkedList<Tree> tList = plinkoLogik.getListe();

                for (Tree t: tList){
                   Platform.runLater(()-> {

                               circle.setCenterX(t.getlayoutX());
                               circle.setCenterY(t.getlayoutY());
                           }
                   );

                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
                Platform.runLater(()->p.getChildren().remove(circle));

            }
           );



            startThread.start();
        }
        catch(NumberFormatException e) {
            betAmount.setText("");
            betAmount.setPromptText("Gültige Zahl eingeben!");
        }
    }
    public void makeInterrupt(){
        System.out.println("interrupt");
        startThread.interrupt();
    }


    @Override
    public void onGameEnd(String result) {
   /*if (startThread.isAlive()){
        startThread.interrupt();
    }*/

    }


    @Override
    public void setBlocked(Button button) {

    }

    @Override
    public void unblockButton(Button button) {

    }

    @Override
    public float convertInput(String inputtext) throws NumberFormatException {
        inputtext = inputtext.replace(",",".");
        return Float.parseFloat(inputtext);

    }

    @Override
    public void betRejected(Exception e) {
        betAmount.setText(e.getMessage());

    }

    public void clearMessage() {
        betAmount.setText("");
    }
}
