package com.t2p1.jdbc.dao;


import com.t2p1.jdbc.models.Employee;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void createEmployee(Employee employee) throws SQLException;
    Employee getEmployeeById(Long employeeId) throws SQLException;
    List<Employee> getAllEmployees() throws SQLException;
    void updateEmployee(Employee employee) throws SQLException;
    void deleteEmployee(Long employeeId) throws SQLException;
}

