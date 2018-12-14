package com.project.mgmt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.project.mgmt.dao.UserDAO;
import com.project.mgmt.model.Projects;
import com.project.mgmt.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAOImpl implements UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
	
	JdbcTemplate jdbcTemplate;
	
	private String INSERT_SQL = "INSERT INTO USERS(FIRSTNAME,LASTNAME,EMPLOYEEID,PROJECTID,TASKID) VALUES(?,?,?,?,?)";
	
	private String DELETE_SQL = "DELETE FROM USERS WHERE USERID=?";
	
	private String UPDATE_SQL = "UPDATE USERS SET FIRSTNAME=?,LASTNAME=?,EMPLOYEEID=?,PROJECTID=?,TASKID=? WHERE USERID=?";
	

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub		
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
			
			jdbcTemplate.update(INSERT_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					//pstmt.setInt(1, user.getUserId());
					pstmt.setString(1, user.getFirstName());
					pstmt.setString(2, user.getLastName());
					pstmt.setInt(3, user.getEmployeeId());
					pstmt.setInt(4, user.getProjectId());
					pstmt.setInt(5, user.getTaskId());
				}
			} );
			
		}catch(Exception ex){
			logger.error("Got Errorin  addUser:"+ex.getMessage());
		}

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub		
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){

			jdbcTemplate.update(DELETE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub					
					pstmt.setInt(1, user.getUserId());

				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  deleteUser:"+ex.getMessage());
		}

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub		
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){

			jdbcTemplate.update(UPDATE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub	
					pstmt.setString(1, user.getFirstName());
					pstmt.setString(2, user.getLastName());
					pstmt.setInt(3, user.getEmployeeId());
					pstmt.setInt(4, user.getProjectId());
					pstmt.setInt(5, user.getTaskId());
					pstmt.setInt(6, user.getUserId());

				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  updateUser:"+ex.getMessage());
		}

	}

	@Override
	public List<User> listUsers(User user) {
		// TODO Auto-generated method stub		
		List<User> users = new ArrayList<>();;
			try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
			//;	System.out.println("Connection="+connection);			
				 users =	jdbcTemplate.query("select * from users", new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						User user1 = new User();
						user1.setUserId(rs.getInt(1));
						user1.setFirstName(rs.getString(2));
						user1.setLastName(rs.getString(3));
						user1.setEmployeeId(rs.getInt(4));
						user1.setProjectId(rs.getInt(5));
						user1.setTaskId(rs.getInt(6));
						return user1;
					}
					
				});				

			}catch(Exception ex){
				logger.error("Got Errorin  listUsers:"+ex.getMessage());
			}
				
		return users;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub		
		List<User> users =new ArrayList<>();;
			try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
				//System.out.println("Connection="+connection);			
				 users =	jdbcTemplate.query("select * from users where userid="+id, new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						User user1 = new User();
						user1.setUserId(rs.getInt(1));
						user1.setFirstName(rs.getString(2));
						user1.setLastName(rs.getString(3));
						user1.setEmployeeId(rs.getInt(4));
						user1.setProjectId(rs.getInt(5));
						user1.setTaskId(rs.getInt(6));
						return user1;
					}
					
				});				

			}catch(Exception ex){
				logger.error("Got Errorin  getUser:"+ex.getMessage());
			}
		if(users != null && users.size()>0){
			return users.get(0);
		}
		return new User();
	}

	@Override
	public User getUserbyProjectId(int projectId) {
		// TODO Auto-generated method stub		
		List<User> users = null;
			try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
				//System.out.println("Connection="+connection);			
				 users =	jdbcTemplate.query("select * from users where projectid="+projectId, new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						User user1 = new User();
						user1.setUserId(rs.getInt(1));
						user1.setFirstName(rs.getString(2));
						user1.setLastName(rs.getString(3));
						user1.setEmployeeId(rs.getInt(4));
						user1.setProjectId(rs.getInt(5));
						user1.setTaskId(rs.getInt(6));
						return user1;
					}
					
				});				

			}catch(Exception ex){
				logger.error("Got Errorin  getUserbyProjectId:"+ex.getMessage());
			}
		if(users != null && users.size()>0){
			return users.get(0);
		}
		return new User();
	}
	
	
	@Override
	public User getUserbyTaskId(int taskId) {
		// TODO Auto-generated method stub		
		List<User> users = null;
			try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
				//System.out.println("Connection="+connection);			
				 users =	jdbcTemplate.query("select * from users where taskid="+taskId, new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						User user1 = new User();
						user1.setUserId(rs.getInt(1));
						user1.setFirstName(rs.getString(2));
						user1.setLastName(rs.getString(3));
						user1.setEmployeeId(rs.getInt(4));
						user1.setProjectId(rs.getInt(5));
						user1.setTaskId(rs.getInt(6));
						return user1;
					}
					
				});				

			}catch(Exception ex){
				logger.error("Got Errorin  getUserbyTaskId:"+ex.getMessage());
			}
		if(users != null && users.size()>0){
			return users.get(0);
		}
		return new User();
	}
	
	
	

}
