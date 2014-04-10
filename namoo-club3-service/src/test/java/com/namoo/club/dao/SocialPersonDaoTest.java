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

import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;
import com.namoo.club.domain.entity.SocialPerson;

public class SocialPersonDaoTest {

	private SocialPersonDao dao;
	IDatabaseTester databaseTester;

	@Before
	public void setUp() throws Exception {
		dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
		prepareDatabaseTester();
		databaseTester.setSetUpOperation(DatabaseOperation.REFRESH);
		databaseTester.onSetup();
	}

	@After
	public void tearDown() throws Exception {
		dao.deletePerson("sss");
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
	public void testReadAllPerson() {
		
		List<SocialPerson> persons = dao.readAllPerson();
		int count = persons.size();
		assertEquals(2, count);
	}

	@Test
	public void testReadPerson() {
		dao.readPerson("ksy5350@nate.com");
	}

	public void testCreatePerson() {
		
		SocialPerson person = new SocialPerson("sss", "sss", "sss");
		dao.createPerson(person);
		assertEquals("sss", person.getName());
	}

	@Test
	public void testDeletePerson() {
		dao.readPerson("ksy5350@nate.com");
	}

	@Test
	public void testUpdatePerson() {
		SocialPerson person = new SocialPerson("sss", "sss", "sss");
		dao.createPerson(person);
		person = dao.readPerson("ksy5350@nate.com");
		person.setName("ssss");
		dao.updatePerson(person);
		person = dao.readPerson("sss");
		assertEquals("ssss", person.getName());
	}

}
