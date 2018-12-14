package com.project.mgmt.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.project.mgmt.model.Project;
import com.project.mgmt.model.ProjectTasks;
import com.project.mgmt.model.Projects;

public interface ProjectDAO {
	
	public int addProject(Projects project);
	public void deleteProject(Project project);
	public void updateProject(Projects project);
	public List<Projects> listProjects(Projects project);
	public Projects getProject(int projectId);
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	public List<ProjectTasks> listProjectsDetails();

}
