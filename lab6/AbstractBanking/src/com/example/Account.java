package com.example;

public abstract class Account {
    protected double balance;

    public Account(double bal) {
        this.balance= bal;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return  " current balance is " + balance;
    }

    public abstract boolean withdraw(double amount) ;
    
}
