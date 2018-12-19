package com.project.mgmt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.project.mgmt.dao.ParentTaskDAO;
import com.project.mgmt.model.ParentTask;
import com.project.mgmt.model.Task;

public class ParentTaskDAOImpl implements ParentTaskDAO {
	private static final Logger logger = LogManager.getLogger(ParentTaskDAOImpl.class);
	
	JdbcTemplate jdbcTemplate;
	private String INSERT_SQL = "INSERT INTO PARENTTASK(PARENTTASKTITLE) VALUES(?)";
	
	private String DELETE_SQL = "DELETE FROM PARENTTASK WHERE PARENTTASKID=?";
	
	private String UPDATE_SQL = "UPDATE PARENTTASK SET PARENTTASKTITLE=? WHERE PARENTTASKID=?";

	@Override
	public void addParentTask(ParentTask task) {
		// TODO Auto-generated method stub		
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
			try{
			jdbcTemplate.update(INSERT_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
				//	pstmt.setInt(1, task.getParentTaskId());					
					pstmt.setString(1, task.getParentTaskTitle());					
				}
			} );
			
		}catch(Exception ex){
			logger.error("Got Errorin  addParentTask:"+ex.getMessage());
		}

	}

	@Override
	public void deleteParentTask(ParentTask task) {
		// TODO Auto-generated method stub		
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
		try{
			jdbcTemplate.update(DELETE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub					
					pstmt.setInt(1, task.getParentTaskId());

				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  deleteParentTask:"+ex.getMessage());
		}

	}

	@Override
	public void updateParentTask(ParentTask task) {
		// TODO Auto-generated method stub		
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
		try{
			jdbcTemplate.update(UPDATE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					pstmt.setString(1, task.getParentTaskTitle());	
					pstmt.setInt(2, task.getParentTaskId());

				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  updateParentTask:"+ex.getMessage());
		}

	}

	@Override
	public List<Task> listParentTasks(ParentTask task) {
		// TODO Auto-generated method stub
		List<Task> parentTasks = new ArrayList<>();
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
		//	logger.debug("Connection="+connection);
		try{
			parentTasks =	jdbcTemplate.query("select * from parenttask", new RowMapper<Task>(){
				@Override
				public Task mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Task parentTask = new Task();	
					parentTask.setParentTaskId(rs.getInt(1));
					parentTask.setParentTaskName(rs.getString(2));
					return parentTask;
				}
				
			});				

		}catch(Exception ex){
			logger.error("Got Errorin  listParentTasks:"+ex.getMessage());
		}
			
	return parentTasks;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
