package com.project.mgmt.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Projects {
	

	
	@JsonProperty("ProjectId")
	private int ProjectId;
	
	@JsonProperty("ProjectName")
	private String ProjectName;
	
	@JsonProperty("StartDate")
	private Date StartDate;	
	
	@JsonProperty("EndDate")
	private Date EndDate;	
	
	@JsonProperty("Priority")	
	private int Priority;
	
	@JsonProperty("Manager")
	private String Manager;	
	
	@JsonProperty("UserId")
	private int UserId;	
	@JsonProperty("NumberOfTasks")
	private int NumberOfTasks;
	
	@JsonProperty("Completed")
	private String Completed;
	
	
	public int getProjectId() {
		return ProjectId;
	}
	public void setProjectId(int projectId) {
		this.ProjectId = projectId;
	}
	
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		this.StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		this.EndDate = endDate;
	}
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		this.Priority = priority;
	}
	public String getManager() {
		return Manager;
	}
	public void setManager(String manager) {
		this.Manager = manager;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		this.UserId = userId;
	}
	public int getNumberOfTasks() {
		return NumberOfTasks;
	}
	public void setNumberOfTasks(int numberOfTasks) {
		this.NumberOfTasks = numberOfTasks;
	}
	public String getCompleted() {
		return Completed;
	}
	public void setCompleted(String completed) {
		this.Completed = completed;
	}
	
	@Override
	public String toString() {
		return "Projects [ProjectId=" + ProjectId + ", ProjectName=" + ProjectName + ", StartDate=" + StartDate
				+ ", EndDate=" + EndDate + ", Priority=" + Priority + ", Manager=" + Manager + ", UserId=" + UserId
				+ ", NumberOfTasks=" + NumberOfTasks + ", Completed=" + Completed + "]";
	}

	

}
