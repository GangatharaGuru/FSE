package com.project.mgmt.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.mgmt.dao.ManagerDAO;
import com.project.mgmt.dao.impl.UserDAOImpl;
import com.project.mgmt.model.Manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
@Path("/managers")
public class ManagerService {
	
	private static final Logger logger = LogManager.getLogger(ManagerService.class);
	
	@Autowired
	ManagerDAO managerDao;
	
	@POST
	@Path("/addManager")
	@Produces("application/text")
	@Consumes("application/json")
	public String addManager(Manager manager){		
		managerDao.addManager(manager);		
		return manager.getManagerName()+" has been added successfully"; 		
	}
	
	@GET
	@Path("/listManagers")
	@Produces("application/json")		
	public List<Manager> listManagers(){
		List<Manager> managers =managerDao.listManagers(null);
		return managers;
	}

}
