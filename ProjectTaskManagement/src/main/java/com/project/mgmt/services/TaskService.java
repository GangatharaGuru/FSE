package com.project.mgmt.services;

import java.util.List;

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

import com.project.mgmt.dao.ParentTaskDAO;
import com.project.mgmt.dao.TaskDAO;
import com.project.mgmt.dao.UserDAO;
import com.project.mgmt.model.ParentTask;
import com.project.mgmt.model.Task;
import com.project.mgmt.model.User;
import com.project.mgmt.response.model.Tasks;

@Component
@Path("/tasks")
public class TaskService {
	
	private static final Logger logger = LogManager.getLogger(TaskService.class);
	
	
	@Autowired
	TaskDAO taskDAO;
	
	@Autowired
	ParentTaskDAO parentTaskDAO;
	
	@Autowired
	UserDAO userDAO;

	
	@GET
	@Path("/listTasks")
	@Produces("application/json")
	public List<Task> listTasks(){		
		logger.debug("calling service listTasks");
		List<Task> tasks =taskDAO.listTasks(null);		
		return tasks;
	}
	
	
	@GET
	@Path("/listParentTasksAll")
	@Produces("application/json")
	public List<Task> listParentTasksAll(){	
		logger.debug("calling service listParentTasksAll");
		List<Task> tasks =parentTaskDAO.listParentTasks(null);
		//System.out.println("listParentTasksAll="+tasks);
		return tasks;
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Task getTask(@PathParam("id") int taskid){	
		logger.debug("calling service getTask taskid="+taskid);
		Task task =taskDAO.getTask(taskid);		
		return task;
	}
	
	@GET
	@Path("/listTasksDetails")
	@Produces("application/json")
	public List<Tasks> listTasksDetails(){
		logger.debug("calling service listTasksDetails");
		List<Tasks> taskslist =taskDAO.listTaskswithdetails();		
		return taskslist;
	}
	
	
	
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}


	public void setParentTaskDAO(ParentTaskDAO parentTaskDAO) {
		this.parentTaskDAO = parentTaskDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	@POST
	@Path("/addTask")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addTask(Task task){	
		logger.debug("calling service addTask="+task);
		if(task.getStartDate()==null && task.getEndDate() == null && task.getTaskId() ==0){
			ParentTask parentTask = new ParentTask();
			parentTask.setParentTaskTitle(task.getTaskName());
			parentTaskDAO.addParentTask(parentTask);
		}else{
			if(task.getTaskId() <= 0){
				// taskDAO.addTask(task);
				int taskid = taskDAO.addTask(task);
				User user =userDAO.getUser(task.getManagerId());
				user.setProjectId(task.getProjectId());
				user.setTaskId(taskid);				
				userDAO.updateUser(user);
			}else{			 
				taskDAO.updateTask(task);
				User user =userDAO.getUserbyTaskId(task.getTaskId());
				user.setTaskId(0);
				userDAO.updateUser(user);
				
				User user1 =userDAO.getUser(task.getManagerId());
				user1.setProjectId(task.getProjectId());
				user1.setTaskId(task.getTaskId());				
				userDAO.updateUser(user1);
			}			
		}
		
		
		return Response.status(200).build();
	}
	
	@POST
	@Path("/complete")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Task> complete(Task task){
		logger.debug("calling service complete="+task);
		task.setStatus("Completed");
		taskDAO.updateTask(task);
		List<Task> tasks =taskDAO.listTasks(null);		
		return tasks;
	}
	
	@PUT
	@Path("/updateTask")
	@Produces("application/text")
	@Consumes("application/json")
	public String updateTask(Task task){		
		logger.debug("calling service updateTask="+task);
		taskDAO.updateTask(task);		
		return task.getTaskName()+" details have been updated successfully";
	}
	
	
	@DELETE
	@Path("/deleteTask")
	@Produces("application/text")
	@Consumes("application/json")
	public String deleteTask(Task task){
		logger.debug("calling service deleteTask="+task);
		taskDAO.deleteTask(task);;		
		return task.getTaskName()+" has been deleted successfully";
	}
}
