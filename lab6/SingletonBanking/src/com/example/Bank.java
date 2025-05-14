package com.example;

public class Bank {
    private static final Bank instance = new Bank(); // Единственный экземпляр

    private final Customer[] customers;
    private int numberOfCustomers;

    // Закрытый конструктор
    private Bank() {
        customers = new Customer[10];
        numberOfCustomers = 0;
    }

    // Метод для получения единственного экземпляра
    public static Bank getInstance() {
        return instance;
    }

    public void addCustomer(String f, String l) {
        int i = numberOfCustomers++;
        customers[i] = new Customer(f, l);
    }

    public int getNumOfCustomers() {
        return numberOfCustomers;
    }

    public Customer getCustomer(int customer_index) {
        return customers[customer_index];
    }
}
