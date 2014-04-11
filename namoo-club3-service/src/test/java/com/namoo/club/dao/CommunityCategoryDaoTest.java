package com.namoo.club.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;
import com.namoo.club.domain.entity.Category;
import com.namoo.club.shared.DbCommonTest;

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
