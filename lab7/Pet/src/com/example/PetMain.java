package com.example;

public class PetMain {
    public static void main(String[] args) {
    
        Spider s = new Spider();
        s.eat();
        s.walk();

        Animal a = new Spider();
        a.eat();

        if (a instanceof Ambulatory) {
            ((Ambulatory) a).walk();
        }
    }

    public static void playWithAnimal(Animal a) {
        
    }
}
