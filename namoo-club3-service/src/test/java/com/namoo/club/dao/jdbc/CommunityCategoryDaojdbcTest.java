package com.namoo.club.dao.jdbc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.CommunityCategoryDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory.DbType;

public class CommunityCategoryDaojdbcTest {
	private CommunityCategoryDao dao;
	IDatabaseTester databaseTester;

	
	@Before
	public void setUp() throws Exception {
		dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getCommunityCategoryDao();
		prepareDatabaseTester();
		databaseTester.setSetUpOperation(DatabaseOperation.REFRESH);
		databaseTester.onSetup();
	}

	@After
	public void tearDown() throws Exception {		
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
		databaseTester.onTearDown();
	}

	private void prepareDatabaseTester() throws DataSetException, IOException {
		String url = "jdbc:mariadb://192.168.0.10:3306/namooclubdb";
		String driver = "org.mariadb.jdbc.Driver";
		String username = "namoouser";
		String password = "namoouser";

		databaseTester = new JdbcDatabaseTester(driver, url, username, password);
		databaseTester.setDataSet(readDataset());

	}

	private IDataSet readDataset() throws DataSetException, IOException {
		InputStream is = this.getClass().getResourceAsStream("dataset.xml");
		IDataSet dataset = new FlatXmlDataSet(is);
		return dataset;
	}

	@Test
	public void testReadAllCategory() {
		List<String> categories = dao.readAllCategory(1);
		String baseball ="";
		String volley ="";
		for(String cate : categories){
			if(cate.equals("야구")){
				baseball = cate;
			}
			else if(cate.equals("배구")){
				volley = cate;
			}
		}
		
		assertEquals("야구", baseball);
		assertEquals("배구", volley);
	}

	@Test
	public void testReadCategory() {
		String category = dao.readCategory(1,9);
		assertEquals("야구", category);
	}

	@Test
	public void testDeleteCategory() {
		dao.deleteCategory(1);
	}

	@Test
	public void testCreateCategory() {
		List<String> category = new ArrayList<>();
		category.add("야구");
		category.add("농구");
		category.add("배구");
		dao.createCategory(1, category);
		
	}

}
