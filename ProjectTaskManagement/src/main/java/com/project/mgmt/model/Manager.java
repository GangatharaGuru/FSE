package com.project.mgmt.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Manager {
	
	@JsonProperty("ManagerId")
	private int managerId;
	
	@JsonProperty("ManagerName")
	private String managerName;
	
	@JsonProperty("ProjectId")
	private int projectId;
	
	

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	

}
