package com.example;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int[] data = new int[1024 * 1024 * 128]; 

        ForkJoinPool pool = new ForkJoinPool();

       
        RandomArrayAction randomTask = new RandomArrayAction(data, 0, data.length - 1, data.length / 16);
        pool.invoke(randomTask);

        
        FindMaxTask task = new FindMaxTask(data, 0, data.length - 1, data.length / 16);
        Integer result = pool.invoke(task);
        System.out.println("Max value found: " + result);
    }
}

