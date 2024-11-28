package com.t2p1.jdbc.dao;


import com.t2p1.jdbc.models.Project;
import com.t2p1.jdbc.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {
    private Connection connection;

    public ProjectDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
            if (this.connection == null) {
                throw new SQLException("No se pudo establecer la conexión con la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión en ProjectDAOImpl: " + e.getMessage());
        }
    }

    @Override
    public void createProject(Project project) throws SQLException {
        String sql = "INSERT INTO projects (project_name, project_description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.executeUpdate();
        }
    }


    @Override
    public Project getProjectById(Long projectId) throws SQLException {
        String sql = "SELECT * FROM projects WHERE project_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, projectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(rs.getLong("project_id"),
                                   rs.getString("name"),
                                   rs.getString("description"),
                                   rs.getTimestamp("last_update"));
            }
            return null;
        }
    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                projects.add(new Project(rs.getLong("project_id"),
                                         rs.getString("name"),
                                         rs.getString("description"),
                                         rs.getTimestamp("last_update")));
            }
        }
        return projects;
    }

    @Override
    public void updateProject(Project project) throws SQLException {
        String sql = "UPDATE projects SET name = ?, description = ? WHERE project_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setLong(3, project.getProjectId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteProject(Long projectId) throws SQLException {
        String sql = "DELETE FROM projects WHERE project_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, projectId);
            stmt.executeUpdate();
        }
    }
}


