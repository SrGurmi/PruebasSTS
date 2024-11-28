package com.t2p1.jdbc.services;

import com.t2p1.jdbc.dao.ProjectDAO;
import com.t2p1.jdbc.dao.ProjectDAOImpl;
import com.t2p1.jdbc.models.Project;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private ProjectDAO projectDAO;

    public ProjectService() throws SQLException {
        this.projectDAO = new ProjectDAOImpl();
    }

    // Método para añadir un proyecto
    public void addProject(Project project) {
        try {
            projectDAO.createProject(project);
            System.out.println("Proyecto añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir proyecto: " + e.getMessage());
        }
    }

    // Método para obtener un proyecto por ID
    public Project getProjectById(Long projectId) {
        try {
            return projectDAO.getProjectById(projectId);
        } catch (SQLException e) {
            System.out.println("Error al consultar proyecto: " + e.getMessage());
            return null;
        }
    }

    // Método para obtener todos los proyectos
    public void getAllProjects() {
        try {
            List<Project> projects = projectDAO.getAllProjects();
            if (projects.isEmpty()) {
                System.out.println("No hay proyectos registrados.");
            } else {
                System.out.println("Lista de Proyectos:");
                for (Project project : projects) {
                    System.out.println(project);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de proyectos: " + e.getMessage());
        }
    }

    // Método para actualizar un proyecto
    public void updateProject(Project project) {
        try {
            projectDAO.updateProject(project);
            System.out.println("Proyecto actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar proyecto: " + e.getMessage());
        }
    }

    // Método para eliminar un proyecto por ID
    public void deleteProject(Long projectId) {
        try {
            projectDAO.deleteProject(projectId);
            System.out.println("Proyecto eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar proyecto: " + e.getMessage());
        }
    }

    // Método para crear proyectos en masa
    public void bulkCreateProjects() {
        try {
            List<Project> projects = List.of(
                new Project(null, "Proyecto A", "Descripción A", null),
                new Project(null, "Proyecto B", "Descripción B", null),
                new Project(null, "Proyecto C", "Descripción C", null),
                new Project(null, "Proyecto D", "Descripción D", null),
                new Project(null, "Proyecto E", "Descripción E", null)
            );

            for (Project project : projects) {
                projectDAO.createProject(project);
            }
            System.out.println("Proyectos creados en masa correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear proyectos en masa: " + e.getMessage());
        }
    }
}
