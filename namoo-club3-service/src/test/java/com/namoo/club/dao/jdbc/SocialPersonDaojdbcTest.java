package com.namoo.club.dao.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.domain.entity.SocialPerson;


public class SocialPersonDaojdbcTest {

	private SocialPersonDao dao =null;
	String userId = "sss";
	
	@Before
	public void setUp() throws Exception{
		dao =  new SocialPersonDaojdbc();
	}
	
	@After
	public void tearDown() throws Exception{
		dao.deletePerson(userId);
	}
	@Test
	public void testReadAllPerson() {
		SocialPerson person = new SocialPerson("sss", "sss", "sss");
		List<SocialPerson> persons = dao.readAllPerson();
		int count = persons.size()+1;
		dao.createPerson(person);
		persons = dao.readAllPerson();
		int count2 = persons.size();
		assertEquals(count,count2);}

	@Test
	public void testReadPerson() {
		
	}

	@Test
	public void testCreatePerson() {
		SocialPerson person = new SocialPerson("sss", "sss", "sss");
		dao.createPerson(person);
		person = dao.readPerson("sss");
		assertEquals("sss", person.getName());
	}

	@Test
	public void testDeletePerson() {
		dao.deletePerson("sss");
	}

	@Test
	public void testUpdatePerson() {
		SocialPerson person = new SocialPerson("sss", "sss", "sss");
		dao.createPerson(person);
		person = dao.readPerson("sss");
		person.setName("ssss");
		dao.updatePerson(person);
		person = dao.readPerson("sss");
		assertEquals("ssss",person.getName());
	}

}
