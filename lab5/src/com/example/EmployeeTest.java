package lab5.src.com.example;

import lab5.src.com.example.business.*;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee emp = new Employee(101, "Jane Smith", "012-34-5678", 120345.27);
        Manager mgr = new Manager(102, "John Doe", "123-45-6789", 150000.00, "IT");
        Director dir = new Director(103, "Alice Johnson", "234-56-7890", 200000.00, "Finance", 50000.00);

        EmployeeStockPlan esp = new EmployeeStockPlan();

        printEmployee(emp, esp);
        printEmployee(mgr, esp);
        printEmployee(dir, esp);
    }

    public static void printEmployee(Employee emp) {
        System.out.println("Employee type: " + emp.getClass().getSimpleName());
        System.out.println(emp);
    }

    public static void printEmployee(Employee emp, EmployeeStockPlan esp) {
        printEmployee(emp);
        System.out.println("Stock Options: " + esp.grantStock(emp));
        System.out.println();
    }
}
