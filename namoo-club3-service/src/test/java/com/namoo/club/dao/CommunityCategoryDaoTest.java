package com.namoo.club.dao;

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
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;
import com.namoo.club.domain.entity.Category;

public class CommunityCategoryDaoTest extends DbCommonTest {
	
	private CommunityCategoryDao dao;

	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getCommunityCategoryDao();
	
	}

	@After
	public void tearDown() throws Exception {		
		super.tearDown();
	}

	@Test
	public void testReadAllCategory() {
		List<Category> categories = dao.readAllCategory(1);
		
		assertEquals(2, categories.size());
	}

	@Test
	public void testReadCategory() {
		Category category = dao.readCategory(1, 1);
		assertEquals("한국요리", category.getName());
	}

	@Test
	public void testDeleteCategory() {
		dao.deleteCategory(1);
		assertNull(dao.readCategory(1, 1));
	}

	@Test
	public void testCreateCategory() {
		Category category = new Category();
		category.setName("한국요리");
		category.setCmId(1);
		
		assertEquals("한국요리", category.getName());
		
	}

}
