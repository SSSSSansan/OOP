package com.example;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayAction extends RecursiveAction {
    private final int threshold;
    private final int[] myArray;
    private final int start;
    private final int end;

    public RandomArrayAction(int[] myArray, int start, int end, int threshold) {
        this.myArray = myArray;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if ((end - start) < threshold) {
            for (int i = start; i <= end; i++) {
                myArray[i] = ThreadLocalRandom.current().nextInt(1000); // значения от 0 до 999
            }
        } else {
            int mid = (start + end) / 2;
            invokeAll(
                new RandomArrayAction(myArray, start, mid, threshold),
                new RandomArrayAction(myArray, mid + 1, end, threshold)
            );
        }
    }
}
