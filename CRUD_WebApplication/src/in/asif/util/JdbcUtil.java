package in.asif.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private JdbcUtil() {
	}

	static {
		//Step1: loading and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException, IOException 
	{
		
				HikariConfig config = new HikariConfig("E:\\JDBC_PROJECT\\Jdbc_CRUD_APP\\application.properties");
				HikariDataSource dataSource = new HikariDataSource(config);
				return dataSource.getConnection();
		
	}
	
}