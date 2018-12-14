package com.project.mgmt.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.project.mgmt.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
	public List<User> listUsers(User user);
	public User getUser(int id);
	public User getUserbyProjectId(int projectId);
	public User getUserbyTaskId(int taskId);
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

}
