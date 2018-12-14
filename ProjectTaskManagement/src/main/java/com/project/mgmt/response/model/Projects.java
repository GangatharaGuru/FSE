package com.project.mgmt.response.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Projects {
	
	@JsonProperty("ProjectId")
	private int projectId;
	
	@JsonProperty("ProjectName")
	private String title;
	
	@JsonProperty("StartDate")
	private Date startDate;
	
	@JsonProperty("EndDate")
	private Date endDate;
	
	@JsonProperty("Priority")
	private int priority;
	
	@JsonProperty("Manager")
	private String manager;
	
	@JsonProperty("UserId")
	private int userId;
	
	@JsonProperty("NumberOfTasks")
	private int numberOfTasks;
	
	@JsonProperty("Completed")
	private String isCompleted;
	
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNumberOfTasks() {
		return numberOfTasks;
	}
	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}
	public String getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}	
	

}
