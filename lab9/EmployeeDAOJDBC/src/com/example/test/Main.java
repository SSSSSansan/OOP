package com.example.test;
import com.example.dao.*;
import com.example.model.*;
public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();

        try {
           
            Employee emp = new Employee(1, "John", "Doe", new java.util.Date(), 50000);
            employeeDAO.add(emp);

            
            Employee foundEmp = employeeDAO.findById(1);
            System.out.println("Found Employee: " + foundEmp.getFirstName());

            foundEmp.setSalary(55000);
            employeeDAO.update(foundEmp);

            System.out.println("All Employees: " + employeeDAO.getAllEmployees());

            employeeDAO.delete(1);

        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                employeeDAO.close();
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }
}