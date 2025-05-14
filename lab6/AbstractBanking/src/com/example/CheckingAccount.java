package com.example;

public class CheckingAccount extends Account {

    private final double overdraftLimit;  // Лимит по кредиту

    public CheckingAccount(double balance, double overdraftLimit) {
        super(balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= balance + overdraftLimit) { // Можно уйти в минус в пределах лимита
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds (Overdraft limit reached).");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Checking Account: balance = " + balance + ", overdraft limit = " + overdraftLimit;
    }
}
