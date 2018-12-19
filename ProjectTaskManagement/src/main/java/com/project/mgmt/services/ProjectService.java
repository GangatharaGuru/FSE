package com.project.mgmt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.mgmt.dao.ProjectDAO;
import com.project.mgmt.dao.UserDAO;
import com.project.mgmt.model.Project;
import com.project.mgmt.model.ProjectTasks;
import com.project.mgmt.model.Projects;
import com.project.mgmt.model.Tasks;
import com.project.mgmt.model.User;

@Component
@Path("/projects")
public class ProjectService {	
	private static final Logger logger = LogManager.getLogger(ProjectService.class);
	
	@Autowired
	ProjectDAO projectDAO;
	
	@Autowired
	UserDAO userDAO;

	
	@GET
	@Path("/listProjects")
	@Produces("application/json")
	public List<Projects> listProjects(){	
		logger.debug("calling service listProjects");
		List<Projects> projects =projectDAO.listProjects(null);		
		return projects;
	}
	
	
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Projects getProject(@PathParam("id")  int id){	
		logger.debug("calling service getProject id="+id);
		Projects project =projectDAO.getProject(id);	
		//System.out.println(project);
		return project;
	}
	
	@GET
	@Path("/listProjectsDetails")
	@Produces("application/json")
	public List<Projects> listProjectsDetails(){
		logger.debug("calling service listProjectsDetails");
		List<Projects> projectdetails = new ArrayList<>();
		List<ProjectTasks> projects =projectDAO.listProjectsDetails();
		
		for(ProjectTasks tasks:projects){
			Projects project = new Projects();
			project.setProjectId(tasks.getProjectid());
			project.setProjectName(tasks.getProjectname());
			project.setStartDate(tasks.getStartDate());
			project.setEndDate(tasks.getEndDate());
			project.setPriority(tasks.getPriority());
			project.setManager(tasks.getManagername());
			project.setUserId(tasks.getUserId());
			project.setNumberOfTasks(tasks.getTaskslist().size());
			
			List<Tasks> pendingtasklsist =tasks.getTaskslist().stream().filter(task ->{
				if(task.getStatus()!= null && !task.getStatus().equalsIgnoreCase("completed"))
				{
					return true;
				}
				return false;
			}).collect(Collectors.toList());
			
			if(pendingtasklsist !=null && pendingtasklsist.size()!=0){
			project.setCompleted("No");
			}else{
				project.setCompleted("Yes");	
			}
			projectdetails.add(project);
			
		}	
		
		return projectdetails;
	}
	
	





	@POST
	@Path("/addProject")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Projects> addProject(Projects project){	
		
		logger.debug("calling service deleteProject");
		if(project.getProjectId() <= 0){
			int projectid =projectDAO.addProject(project);
			User user1 =userDAO.getUserbyProjectId(projectid);
			user1.setProjectId(0);
			userDAO.updateUser(user1);
			
			User user =userDAO.getUser(project.getUserId());
			user.setProjectId(projectid);
			userDAO.updateUser(user);
			
		}else{			
			User user1 =userDAO.getUserbyProjectId(project.getProjectId());
			user1.setProjectId(0);
			userDAO.updateUser(user1);
			
			projectDAO.updateProject(project);			
			
			User user =userDAO.getUser(project.getUserId());
			user.setProjectId(project.getProjectId());
			userDAO.updateUser(user);
		}
		//return project.getProjectName() +" has been added successfully";
		List<Projects> projects =projectDAO.listProjects(null);		
		return projects;
		//return Response.status(200).build();
	}
	
	@PUT
	@Path("/updateProject")
	@Produces("application/text")
	@Consumes("application/json")
	public String updateProject(Projects project){		
		logger.debug("calling service updateProject");
		projectDAO.updateProject(project);		
		return project.getProjectName()+" details have been updated successfully";
	}
	
	@DELETE
	@Path("/deleteTask")
	@Produces("application/text")
	@Consumes("application/json")
	public String deleteProject(Project project){	
		logger.debug("calling service deleteProject");
		projectDAO.deleteProject(project);;		
		return project.getTitle()+" has been deleted successfully";
	}
	
	
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
