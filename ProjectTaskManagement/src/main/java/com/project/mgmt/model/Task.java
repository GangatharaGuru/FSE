package com.project.mgmt.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("TaskId")
	private int TaskId;
	
	@JsonProperty("TaskName")
	private String TaskName;	
	
	@JsonProperty("ProjectId")
	private int ProjectId;
	
	@JsonProperty("ProjectName")
	private String ProjectName;
	
	@JsonProperty("IsParentTask")
	private boolean IsParentTask; 
	
	@JsonProperty("Priority")
	private int Priority;
	
	@JsonProperty("ParentTaskId")
	private int ParentTaskId;
	
	@JsonProperty("ParentTaskName")
	private String ParentTaskName;
	
	
	@JsonProperty("StartDate")
	private Date StartDate;
	

	@JsonProperty("Status")
	private String Status;
	
	@JsonProperty("EndDate")
	private Date EndDate;
	
	@JsonProperty("ManagerName")
	private String ManagerName;
	
	@JsonProperty("ManagerId")
	private int ManagerId;

	public int getTaskId() {
		return TaskId;
	}

	public void setTaskId(int taskId) {
		TaskId = taskId;
	}

	public String getTaskName() {
		return TaskName;
	}

	public void setTaskName(String taskName) {
		TaskName = taskName;
	}

	public int getProjectId() {
		return ProjectId;
	}

	public void setProjectId(int projectId) {
		ProjectId = projectId;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public boolean isIsParentTask() {
		return IsParentTask;
	}

	public void setIsParentTask(boolean isParentTask) {
		IsParentTask = isParentTask;
	}

	public int getPriority() {
		return Priority;
	}

	public void setPriority(int priority) {
		Priority = priority;
	}

	public int getParentTaskId() {
		return ParentTaskId;
	}

	public void setParentTaskId(int parentTaskId) {
		ParentTaskId = parentTaskId;
	}

	public String getParentTaskName() {
		return ParentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		ParentTaskName = parentTaskName;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		this.StartDate = startDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public String getManagerName() {
		return ManagerName;
	}

	public void setManagerName(String managerName) {
		ManagerName = managerName;
	}

	public int getManagerId() {
		return ManagerId;
	}

	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}

	@Override
	public String toString() {
		return "Task [TaskId=" + TaskId + ", TaskName=" + TaskName + ", ProjectId=" + ProjectId + ", ProjectName="
				+ ProjectName + ", IsParentTask=" + IsParentTask + ", Priority=" + Priority + ", ParentTaskId="
				+ ParentTaskId + ", ParentTaskName=" + ParentTaskName + ", startDate=" + StartDate + ", Status="
				+ Status + ", EndDate=" + EndDate + ", ManagerName=" + ManagerName + ", ManagerId=" + ManagerId + "]";
	}
	

}
