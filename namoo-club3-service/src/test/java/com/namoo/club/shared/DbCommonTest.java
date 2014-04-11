package com.namoo.club.shared;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.namoo.club.dao.jdbc.DbConnection;

public class DbCommonTest {
	//
	@BeforeClass
	public static void createSchema() throws SQLException {
		//
		DbConnection.overrideProperties("test-jdbc.properties");
		
		InputStream is = DbCommonTest.class.getResourceAsStream("schema.sql");
		Reader reader = new InputStreamReader(is);
		RunScript.execute(DbConnection.getConnection(), reader);
	}
	private IDatabaseTester databaseTester;

	//
	@Before
	public void setUp() throws Exception {
		InputStream is = this.getClass().getResourceAsStream(this.getClass().getSimpleName()+"_dataset.xml");
		IDataSet dataset = new FlatXmlDataSet(is);
		
		String url = DbConnection.getUrl();
		String username = DbConnection.getUsername();
		String password = DbConnection.getPassword();
		String driver= DbConnection.getDriverClassName();
		
		databaseTester = new JdbcDatabaseTester(driver, url, username, password);
		databaseTester.setDataSet(dataset);
		
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
	}

	@After
	public void tearDown() throws Exception {
		//
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.onTearDown();
	}
}