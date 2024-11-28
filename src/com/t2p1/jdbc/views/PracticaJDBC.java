package com.t2p1.jdbc.views;


import com.t2p1.jdbc.models.Employee;
import com.t2p1.jdbc.models.Project;
import com.t2p1.jdbc.services.EmployeeService;
import com.t2p1.jdbc.services.ProjectService;

import java.sql.SQLException;
import java.util.Scanner;

public class PracticaJDBC {
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final Scanner scanner;

    public PracticaJDBC() throws SQLException {
        employeeService = new EmployeeService();
        projectService = new ProjectService();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws SQLException {
        PracticaJDBC app = new PracticaJDBC();
        app.run();
    }

    // Método principal para ejecutar el menú
    public void run() {
        int option;
        do {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consume el salto de línea
            switch (option) {
                case 1 -> addEmployee();
                case 2 -> modifyEmployee();
                case 3 -> consultEmployee();
                case 4 -> deleteEmployee();
                case 5 -> addProject();
                case 6 -> assignProject();
                case 7 -> bulkCreateProjects();
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 8);
    }

    // Muestra el menú principal en la consola
    private void displayMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Añadir Empleado");
        System.out.println("2. Modificar Empleado");
        System.out.println("3. Consultar Empleado");
        System.out.println("4. Eliminar Empleado");
        System.out.println("5. Añadir Proyecto");
        System.out.println("6. Asignar Proyecto a Empleado");
        System.out.println("7. Crear Proyectos en Masa");
        System.out.println("8. Salir");
        System.out.print("Selecciona una opción: ");
    }

    // Métodos específicos para cada opción del menú

    // Añade un nuevo empleado
    private void addEmployee() {
        System.out.println("\n--- Añadir Empleado ---");
        System.out.print("Nombre: ");
        String firstName = scanner.nextLine();
        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Salario: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(null, firstName, lastName, email, salary, null);
        employeeService.addEmployee(employee);
    }

    // Modifica la información de un empleado existente
    private void modifyEmployee() {
        System.out.println("\n--- Modificar Empleado ---");
        System.out.print("ID del Empleado a modificar: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine(); // Consume el salto de línea

        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            System.out.print("Nuevo Nombre (dejar en blanco para no cambiar): ");
            String firstName = scanner.nextLine();
            System.out.print("Nuevo Apellido (dejar en blanco para no cambiar): ");
            String lastName = scanner.nextLine();
            System.out.print("Nuevo Email (dejar en blanco para no cambiar): ");
            String email = scanner.nextLine();
            System.out.print("Nuevo Salario (0 para no cambiar): ");
            double salary = scanner.nextDouble();

            // Actualización condicional de los datos
            if (!firstName.isEmpty()) employee.setFirstName(firstName);
            if (!lastName.isEmpty()) employee.setLastName(lastName);
            if (!email.isEmpty()) employee.setEmail(email);
            if (salary != 0) employee.setSalary(salary);

            employeeService.updateEmployee(employee);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    // Consulta un empleado por su ID
    private void consultEmployee() {
        System.out.println("\n--- Consultar Empleado ---");
        System.out.print("ID del Empleado a consultar: ");
        Long employeeId = scanner.nextLong();

        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            System.out.println("Información del Empleado:");
            System.out.println(employee);
            // Aquí se podría añadir la lógica para mostrar los proyectos asignados al empleado.
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    // Elimina un empleado por su ID
    private void deleteEmployee() {
        System.out.println("\n--- Eliminar Empleado ---");
        System.out.print("ID del Empleado a eliminar: ");
        Long employeeId = scanner.nextLong();

        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            System.out.print("¿Estás seguro de eliminar al empleado? (S/N): ");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("S")) {
                employeeService.deleteEmployee(employeeId);
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    // Añade un nuevo proyecto
    private void addProject() {
        System.out.println("\n--- Añadir Proyecto ---");
        System.out.print("Nombre del Proyecto: ");
        String name = scanner.nextLine();
        System.out.print("Descripción del Proyecto: ");
        String description = scanner.nextLine();

        Project project = new Project(null, name, description, null);
        projectService.addProject(project);
    }

    // Asigna un proyecto existente a un empleado
    private void assignProject() {
        System.out.println("\n--- Asignar Proyecto a Empleado ---");
        System.out.print("ID del Empleado: ");
        Long employeeId = scanner.nextLong();
        System.out.print("ID del Proyecto: ");
        Long projectId = scanner.nextLong();

        employeeService.assignProjectToEmployee(employeeId, projectId);
    }

    // Crea varios proyectos en masa
    private void bulkCreateProjects() {
        System.out.println("\n--- Crear Proyectos en Masa ---");
        projectService.bulkCreateProjects();
    }
}

