package com.namoo.club.dao.jdbc;

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
import com.namoo.club.domain.entity.Club;

public class ClubDaojdbcTest {

	private ClubDao dao;
	IDatabaseTester databaseTester;

	@Before
	public void setUp() throws Exception {
		dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getClubDao();
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
	public void testReadAllClub() {
		List<Club> clubs = dao.readAllClub(1);
		for(Club club : clubs){
			assertEquals("축구", club.getName());			
		}
	}

	@Test
	public void testReadClub() {

		Club club = dao.readClub(15);
		assertEquals("축구", club.getName());
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
