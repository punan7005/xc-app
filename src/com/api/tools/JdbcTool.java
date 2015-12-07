package com.api.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcTool {
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://xiaohuoban.mysql.rds.aliyuncs.com:3306/api_test?useUnicode=true&autoReconnect=true&autoReconnectForPools=true&characterEncoding=UTF-8";
	public static final String MYSQL_USER_NAME = "xdf_66xue";
	public static final String MYSQL_USER_PASSWORD = "nimei_321";
	
	static{
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection createConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER_NAME, MYSQL_USER_PASSWORD);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeResources(ResultSet resultSet, Statement statement, Connection connection){
		try {
			if(resultSet != null){
				resultSet.close();
			}
			if(statement != null){
				statement.close();
			}
			if(connection != null){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
