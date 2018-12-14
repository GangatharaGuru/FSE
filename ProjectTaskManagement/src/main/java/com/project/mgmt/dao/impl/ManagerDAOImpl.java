package com.project.mgmt.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.project.mgmt.dao.ManagerDAO;
import com.project.mgmt.model.Manager;
import com.project.mgmt.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagerDAOImpl implements ManagerDAO {
	
	private static final Logger logger = LogManager.getLogger(ManagerDAOImpl.class);

	JdbcTemplate jdbcTemplate;

	private String INSERT_SQL = "INSERT INTO MANAGER(MANAGERNAME,PROJECTID) VALUES(?,?)";

	private String DELETE_SQL = "DELETE FROM MANAGER WHERE MANAGERID=?";

	private String UPDATE_SQL = "UPDATE MANAGER SET MANAGERNAME=?,PROJECTID=? WHERE MANAGERID=?";

	@Override
	public void addManager(Manager manager) {
		// TODO Auto-generated method stub		
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){

			jdbcTemplate.update(INSERT_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					//pstmt.setInt(1, user.getUserId());
					pstmt.setString(1, manager.getManagerName());					
					pstmt.setInt(2, manager.getProjectId());
				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  addManager"+ex.getMessage());
		}

	}

	@Override
	public void deleteManager(Manager manager) {
		// TODO Auto-generated method stub		
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){

			jdbcTemplate.update(DELETE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub					
					pstmt.setInt(1, manager.getManagerId());

				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  deleteManager"+ex.getMessage());
			//ex.printStackTrace();
		}

	}

	@Override
	public void updateManager(Manager manager) {
		// TODO Auto-generated method stub		
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){

			jdbcTemplate.update(UPDATE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub	
					pstmt.setString(1, manager.getManagerName());					
					pstmt.setInt(2, manager.getProjectId());
					pstmt.setInt(3, manager.getManagerId());

				}
			} );

		}catch(Exception ex){
			logger.error("Got Errorin  updateManager"+ex.getMessage());
		}

	}

	@Override
	public List<Manager> listManagers(Manager manager) {
		// TODO Auto-generated method stub		
		List<Manager> managers = null;
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
			logger.debug("Connection="+connection);			
			managers =	jdbcTemplate.query("select * from manager", new RowMapper<Manager>(){
				@Override
				public Manager mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Manager manager = new Manager();
					manager.setManagerId(rs.getInt(1));
					manager.setManagerName(rs.getString(2));
					manager.setProjectId(rs.getInt(3));

					return manager;
				}

			});				

		}catch(Exception ex){
			logger.error("Got Errorin  listManagers:"+ex.getMessage());
		}

		return managers;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = jdbcTemplate;
	}


}
