package com.project.mgmt.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.project.mgmt.model.ParentTask;
import com.project.mgmt.model.Task;


public interface ParentTaskDAO {
	
	public void addParentTask(ParentTask task);
	public void deleteParentTask(ParentTask task);
	public void updateParentTask(ParentTask task);
	public List<Task> listParentTasks(ParentTask task);
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

}
