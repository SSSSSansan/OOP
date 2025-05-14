package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.model.Employee;

public class EmployeeTestInteractive {
    private static List<Employee> employees = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean timeToQuit = false;
        do {
            timeToQuit = executeMenu(in);
        } while (!timeToQuit);
    }

    public static boolean executeMenu(BufferedReader in) throws IOException {
        Employee emp;
        String action;
        int id;

        System.out.println("\n\n[C]reate | [R]ead | [U]pdate | [D]elete | [L]ist | [Q]uit: ");
        action = in.readLine();
        if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
            return true;
        }

        switch (action.toUpperCase().charAt(0)) {
            case 'C':
                emp = inputEmployee(in);
                employees.add(emp);
                System.out.println("Successfully added Employee Record: " + emp.getId());
                break;
            case 'R':
                System.out.println("Enter employee id: ");
                id = Integer.parseInt(in.readLine().trim());
                emp = findById(id);
                System.out.println(emp != null ? emp : "Employee not found");
                break;
            case 'U':
                System.out.println("Enter employee id: ");
                id = Integer.parseInt(in.readLine().trim());
                emp = findById(id);
                if (emp != null) {
                    employees.remove(emp);
                    emp = inputEmployee(in, emp);
                    employees.add(emp);
                    System.out.println("Successfully updated Employee Record: " + emp.getId());
                } else {
                    System.out.println("Employee not found");
                }
                break;
            case 'D':
                System.out.println("Enter employee id: ");
                id = Integer.parseInt(in.readLine().trim());
                emp = findById(id);
                if (emp != null) {
                    employees.remove(emp);
                    System.out.println("Deleted Employee " + id);
                } else {
                    System.out.println("Employee not found");
                }
                break;
            case 'L':
                employees.forEach(System.out::println);
                break;
        }
        return false;
    }

    private static Employee findById(int id) {
        return employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public static Employee inputEmployee(BufferedReader in) throws IOException {
        return inputEmployee(in, null);
    }

    public static Employee inputEmployee(BufferedReader in, Employee empDefaults) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        int id = empDefaults == null ? (employees.size() + 1) : empDefaults.getId();
        String firstName;
        String lastName;
        Date birthDate = null;
        float salary;

        System.out.println("Enter first name: ");
        firstName = in.readLine().trim();
        if (firstName.isEmpty() && empDefaults != null) firstName = empDefaults.getFirstName();

        System.out.println("Enter last name: ");
        lastName = in.readLine().trim();
        if (lastName.isEmpty() && empDefaults != null) lastName = empDefaults.getLastName();

        System.out.println("Enter birth date (MMM d, yyyy): ");
        String dateStr = in.readLine().trim();
        if (dateStr.isEmpty() && empDefaults != null) {
            birthDate = empDefaults.getBirthDate();
        } else {
            try {
                birthDate = df.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Using default.");
            }
        }

        System.out.println("Enter salary: ");
        String salaryStr = in.readLine().trim();
        salary = salaryStr.isEmpty() && empDefaults != null ? empDefaults.getSalary() : Float.parseFloat(salaryStr);

        return new Employee(id, firstName, lastName, birthDate, salary);
    }
}
