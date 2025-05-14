package com.example.dao;

public class EmployeeDAOFactory {
    public static EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAOJDBCImpl();
    }
}