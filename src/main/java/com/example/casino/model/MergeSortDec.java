package com.example.casino.model;

import java.util.ArrayList;
import java.util.List;

public class MergeSortDec {

    // merge sort for the score arraylist just to flex it
    public static List<Float> mergeSort(List<Float> arr) {
        if (arr.size() <= 1) {
            return arr;
        }

        int middle = arr.size() / 2;
        List<Float> left = new ArrayList<>(arr.subList(0, middle));
        List<Float> right = new ArrayList<>(arr.subList(middle, arr.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static List<Float> merge(List<Float> left, List<Float> right) {
        int leftIndex = 0, rightIndex = 0;
        List<Float> merged = new ArrayList<>();

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) >= right.get(rightIndex)) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }

        return merged;
    }

}
