package com.project.mgmt.response.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tasks {
	@JsonProperty("TaskId")
	private int taskId;
	
	@JsonProperty("TaskName")
	private String title;
	
	@JsonProperty("ProjectId")
	private int projectId;
	
	@JsonProperty("ProjectName")
	private String projectName;
	
	@JsonProperty("Priority")
	private int priority;
	
	@JsonProperty("ParentTaskId")
	private int parentTaskId;
	
	@JsonProperty("ParentTaskName")
	private String parentTaskName;	
	
	
	@JsonProperty("StartDate")
	private Date startDate;
	
	@JsonProperty("EndDate")
	private Date endDate;
	
	@JsonProperty("ManagerId")
	private int managerId;
	
	@JsonProperty("ManagerName")
	private String managerName;
	
	@JsonProperty("Status")
	private String status;
	
	
	
	
	
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(int parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getParentTaskName() {
		return parentTaskName;
	}
	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}
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
	
	

}
