package com.example;

import java.util.Date;

public class TimeDepositAccount extends Account {

    private final Date maturityDate;

    public TimeDepositAccount(double balance, Date maturityDate) {
        super(balance);
        this.maturityDate = maturityDate;
    }

    @Override
    public boolean withdraw(double amount) {
        Date today = new Date();
        if (today.after(maturityDate)) {  // Проверяем, прошла ли дата окончания депозита
            if (amount <= balance) {
                balance -= amount;
                return true;
            } else {
                System.out.println("Insufficient funds.");
                return false;
            }
        } else {
            System.out.println("Withdrawal not allowed until maturity date: " + maturityDate);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Time Deposit Account (matures on " + maturityDate + "): balance = " + balance;
    }
}
