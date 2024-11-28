package com.t2p1.jdbc.models;

import java.sql.Timestamp;

public class Project {
    private Long projectId;
    private String name;
    private String description;
    private Timestamp lastUpdate;

    public Project(Long projectId, String name, String description, Timestamp lastUpdate) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.lastUpdate = lastUpdate;
    }

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    
}
