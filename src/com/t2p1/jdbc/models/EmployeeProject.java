package com.t2p1.jdbc.models;

public class EmployeeProject {
    private Long employeeId;
    private Long projectId;

    public EmployeeProject(Long employeeId, Long projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

    
}
