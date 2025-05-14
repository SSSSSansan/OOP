package com.example;

public abstract class Account1 {
    protected double balance;

    public Account1() {
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract boolean withdraw(double amount) ;
    
}
