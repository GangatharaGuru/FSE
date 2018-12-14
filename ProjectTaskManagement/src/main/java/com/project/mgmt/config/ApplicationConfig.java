package com.project.mgmt.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.project.mgmt.dao.ManagerDAO;
import com.project.mgmt.dao.ParentTaskDAO;
import com.project.mgmt.dao.ProjectDAO;
import com.project.mgmt.dao.TaskDAO;
import com.project.mgmt.dao.UserDAO;
import com.project.mgmt.dao.impl.ManagerDAOImpl;
import com.project.mgmt.dao.impl.ParentTaskDAOImpl;
import com.project.mgmt.dao.impl.ProjectDAOImpl;
import com.project.mgmt.dao.impl.TaskDAOImpl;
import com.project.mgmt.dao.impl.UserDAOImpl;

@Configuration
public class ApplicationConfig {
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties taskDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean	
	public DataSource taskDataSource() {
		return taskDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(){	
		return new JdbcTemplate(taskDataSource());
	}
	
	@Bean
	public UserDAO userDAO(){
		UserDAO userDAO = new UserDAOImpl();
		userDAO.setJdbcTemplate(jdbcTemplate());
		return userDAO;
	}
	
	@Bean
	public TaskDAO taskDAO(){
		TaskDAO taskDAO = new TaskDAOImpl();
		taskDAO.setJdbcTemplate(jdbcTemplate());
		return taskDAO;
	}
	
	@Bean
	public ProjectDAO projectDAO(){
		ProjectDAO projectDAO = new ProjectDAOImpl();
		projectDAO.setJdbcTemplate(jdbcTemplate());
		return projectDAO;
	}
	
	@Bean
	public ParentTaskDAO parentTaskDAO(){
		ParentTaskDAO parentDAO = new ParentTaskDAOImpl();
		parentDAO.setJdbcTemplate(jdbcTemplate());
		return parentDAO;
	}
	
	@Bean
	public ManagerDAO managerDAO(){
		ManagerDAO managerDAO = new ManagerDAOImpl();
		managerDAO.setJdbcTemplate(jdbcTemplate());
		return managerDAO;
	}
	

}
