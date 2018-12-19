package com.project.mgmt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.project.mgmt.dao.TaskDAO;
import com.project.mgmt.model.Projects;
import com.project.mgmt.model.Task;
import com.project.mgmt.response.model.Tasks;
import com.project.mgmt.util.GenericUtil;

public class TaskDAOImpl implements TaskDAO {
	
	private static final Logger logger = LogManager.getLogger(TaskDAOImpl.class);
	
	JdbcTemplate jdbcTemplate;
	
	private String INSERT_SQL = "INSERT INTO TASKS(PARENTTASKID,PROJECTID,TITLE,STARTDATE,ENDDATE,STATUS,PRIORITY) VALUES(?,?,?,?,?,?,?)";
	
	private String DELETE_SQL = "DELETE FROM TASKS WHERE TASKID=?";
	
	private String UPDATE_SQL = "UPDATE TASKS SET PARENTTASKID=?,PROJECTID=?,TITLE=?,STARTDATE=?,ENDDATE=?,STATUS =?,PRIORITY =? WHERE TASKID=?";
	
	
	private String SELECT_ALL_TASKS_DETAILS ="SELECT A.PROJECTID,A.TITLE,C.STARTDATE,C.ENDDATE,"
											+ "A.PRIORITY,B.FIRSTNAME,C.TASKID,C.TITLE,C.PRIORITY,C.STATUS,D.PARENTTASKID,D.PARENTTASKTITLE "
											+ "FROM PROJECTS A, USERS B , TASKS C,PARENTTASK D "
											+"WHERE A.PROJECTID= B.PROJECTID  AND A.PROJECTID= C.PROJECTID "
											+ "AND D.PARENTTASKID = C.PARENTTASKID ";
	
	/*private String SELECT_TASKS_DETAILS ="SELECT TASKS.TASKID,TASKS.TITLE,TASKS.PROJECTID,PROJECTS.TITLE,TASKS.PRIORITY,"
			+ "TASKS.PARENTTASKID,PARENTTASK.PARENTTASKTITLE,TASKS.STARTDATE,TASKS.STATUS,"
			+"TASKS.ENDDATE,MANAGER.MANAGERNAME,MANAGER.MANAGERID FROM TASKS,PROJECTS,MANAGER,PARENTTASK "
			+ "WHERE TASKS.PROJECTID = PROJECTS.PROJECTID AND MANAGER.PROJECTID = TASKS.PROJECTID "
			+ "AND PARENTTASK.PARENTTASKID=TASKS.PARENTTASKID";*/
	
	
	private String SELECT_TASKS_DETAILS = "SELECT TASKS.TASKID,TASKS.TITLE,TASKS.PROJECTID,PROJECTS.TITLE,TASKS.PRIORITY,"
			+"TASKS.PARENTTASKID,PARENTTASK.PARENTTASKTITLE,TASKS.STARTDATE,TASKS.STATUS,"
			+"TASKS.ENDDATE FROM TASKS,PROJECTS,PARENTTASK "
			+" WHERE TASKS.PROJECTID = PROJECTS.PROJECTID  "
			+" AND PARENTTASK.PARENTTASKID=TASKS.PARENTTASKID";
	
	private String SELECT_TASK_DETAIL = " SELECT TASKS.TASKID,TASKS.TITLE,TASKS.PROJECTID,PROJECTS.TITLE,TASKS.PRIORITY,"
			+"TASKS.PARENTTASKID,PARENTTASK.PARENTTASKTITLE,TASKS.STARTDATE,TASKS.STATUS,"
			+"TASKS.ENDDATE,USERS.USERID,USERS.FIRSTNAME,USERS.LASTNAME FROM TASKS inner join  PROJECTS on(TASKS.PROJECTID = PROJECTS.PROJECTID) "
            +" inner join PARENTTASK on(PARENTTASK.PARENTTASKID=TASKS.PARENTTASKID) "
            +" left outer join USERS ON (USERS.TASKID =TASKS.TASKID) ";

	@Override
	public List<Tasks> listTaskswithdetails() {
		// TODO Auto-generated method stub
		List<Tasks> tasks = null;
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
			//System.out.println("Connection="+connection);			
		try{	
			tasks =	jdbcTemplate.query(SELECT_ALL_TASKS_DETAILS, new RowMapper<Tasks>(){
				@Override
				public Tasks mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Tasks tasks = new Tasks();
					tasks.setProjectId(rs.getInt(1));
					tasks.setTitle(rs.getString(2));
					tasks.setStartDate(GenericUtil.toUtilDate(rs.getDate(3)));
					tasks.setEndDate(GenericUtil.toUtilDate(rs.getDate(4)));
					tasks.setPriority(rs.getInt(5));
					tasks.setManagerName(rs.getString(6));
					tasks.setTaskId(rs.getInt(7));
					tasks.setTitle(rs.getString(8));
					tasks.setPriority(rs.getInt(9));
					//System.out.println(rs.getString(10));
					tasks.setStatus(rs.getString(10));
					tasks.setParentTaskId(rs.getInt(11));
					tasks.setParentTaskName(rs.getString(12));			
					
					return tasks;
				}
				
			});				

		}catch(Exception ex){
			logger.error("Got Errorin  listTaskswithdetails:"+ex.getMessage());
		}
			
