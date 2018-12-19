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

import com.project.mgmt.dao.ProjectDAO;
import com.project.mgmt.model.Project;
import com.project.mgmt.model.ProjectTasks;
import com.project.mgmt.model.Projects;
import com.project.mgmt.model.Tasks;
import com.project.mgmt.util.GenericUtil;

public class ProjectDAOImpl implements ProjectDAO {
	private static final Logger logger = LogManager.getLogger(ProjectDAOImpl.class);


	JdbcTemplate jdbcTemplate;

	private String INSERT_SQL = "INSERT INTO PROJECTS(TITLE,STARTDATE,ENDDATE,PRIORITY) VALUES(?,?,?,?)";

	private String DELETE_SQL = "DELETE FROM PROJECTS WHERE PROJECTID=?";

	private String UPDATE_SQL = "UPDATE PROJECTS SET TITLE=?,STARTDATE=?,ENDDATE=?,PRIORITY =? WHERE PROJECTID=?";

	private String SELECT_PROJECTSDETAILS_SQL ="SELECT A.PROJECTID,A.TITLE,A.STARTDATE,A.ENDDATE,A.PRIORITY,D.USERID,D.FirstName,D.LastName"
			+",C.TASKID,C.TITLE, C.STATUS FROM PROJECTS A left outer join USERS D on (D.PROJECTID=A.PROJECTID)"  
			+"left outer join TASKS C on(C.PROJECTID = A.PROJECTID)  GROUP BY A.PROJECTID,A.TITLE,C.STATUS";


	@Override
	public List<ProjectTasks> listProjectsDetails() {
		// TODO Auto-generated method stub
		/*List<Projects> projectslist = null;
		List<Projects> finalprojectslist = new ArrayList<>();*/
		List<ProjectTasks> projectslist = new ArrayList<>();
		try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
			logger.debug("Connection="+connection);	
			int currentprojectId =0;

			Statement stmt = connection.createStatement();
			ResultSet rs =stmt.executeQuery(SELECT_PROJECTSDETAILS_SQL);




			ProjectTasks project = new ProjectTasks();             

			//List<Task> taskslist = new ArrayList<>();
			int projectid =0;
			while(rs.next()) {
				if(projectid != rs.getInt(1)){
					project = new ProjectTasks(); 
					project.setProjectid(rs.getInt(1));
					project.setProjectname(rs.getString(2));
					project.setStartDate(GenericUtil.toUtilDate(rs.getDate(3)));
					project.setEndDate(GenericUtil.toUtilDate(rs.getDate(4)));
					project.setPriority(rs.getInt(5));
					project.setUserid(rs.getInt(6));
					project.setManagername(rs.getString(7)+" "+rs.getString(8));

				}
				Tasks task = new Tasks();
				task.setTaskId(rs.getInt(9));
				task.setTaskname(rs.getString(10));
				task.setUserId(rs.getInt(6));
				task.setStatus(rs.getString(11));

				//taskslist .add(task);
				if(task.getTaskname() != null) {
					project.getTaskslist().add(task);  
				}
				if(projectid != rs.getInt(1)){
					projectslist.add(project);
				}
				projectid = rs.getInt(1);

			}
			logger.debug(projectslist.size()); 
			for(ProjectTasks projects :projectslist){
				logger.debug(projects);
			}


			//	List<Projects> projectslist = null;

		}catch(Exception ex){
			logger.error(ex.getMessage());
			//ex.printStackTrace();
		}

