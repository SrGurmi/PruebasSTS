package com.t2p1.jdbc.dao;

import com.t2p1.jdbc.models.Employee;
import com.t2p1.jdbc.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;

    public EmployeeDAOImpl() {
        try {
           
            this.connection = DBConnection.getInstance().getConnection();
            
           
            if (this.connection == null) {
                throw new SQLException("La conexión a la base de datos no se pudo establecer.");
            }
        } catch (SQLException e) {            // Imprimir el mensaje de error si la conexión falla
            System.out.println("Error al obtener la conexión en EmployeeDAOImpl: " + e.getMessage());
        }
    }

    @Override
    public void createEmployee(Employee employee) throws SQLException {
        if (connection == null) {
            throw new SQLException("No se ha establecido la conexión a la base de datos.");
        }

        String sql = "INSERT INTO employees (first_name, last_name, email, salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setDouble(4, employee.getSalary());
            stmt.executeUpdate();
        }
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws SQLException {
        if (connection == null) {
            throw new SQLException("No se ha establecido la conexión a la base de datos.");
        }

        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getTimestamp("last_update"));
            }
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        if (connection == null) {
            throw new SQLException("No se ha establecido la conexión a la base de datos.");
        }

        String sql = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                employees.add(new Employee(rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getTimestamp("last_update")));
            }
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        if (connection == null) {
            throw new SQLException("No se ha establecido la conexión a la base de datos.");
        }

        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, salary = ? WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setDouble(4, employee.getSalary());
            stmt.setLong(5, employee.getEmployeeId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) throws SQLException {
        if (connection == null) {
            throw new SQLException("No se ha establecido la conexión a la base de datos.");
        }

        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, employeeId);
            stmt.executeUpdate();
        }
    }
}


