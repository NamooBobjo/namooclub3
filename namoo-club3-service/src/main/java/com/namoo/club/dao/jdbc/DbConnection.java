package com.namoo.club.dao.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DbConnection {
	//
	private static DbConnection instance = new DbConnection();
	private static final String PROPERTIES = "jdbc.properties";
	
	private DataSource dataSource;
	
	private DbConnection() {
		//
		initialize(PROPERTIES);
	}

	private void initialize(String propertiesName) {
		// 
		Properties prop = loadProperties(propertiesName);
		
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(prop.getProperty("database.url"));
		ds.setUsername(prop.getProperty("database.username"));
		ds.setPassword(prop.getProperty("database.password"));
		ds.setDriverClassName(prop.getProperty("database.driver"));
		
		// setting connection pool
		int maxConnection = Integer.parseInt(
				prop.getProperty("database.maxConnection"));
		ds.setMaxTotal(maxConnection);
		
		this.dataSource = ds;
		
	}

	//--------------------------------------------------------------------------
	public static Connection getConnection() throws SQLException {
		//
		return instance.dataSource.getConnection();
	}
	
	//--------------------------------------------------------------------------
	// private methods
	
	private Properties loadProperties(String propertiesName) {
		// 
		Properties prop = new Properties();
		try {
			prop.load(getClass().getResourceAsStream(propertiesName));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("데이터베이스 설정정보를 로드하는 중 오류가 발생하였습니다.", e);
		}
		return prop;
	}
	
	public static void overrideProperties(String propertiesName) {
		//
		instance.initialize(propertiesName);
	}
	
	public static String getUsername() {
		return ((BasicDataSource) instance.dataSource).getUsername();
	}
	
	public static String getPassword() {
		return ((BasicDataSource) instance.dataSource).getPassword();
	}
	
	public static String getUrl() {
		return ((BasicDataSource) instance.dataSource).getUrl();
	}
	
	public static String getDriverClassName() {
		return ((BasicDataSource) instance.dataSource).getDriverClassName();
	}
}
