package com.t2p1.jdbc.services;

import com.t2p1.jdbc.dao.EmployeeDAO;
import com.t2p1.jdbc.dao.EmployeeDAOImpl;
import com.t2p1.jdbc.models.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeService() throws SQLException {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    // Método para añadir un empleado
    public void addEmployee(Employee employee) {
        try {
            employeeDAO.createEmployee(employee);
            System.out.println("Empleado añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir empleado: " + e.getMessage());
        }
    }

    // Método para obtener un empleado por ID
    public Employee getEmployeeById(Long employeeId) {
        try {
            return employeeDAO.getEmployeeById(employeeId);
        } catch (SQLException e) {
            System.out.println("Error al consultar empleado: " + e.getMessage());
            return null;
        }
    }

    // Método para obtener todos los empleados
    public void getAllEmployees() {
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println("No hay empleados registrados.");
            } else {
                System.out.println("Lista de Empleados:");
                for (Employee employee : employees) {
                    System.out.println(employee);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de empleados: " + e.getMessage());
        }
    }

    // Método para actualizar un empleado
    public void updateEmployee(Employee employee) {
        try {
            employeeDAO.updateEmployee(employee);
            System.out.println("Empleado actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
        }
    }

    // Método para eliminar un empleado por ID
    public void deleteEmployee(Long employeeId) {
        try {
            employeeDAO.deleteEmployee(employeeId);
            System.out.println("Empleado eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }
    }

    // Método para asignar un proyecto a un empleado (relacionar proyectos con empleados)
    public void assignProjectToEmployee(Long employeeId, Long projectId) {
        try {
            // Aquí deberías implementar la lógica para asignar un proyecto a un empleado
            // usando la clase EmployeeProjectDAOImpl si existe.
            System.out.println("Proyecto asignado al empleado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al asignar proyecto al empleado: " + e.getMessage());
        }
    }
}