		return projectslist;
	}

	@Override
	public int addProject(Projects project) {
		// TODO Auto-generated method stub		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){

			try{
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub

					PreparedStatement pstmt = con
							.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);

					pstmt.setString(1, project.getProjectName());
					pstmt.setDate(2, GenericUtil.toSQLDate(project.getStartDate()));
					pstmt.setDate(3, GenericUtil.toSQLDate(project.getEndDate()));
					pstmt.setInt(4, project.getPriority());
					return pstmt;
				}
			}, keyHolder);

			/*jdbcTemplate.update(INSERT_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					//pstmt.setInt(1, project.getProjectId());
					pstmt.setString(1, project.getProjectName());
					pstmt.setDate(2, GenericUtil.toSQLDate(project.getStartDate()));
					pstmt.setDate(3, GenericUtil.toSQLDate(project.getEndDate()));
					pstmt.setInt(4, project.getPriority());
				}
			} );*/

		}catch(Exception ex){
			logger.error(ex.getMessage());
			//ex.printStackTrace();
		}
		return keyHolder.getKey().intValue();
	}

	@Override
	public void deleteProject(Project project) {
		// TODO Auto-generated method stub		
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
		try{
			jdbcTemplate.update(DELETE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub					
					pstmt.setInt(1, project.getProjectId());
				}
			} );

		}catch(Exception ex){
			logger.error(ex.getMessage());
			//ex.printStackTrace();
		}

	}

	@Override
	public void updateProject(Projects project) {
		// TODO Auto-generated method stub		
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){
		try{
			jdbcTemplate.update(UPDATE_SQL, new PreparedStatementSetter() {				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub						

					pstmt.setString(1, project.getProjectName());
					pstmt.setDate(2, GenericUtil.toSQLDate(project.getStartDate()));
					pstmt.setDate(3, GenericUtil.toSQLDate(project.getEndDate()));
					pstmt.setInt(4, project.getPriority());
					pstmt.setInt(5, project.getProjectId());

				}
			} );

		}catch(Exception ex){
			logger.error(ex.getMessage());
			//ex.printStackTrace();
		}

	}

	@Override
	public List<Projects> listProjects(Projects project) {
		// TODO Auto-generated method stub
		List<Projects> projects = new ArrayList<>();
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
			//logger.debug("Connection="+connection);
		try{
			projects =	jdbcTemplate.query("select * from projects", new RowMapper<Projects>(){
				@Override
				public Projects mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Projects project = new Projects();					

					project.setProjectId(rs.getInt(1));
					project.setProjectName(rs.getString(2));
					project.setStartDate(rs.getDate(3));
					project.setEndDate(rs.getDate(4));							
					project.setPriority(rs.getInt(5));

					return project;
				}

			});				

		}catch(Exception ex){
			logger.error(ex.getMessage());
			logger.error("Got Errorin  listProjects:"+ex.getMessage());
			//ex.printStackTrace();
		}

		return projects;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Projects getProject(int projectId) {
		// TODO Auto-generated method stub
		List<Projects> projects = new ArrayList<>();
		//try(Connection connection =jdbcTemplate.getDataSource().getConnection()){	
			//logger.debug("Connection="+connection);
		try{
			projects =	jdbcTemplate.query(" SELECT PROJECTS.PROJECTID,PROJECTS.TITLE,PROJECTS.STARTDATE,PROJECTS.ENDDATE,"
					+"PROJECTS.PRIORITY,USERS.FIRSTNAME,USERS.LASTNAME FROM PROJECTS left outer join "   
					+" USERS on(PROJECTS.PROJECTID = USERS.PROJECTID ) where PROJECTS.PROJECTID="+projectId, new RowMapper<Projects>(){
						@Override
						public Projects mapRow(ResultSet rs, int arg1) throws SQLException {
							// TODO Auto-generated method stub
							Projects project = new Projects();					

							project.setProjectId(rs.getInt(1));
							project.setProjectName(rs.getString(2));
							project.setStartDate(rs.getDate(3));
							project.setEndDate(rs.getDate(4));							
							project.setPriority(rs.getInt(5));
							if(rs.getString(6) !=null && rs.getString(7) != null){
								project.setManager(rs.getString(6) +" "+ rs.getString(7));
							}else if(rs.getString(6) !=null){
								project.setManager(rs.getString(6));
							}else if(rs.getString(7) !=null){
								project.setManager(rs.getString(7));
							}else{
								project.setManager("");
							}

							return project;
						}

					});				

		}catch(Exception ex){
			logger.error(ex.getMessage());
			//ex.printStackTrace();
		}
		if(projects != null && projects.size()>0){

			return projects.get(0);
		}else{
			return new Projects();
		}
	}

}
