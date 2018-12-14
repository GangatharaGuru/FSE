package com.project.mgmt.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.project.mgmt.services.ManagerService;
import com.project.mgmt.services.ParentTaskService;
import com.project.mgmt.services.ProjectService;
import com.project.mgmt.services.TaskService;
import com.project.mgmt.services.UserService;

@Configuration
public class JerceyConfig extends ResourceConfig {
	
	public JerceyConfig(){		
		registerClasses(CORSResponseFilter.class,ProjectService.class,ParentTaskService.class,UserService.class,TaskService.class,ManagerService.class);
	}

}
