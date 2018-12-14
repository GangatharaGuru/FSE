package com.project.mgmt.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	@JsonProperty("UserId")
	private int UserId;
	
	@JsonProperty("FirstName")
	private String FirstName;
	
	@JsonProperty("LastName")
	private String LastName;
	
	@JsonProperty("EmployeeId")
	private int EmployeeId;
	
	@JsonProperty("ProjectId")
	private int ProjectId;
	
	@JsonProperty("TaskId")
	private int TaskId;
	
	
	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		UserId = userId;
	}


	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public int getEmployeeId() {
		return EmployeeId;
	}


	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}


	public int getProjectId() {
		return ProjectId;
	}


	public void setProjectId(int projectId) {
		ProjectId = projectId;
	}


	public int getTaskId() {
		return TaskId;
	}


	public void setTaskId(int taskId) {
		TaskId = taskId;
	}


	@Override
	public String toString() {
		return "User [userId=" + UserId + ", firstName=" + FirstName + ", lastName=" + LastName + ", employeeId="
				+ EmployeeId + ", projectId=" + ProjectId + ", taskId=" + TaskId + "]";
	}
	
	

}
