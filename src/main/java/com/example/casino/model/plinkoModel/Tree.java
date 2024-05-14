package com.example.casino.model.plinkoModel;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Tree {
    private Object data;
    private Tree left;
    private Tree right;

    public Tree(Object data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Object getObj() {
        return data;
    }
    public double getlayoutX(){

        if (data instanceof Label){
            return ((Label) data).getLayoutX() ;
        }
        else if (data instanceof Circle){
            return ((Circle) data).getLayoutX();
        }
        return 0;
    }
    public double getlayoutY(){

        if (data instanceof Label){
            return ((Label) data).getLayoutY() ;
        }
        else if (data instanceof Circle){
            return ((Circle) data).getLayoutY();
        }
        return 0;
    }
    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setRight(Tree right) {
        this.right = right;
    }



    }
