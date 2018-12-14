package com.project.mgmt.util;

public class GenericUtil {
	
	public static java.sql.Date toSQLDate(java.util.Date utildate){		
		return new java.sql.Date(utildate.getTime());
	}
	
	
	public static java.util.Date toUtilDate(java.sql.Date sqlDate){
		return  new java.util.Date(sqlDate.getTime());
	}
	

}
