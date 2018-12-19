package com.project.mgmt;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.mgmt.dao.impl.ParentTaskDAOImpl;
import com.project.mgmt.dao.impl.ProjectDAOImpl;
import com.project.mgmt.dao.impl.TaskDAOImpl;
import com.project.mgmt.dao.impl.UserDAOImpl;
import com.project.mgmt.model.ParentTask;
import com.project.mgmt.model.Project;
import com.project.mgmt.model.Projects;
import com.project.mgmt.model.Task;
import com.project.mgmt.model.User;
import com.project.mgmt.response.model.Tasks;
import com.project.mgmt.services.ParentTaskService;
import com.project.mgmt.services.ProjectService;
import com.project.mgmt.services.TaskService;
import com.project.mgmt.services.UserService;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "application.properties")
//@ContextConfiguration(classes = ApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTaskManagementApplicationTests {
	
	
	@Autowired
	DataSource datasource;
	@Autowired
	UserDAOImpl userDAOImpl;
	@Autowired
	ParentTaskDAOImpl parentTaskDAOImpl;
	@Autowired
	ProjectDAOImpl projectDAOImpl;	
	@Autowired
	TaskDAOImpl taskdaoImpl;
	
	/*@Mock
	ManagerDAO managerDao;
	*/
	
	UserService userservice = new UserService();
	ParentTaskService parentTaskService = new ParentTaskService();
	ProjectService projectService = new ProjectService();	
	TaskService taskService = new TaskService();
	User user;
	
	@Before
	public void init() throws Exception{		
		//System.out.println(datasource.getConnection());
		//service.setManagerDao(managerDAOImpl);
		userservice.setUserDAO(userDAOImpl);		
		parentTaskService.setParentTaskDAO(parentTaskDAOImpl);
		projectService.setProjectDAO(projectDAOImpl);
		projectService.setUserDAO(userDAOImpl);
		taskService.setUserDAO(userDAOImpl);
		taskService.setTaskDAO(taskdaoImpl);
		taskService.setParentTaskDAO(parentTaskDAOImpl);
	}
	
	@Test
	public void aaddProjectTest() throws Exception{	
		Projects project = new Projects();
		project.setProjectName("Test Project");
		
		 java.util.Date startDate = new  java.util.Date();
		 startDate.setDate(15);
		 startDate.setMonth(6);
		 startDate.setYear(2018);
		 
		 java.util.Date endDate = new  java.util.Date();
		 endDate.setDate(15);
		 endDate.setMonth(8);
		 endDate.setYear(2018);
		 
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		project.setPriority(18);
		List<Projects> projectslist = projectService.addProject(project);
		assertEquals(true, projectslist != null && projectslist.size()>0);	
	}
	
	@Test
	public void blistProjectsTest() throws Exception{
		//Thread.sleep(60000);
		List<Projects> projectslist = projectService.listProjects();
		assertEquals(true, projectslist != null && projectslist.size()>0);
		//to test GetProject
		Projects project  = projectslist.get(projectslist.size() -1);		
		Projects projects = projectService.getProject(project.getProjectId());
		assertEquals(project.getProjectName(), projects.getProjectName());
		//to test updateProject
		project.setProjectName("Test Updated Project");
		project.setPriority(27);
		String result = projectService.updateProject(project);
		assertEquals("Test Updated Project details have been updated successfully", result);
		
		List<Projects> chkupdateaddproject = projectService.addProject(project);
		assertEquals(true, projectslist != null && projectslist.size()>0);
		
		
		//to test Delete Project
		Project project1 = new Project();
		project1.setProjectId(projects.getProjectId());
		project1.setTitle("Test Updated Project");
		String deleteresult = projectService.deleteProject(project1);	
		
		assertEquals("Test Updated Project has been deleted successfully", deleteresult);
	}	
	
	
	// unit testcases for Tasks	
	@Test
	public void caddTaskTest(){
		Projects project =addProject();
		Task parenttask = addParentTask();
		
		Task task = new Task();	
		
		task.setTaskName("Test Task");
		task.setProjectId(project.getProjectId());
		task.setProjectName(project.getProjectName());		
		//task.setIsParentTask(isParentTask);
		task.setPriority(3);
		task.setParentTaskId(parenttask.getParentTaskId());
		task.setParentTaskName(parenttask.getParentTaskName());
		 java.util.Date startDate = new  java.util.Date();
		 startDate.setDate(15);
		 startDate.setMonth(6);
		 startDate.setYear(2018);		 
		 java.util.Date endDate = new  java.util.Date();
		 endDate.setDate(15);
		 endDate.setMonth(8);
		 endDate.setYear(2018);
		task.setStartDate(startDate);
		task.setStatus("Inprogress");
		task.setEndDate(endDate);			
		Response response =taskService.addTask(task);
		assertEquals(200,response.getStatus());
		
		List<Projects> projectDetails =projectService.listProjectsDetails();
		assertEquals(true, projectDetails != null && projectDetails.size()>0);	
		
		List<Task> tasks = taskService.listTasks();
		Task newTask = tasks.get(0);
		
		User user1 = new User();		
		user1.setFirstName("Test");
		user1.setLastName("User");
		user1.setEmployeeId(101);
		user1.setProjectId(newTask.getProjectId());
		user1.setTaskId(newTask.getTaskId());		
		Response userresponse =userservice.addUser(user1);
		assertEquals(200,userresponse.getStatus());
		
		List<Tasks> taskslist =taskService.listTasksDetails();
		assertEquals(true, taskslist != null && taskslist.size()>0);
		
		User  user2 =userDAOImpl.getUserbyProjectId(newTask.getProjectId());
		assertEquals(user2.getProjectId(), newTask.getProjectId());
		
		User  user4 =userDAOImpl.getUserbyTaskId(newTask.getTaskId());		
		assertEquals(user4.getTaskId(), newTask.getTaskId());
		
		List<User> userslist = userservice.listUsers();		
		if(userslist != null && userslist.size()>0){
			User user3 = userslist.get(userslist.size()-1);
			Response deleteresponse =userservice.delete(user3.getUserId());		
			assertEquals(200,deleteresponse.getStatus());
		}
		
		
		
	}
	
	public Projects addProject(){
		Projects project = new Projects();
		project.setProjectName("Test Project");
		
		 java.util.Date startDate = new  java.util.Date();
		 startDate.setDate(15);
		 startDate.setMonth(6);
		 startDate.setYear(2018);
		 
		 java.util.Date endDate = new  java.util.Date();
		 endDate.setDate(15);
		 endDate.setMonth(8);
		 endDate.setYear(2018);
		 
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		project.setPriority(18);
		List<Projects> projectslist = projectService.addProject(project);
		return projectslist.get(projectslist.size()-1);
		
	}
	
	public Task addParentTask(){
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTaskTitle("Test ParentTask");
		String result = parentTaskService.addParentTask(parentTask);
		List<Task> parenttasklist = parentTaskService.listParentTasks();
		return parenttasklist.get(parenttasklist.size()-1);
	}
	
	@Test
	public void dlistTasksTest(){
		List<Task> tasks = taskService.listTasks();
		//System.out.println(tasks);
		assertEquals(true, tasks != null && tasks.size()>0);
		Task task = tasks.get(tasks.size()-1);
		Task result = taskService.getTask(task.getTaskId());
		assertEquals(task.getTaskName(),result.getTaskName());
		//addtask test
		Task addtask = taskService.getTask(task.getTaskId());
		Response updateTask =taskService.addTask(addtask);
		assertEquals(200,updateTask.getStatus());
		//tets for complteTask
		List<Task> alltasks  =taskService.complete(task);
		Task complteresult = taskService.getTask(task.getTaskId());
		assertEquals("Completed",complteresult.getStatus());
		//to Update Task
		task.setTaskName("Test Updated Task");		
		String updateresult = taskService.updateTask(task);
		assertEquals(updateresult,task.getTaskName()+" details have been updated successfully");
		//to delete task
		String deletetionresult =taskService.deleteTask(task);	
		
		List<Projects> projectslist = projectService.listProjects();
		assertEquals(true, projectslist != null && projectslist.size()>0);
		//to test GetProject
		Projects projects  = projectslist.get(projectslist.size() -1);
		Project project1 = new Project();
		project1.setProjectId(projects.getProjectId());		
		String deleteresult = projectService.deleteProject(project1);	
		//projectDAOImpl.deleteAllData();
		//parentTaskDAOImpl.deleteAllData();
		List<Task> parenttasklist = parentTaskService.listParentTasks();
		Task task1 = parenttasklist.get(parenttasklist.size()-1);		
		ParentTask parenttask = new ParentTask();
		parenttask.setParentTaskId(task1.getParentTaskId());
		String deletionresult = parentTaskService.deleteProject(parenttask);
		assertEquals(deletetionresult,task.getTaskName()+" has been deleted successfully");
	}
	
	
	
	@Test
	public void eaddParentTaskTest() throws Exception{	
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTaskTitle("Test ParentTask");
		String result = parentTaskService.addParentTask(parentTask);
		assertEquals("Test ParentTask has been added successfully", result);	
		
		Task task = new Task();
		task.setParentTaskName("Test ParenTask");
		Response updateTask =taskService.addTask(task);
		assertEquals(200,updateTask.getStatus());
	}
	
	@Test
	public void fgetParentTasksTest(){
		List<Task> tasks =taskService.listParentTasksAll();
		assertEquals(true, tasks != null && tasks.size()>0);		
	}	
	
	@Test
	public void glistParentTaskTest() throws Exception{
		List<Task> parenttasklist = parentTaskService.listParentTasks();
		assertEquals(true, parenttasklist != null && parenttasklist.size()>0);	
		//for Update Task
		Task task = parenttasklist.get(parenttasklist.size()-1);
		Task task2 = parenttasklist.get(parenttasklist.size()-2);
		
		ParentTask parenttask = new ParentTask();
		parenttask.setParentTaskId(task.getParentTaskId());
		parenttask.setParentTaskTitle("Test Updated ParentTask");
		
		String result = parentTaskService.updateParentTask(parenttask);
		assertEquals("Test Updated ParentTask details have been updated successfully", result);	
		// listTask Details tes
		
		List<Task> parenttaskslist = taskService.listParentTasksAll();
		assertEquals(true, parenttaskslist != null && parenttaskslist.size()>0);	
		//for delete parenttask
		String deletionresult = parentTaskService.deleteProject(parenttask);
		assertEquals("Test Updated ParentTask has been deleted successfully", deletionresult);	
		ParentTask parenttask2 = new ParentTask();
		parenttask2.setParentTaskId(task2.getParentTaskId());
		String deletionresult1 = parentTaskService.deleteProject(parenttask2);
	}
	// For manager services

	
	
	@Test
	public void haddUserTest() throws Exception{		
		User user1 = new User();		
		user1.setFirstName("Test");
		user1.setLastName("User");
		user1.setEmployeeId(101);
		user1.setProjectId(201);
		user1.setTaskId(301);		
		Response response =userservice.addUser(user1);
		assertEquals(200,response.getStatus());
	}
	
	@Test
	public void	ilistgetupdateUsersTest()throws Exception{
		List<User> userslist = userservice.listUsers();		
		if(userslist != null && userslist.size()>0){
			user = userslist.get(userslist.size()-1);
		}		
		assertEquals(true, userslist != null && userslist.size()>0);
		//get user test
		User user1 = userservice.getUser(user.getUserId());			
		assertEquals(true, user1 != null && (user.getUserId() == user1.getUserId()));
		// to update user
		user1.setFirstName("Test");
		user1.setLastName("Updated User");		
		user1.setTaskId(302);		
		String response =userservice.updateUser(user1);
		assertEquals(true,response.contains("details have been updated successfully"));
		Response deleteresponse =userservice.delete(user1.getUserId());		
		assertEquals(200,deleteresponse.getStatus());
		
	}
	/*@Test
	public void uaddManagerTest() throws Exception{
		//assertArrayEquals(true, true);			
		Manager manager = new Manager();
		manager.setManagerName("test");
		manager.setProjectId(1);		
		String result=service.addManager(manager);
		System.out.println(result);		
		assertEquals("test has been added successfully", result);		
				
	}
	
	@Test
	public void vlistManagersTest(){
		List<Manager> managers =service.listManagers();
		assertEquals(true, managers != null && managers.size()>0);
	}
	*/

}
