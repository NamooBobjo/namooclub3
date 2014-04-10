package com.namoo.club.dao;

import static org.junit.Assert.assertEquals;

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

import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;
import com.namoo.club.domain.entity.SocialPerson;

public class MemberDaoTest {

	private MemberDao dao;
	IDatabaseTester databaseTester;
	
	@Before
	public void setUp() throws Exception {
		dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getMemberDao();
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
	public void testReadCommunityMember() {
		
		List<SocialPerson> test = dao.readCommunityMembers(1);
		assertEquals(1, test.size());
	}

	@Test
	public void testReadClubMember() {
		List<SocialPerson> test = dao.readClubMembers(16);
		assertEquals(1, test.size());
	}

	@Test
	public void testJoinAsCommunityMember() {
		SocialPerson person = new SocialPerson("yong", "yong@nate.com", "1234");
		
		dao.joinAsCommunityMember(1, 1, person);

	}

	@Test
	public void testJoinAsClubMember() {
		SocialPerson person = new SocialPerson("yong", "yong@nate.com", "1234");		
		dao.joinAsClubMember(16, 0, person);
		
	}

	@Test
	public void testDeleteCommunityMember() {
		dao.deleteCommunityMember(1, "yong@nate.com");
	}

	@Test
	public void testDeleteClubMember() {
		dao.deleteClubMember(16, "yong@nate.com");
	}

	@Test
	public void testUpdateClubMember() {
		dao.updateClubMember(16, "yong@nate.com", 1);
	}

}
