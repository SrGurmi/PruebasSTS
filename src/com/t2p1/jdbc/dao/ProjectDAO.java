package com.t2p1.jdbc.dao;

import com.t2p1.jdbc.models.Project;
import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO {
    void createProject(Project project) throws SQLException;
    Project getProjectById(Long projectId) throws SQLException;
    List<Project> getAllProjects() throws SQLException;
    void updateProject(Project project) throws SQLException;
    void deleteProject(Long projectId) throws SQLException;
}
