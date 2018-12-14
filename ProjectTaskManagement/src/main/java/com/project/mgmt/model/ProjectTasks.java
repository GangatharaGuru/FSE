package com.project.mgmt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectTasks {
	
	int projectid;
	String projectname;
	String managername;
	private Date startDate;	
	private Date endDate;	
	private int priority;
	private int userId;
	
	List<Tasks> taskslist = new ArrayList<>();

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
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

	public List<Tasks> getTaskslist() {
		return taskslist;
	}

	public void setTaskslist(List<Tasks> taskslist) {
		this.taskslist = taskslist;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserid(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ProjectTasks [projectid=" + projectid + ", projectname=" + projectname + ", managername=" + managername
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority + ", taskslist="
				+ taskslist + "]";
	}
	
	
	
	

}
