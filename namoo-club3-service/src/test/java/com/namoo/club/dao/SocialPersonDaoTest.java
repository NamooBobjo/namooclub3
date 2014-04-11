package com.namoo.club.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;
import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.shared.DbCommonTest;

public class SocialPersonDaoTest extends DbCommonTest {

	private SocialPersonDao dao;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
	
	}

	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}

	@Test
	public void testReadAllPerson() {
		//
		List<SocialPerson> persons = dao.readAllPerson();
		assertEquals(2, persons.size());
	}

	@Test
	public void testReadPerson() {
		//
		SocialPerson person = dao.readPerson("ksy5350@nate.com");
		assertEquals("ksy5350@nate.com",person.getEmail());
	}

	@Test
	public void testCreatePerson() {
		
		SocialPerson person = new SocialPerson();
		person.setName("정효진");
		person.setPassword("1234");
		person.setEmail("jjj@nate.com");
		
		assertEquals("jjj@nate.com", person.getEmail());
		
	}

	@Test
	public void testDeletePerson() {
		//
		dao.deletePerson("ksy5350@nate.com");
		assertNull(dao.readPerson("ksy5350@nate.com"));
	}

	@Test
	public void testUpdatePerson() {
		//
		SocialPerson person = dao.readPerson("ksy5350@nate.com");
		person.setName("삼천포");
		person.setPassword("1234");
		dao.updatePerson(person);
		
		person = dao.readPerson("ksy5350@nate.com");
		assertEquals("삼천포", person.getName());
	}

}
