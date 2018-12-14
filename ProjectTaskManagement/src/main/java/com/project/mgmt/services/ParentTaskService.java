package com.project.mgmt.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.mgmt.dao.ParentTaskDAO;
import com.project.mgmt.model.ParentTask;
import com.project.mgmt.model.Task;

@Component
@Path("/parentTasks")
public class ParentTaskService {
	private static final Logger logger = LogManager.getLogger(ParentTaskService.class);
	
	@Autowired
	ParentTaskDAO parentTaskDAO;	
	
	
	@GET
	@Path("/listParentTasks")
	@Produces("application/json")
	public List<Task> listParentTasks(){		
		logger.debug("calling service listParentTasks");
		List<Task> parentTasks =parentTaskDAO.listParentTasks(null);		
		return parentTasks;
	}
	
	
	@POST
	@Path("/addParentTask")
	@Produces("application/text")
	@Consumes("application/json")
	public String addParentTask(ParentTask parentTask){	
		logger.debug("calling service addParentTask");
		parentTaskDAO.addParentTask(parentTask);
		return parentTask.getParentTaskTitle() +" has been added successfully";
	}
	
	@PUT
	@Path("/updateParentTask")
	@Produces("application/text")
	@Consumes("application/json")
	public String updateParentTask(ParentTask parentTask){
		logger.debug("calling service updateParentTask");
		parentTaskDAO.updateParentTask(parentTask);;		
		return parentTask.getParentTaskTitle()+" details have been updated successfully";
	}
	
	@DELETE
	@Path("/deleteParentTask")
	@Produces("application/text")
	@Consumes("application/json")
	public String deleteProject(ParentTask parentTask){	
		logger.debug("calling service deleteProject");
		parentTaskDAO.deleteParentTask(parentTask);;		
		return parentTask.getParentTaskTitle()+" has been deleted successfully";
	}

}
