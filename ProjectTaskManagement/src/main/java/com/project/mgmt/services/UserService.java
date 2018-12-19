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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.mgmt.dao.UserDAO;
import com.project.mgmt.model.User;

@Component
@Path("/users")
public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	UserDAO userDAO;

	
	@GET
	@Path("/listUsers")
	@Produces("application/json")
	//@CrossOrigin(origins = "http://localhost:4200")
	public List<User> listUsers(){	
		logger.debug("calling service listUsers");
		List<User> users =userDAO.listUsers(null);		
		return users;
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	//@CrossOrigin(origins = "http://localhost:4200")
	public User getUser(@PathParam("id")  int id){	
		logger.debug("calling service getUser="+id);
		//System.out.println("id="+id);
		User user =userDAO.getUser(id);		
		return user;
	}
	
	
	@POST
	@Path("/addUser")
	@Produces("application/text")
	@Consumes("application/json")
	//@CrossOrigin(origins = "http://localhost:4200/")
	public Response addUser(User user){	
		
		logger.debug("calling service addUser="+user);
		if(user.getUserId() <= 0){
			userDAO.addUser(user);
		}else{
			userDAO.updateUser(user);
		}
		//return user.getFirstName()+" "+user.getLastName()+" has been added successfully";
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/updateUser")
	@Produces("application/text")
	@Consumes("application/json")
	public String updateUser(User user){
		logger.debug("calling service updateUser="+user);
		userDAO.updateUser(user);;		
		return user.getFirstName()+" "+user.getLastName()+" details have been updated successfully";
	}
	
	@POST
	@Path("/delete/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response delete(@PathParam("id")  int id){	
		logger.debug("calling service updateUser="+id);
		User user = new User();
		user.setUserId(id);
		//System.out.println(user);
		userDAO.deleteUser(user);;		
		return Response.status(200).build();
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
}
