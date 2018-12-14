package com.project.mgmt.model;

public class Tasks {
	
	private String Taskname;
	private String Status;
	private int Priority;
	private int userId;
	
	private int taskId;
	
	
	
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskname() {
		return Taskname;
	}
	public void setTaskname(String taskname) {
		Taskname = taskname;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		Priority = priority;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Tasks [Taskname=" + Taskname + ", Status=" + Status + ", Priority=" + Priority + ", userId=" + userId
				+ "]";
	}
	
	
	

}
