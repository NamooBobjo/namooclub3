package com.namoo.club.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
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

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;
import com.namoo.club.domain.entity.Club;

public class ClubDaoTest extends DbCommonTest {

	private ClubDao dao;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getClubDao();
	}

	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}

	
	@Test
	public void testReadAllClub() {
		//
		List<Club> clubs = dao.readAllClub();
		
		assertEquals(2,clubs.size());		
		}

	@Test
	public void testReadClub() {
		//
		
		Club club = dao.readClub(1);
		assertEquals("한식", club.getName());
	}

	@Test
	public void testCreateClub() {

		Club club = new Club(1, 2, 11, "축구", "축구합시다");
		dao.createClub(club);
	}

	@Test
	public void testDeleteClub() {

		dao.deleteClub(15);
	}

	@Test
	public void testUpdateClub() {
		Club club = new Club(1, 2, 11, "축구", "축구합시다");
		dao.createClub(club);
		
		club = dao.readClub(17);
		club.setName("농구");
		
		dao.updateClub(club);
		club = dao.readClub(17);
		
		assertEquals("농구", club.getName());
	}

}
