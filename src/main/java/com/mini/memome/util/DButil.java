package com.mini.memome.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	static {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/memome";
    private static final String JDBC_USER = "SCOTT";
    private static final String JDBC_PASSWORD = "TIGER";
    
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
}