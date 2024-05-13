package com.example.casino.model;

import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.Random;

public class BinaryTree {
    private Tree root;
    private LinkedList<Tree> liste = new LinkedList<Tree>();

    public BinaryTree(Tree root) {
        this.root = root;
    }

    public void addNode(Tree parent, String position, Tree child) {
        if (parent == null) {
            System.out.println("Cannot add node. Parent is null.");
            return;
        }

        if (position.equalsIgnoreCase("left")) {
            parent.setLeft(child);
        } else if (position.equalsIgnoreCase("right")) {
            parent.setRight(child);
        } else {
            System.out.println("Invalid position. Node not added.");
        }
    }
    public Tree getNode(){
        return root;
    }
    private void setListe(Tree t){
        liste.add(t);
    }

    public LinkedList<Tree> getListe() {
        return liste;
    }

    public   Label getRandomLeafNode(Tree node) {
        if (node == null) {
            return null;
        }


        if (node.getLeft() == null && node.getRight() == null) {
            // Leaf node found
            if (node.getObj() instanceof Label) {
                return (Label) node.getObj();
            } else {
                return null;
            }
        }
        setListe(node);
        Random random = new Random();
        System.out.println("Random dings: " + random);
        if (random.nextBoolean()) {
            return getRandomLeafNode(node.getLeft());
        } else {
            return getRandomLeafNode(node.getRight());
        }
    }
}
