package com.project.mgmt.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.project.mgmt.model.Task;
import com.project.mgmt.response.model.Tasks;

public interface TaskDAO {
	
	public int addTask(Task task);
	public void deleteTask(Task task);
	public void updateTask(Task task);
	public List<Task> listTasks(Task task);
	public Task getTask(int taskId);
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	public List<Tasks> listTaskswithdetails();

}