	return tasks;
	}
	
	
	@Override
	public int addTask(Task task) {
		// TODO Auto-generated method stub	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int key =0;
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
		try{	
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					
					PreparedStatement pstmt = con
					          .prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
					
					pstmt.setInt(1, task.getParentTaskId());
					pstmt.setInt(2, task.getProjectId());
					pstmt.setString(3, task.getTaskName());
					pstmt.setDate(4, GenericUtil.toSQLDate(task.getStartDate()));
					pstmt.setDate(5, GenericUtil.toSQLDate(task.getEndDate()));					
					pstmt.setString(6, task.getStatus());
					pstmt.setInt(7, task.getPriority());
					return pstmt;
				}
			}, keyHolder);
			logger.debug("key ="+keyHolder.getKey().intValue());
			
		/* key = jdbcTemplate.update(INSERT_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					//pstmt.setInt(1, task.getTaskId());
					pstmt.setInt(1, task.getParentTaskId());
					pstmt.setInt(2, task.getProjectId());
					pstmt.setString(3, task.getTaskName());
					pstmt.setDate(4, GenericUtil.toSQLDate(task.getStartDate()));
					pstmt.setDate(5, GenericUtil.toSQLDate(task.getEndDate()));					
					pstmt.setString(6, task.getStatus());
					pstmt.setInt(7, task.getPriority());
				}
			} );*/
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		return keyHolder.getKey().intValue();
		//return keyHolder.getKey().intValue();
	}

	@Override
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub		
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
		try{
			jdbcTemplate.update(DELETE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub					
					pstmt.setInt(1, task.getTaskId());
				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  deleteTask:"+ex.getMessage());
		}

	}

	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub		
	//	try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
		try{
			jdbcTemplate.update(UPDATE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub						
					pstmt.setInt(1, task.getParentTaskId());
					pstmt.setInt(2, task.getProjectId());
					pstmt.setString(3, task.getTaskName());
					pstmt.setDate(4, GenericUtil.toSQLDate(task.getStartDate()));
					pstmt.setDate(5, GenericUtil.toSQLDate(task.getEndDate()));					
					pstmt.setString(6, task.getStatus());
					pstmt.setInt(7, task.getPriority());
					pstmt.setInt(8, task.getTaskId());

				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  updateTask:"+ex.getMessage());
		}

	}
	/* TaskId: number;
    TaskName: string;
    ProjectId: number;
    ProjectName: string;
    IsParentTask: boolean;
    Priority: number;
    ParentTaskId: number;
    ParentTaskName: string;
    StartDate: Date;
    Status: string;
    EndDate: Date;
    ManagerName: string;
    ManagerId: number;*/
	
	
	@Override
	public List<Task> listTasks(Task task) {
		// TODO Auto-generated method stub
		List<Task> tasks =  new ArrayList<>();;
	//	try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
		try{		
			tasks =	jdbcTemplate.query(SELECT_TASKS_DETAILS, new RowMapper<Task>(){
				@Override
				public Task mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Task task = new Task();	
					
					
					task.setTaskId(rs.getInt(1));
					task.setTaskName(rs.getString(2));
					task.setProjectId(rs.getInt(3));
					task.setProjectName(rs.getString(4));
					
					//task.setIsParentTask(isParentTask);
					task.setPriority(rs.getInt(5));
					task.setParentTaskId(rs.getInt(6));
					task.setParentTaskName(rs.getString(7));
					
					task.setStartDate(rs.getDate(8));
					task.setStatus(rs.getString(9));
					task.setEndDate(rs.getDate(10));	
					//task.setManagerName(rs.getString(11));
					//task.setManagerId(rs.getInt(12));		
					
					
					return task;
				}
				
			});				

		}catch(Exception ex){
			logger.error("Got Errorin  listTasks:"+ex.getMessage());
		}
			
	return tasks;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public Task getTask(int taskId) {
		// TODO Auto-generated method stub
		List<Task> tasks = null;
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
		try{		
			tasks =	jdbcTemplate.query(SELECT_TASK_DETAIL +" where TASKS.TASKID="+taskId, new RowMapper<Task>(){
				@Override
				public Task mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Task task = new Task();	
					
					
					task.setTaskId(rs.getInt(1));
					task.setTaskName(rs.getString(2));
					task.setProjectId(rs.getInt(3));
					task.setProjectName(rs.getString(4));
					
					//task.setIsParentTask(isParentTask);
					task.setPriority(rs.getInt(5));
					task.setParentTaskId(rs.getInt(6));
					task.setParentTaskName(rs.getString(7));
					
					task.setStartDate(rs.getDate(8));
					task.setStatus(rs.getString(9));
					task.setEndDate(rs.getDate(10));
					task.setManagerId(rs.getInt(11));
					if(rs.getString(12) != null && rs.getString(13) !=null){
					task.setManagerName(rs.getString(12) +" "+rs.getString(13));
					}else if(rs.getString(12) != null){
						task.setManagerName(rs.getString(12));
					}else if(rs.getString(13) != null){
						task.setManagerName(rs.getString(13));
					}else{
						task.setManagerName("");
					}
					
					
					return task;
				}
				
			});				

		}catch(Exception ex){
			logger.error("Got Errorin  getTask:"+ex.getMessage());
		}
		
		if(tasks != null && tasks.size() >0){
			return tasks.get(0);
		}
			
	return new Task();
	}


}
