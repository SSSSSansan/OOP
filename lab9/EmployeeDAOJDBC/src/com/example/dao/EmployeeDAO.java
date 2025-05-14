package com.example.dao;

import java.util.List;
import com.example.model.*;
public interface EmployeeDAO {
    public void add(Employee emp) throws DAOException;
    public Employee findById(int id) throws DAOException;
    public void update(Employee emp) throws DAOException;
    public void delete(int id) throws DAOException;
    public List<Employee> getAllEmployees() throws DAOException;
    public void close() throws DAOException;
}