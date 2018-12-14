package com.project.mgmt.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.project.mgmt.model.Manager;

public interface ManagerDAO {
	
	public void addManager(Manager manager);
	public void deleteManager(Manager manager);
	public void updateManager(Manager manager);
	public List<Manager> listManagers(Manager manager);
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

}
