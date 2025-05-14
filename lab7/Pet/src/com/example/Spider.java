package com.example;

public class Spider extends Animal implements Ambulatory {
    private Ambulatory ambulatory;

    public Spider() {
        super(8);
        this.ambulatory = new AmbulatoryImpl(8);
    }

    @Override
    public void eat() {
        System.out.println("The spider eats a fly.");
    }

    @Override
    public void walk() {
        ambulatory.walk();
    }
}
