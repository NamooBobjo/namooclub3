package com.namoo.club.dao.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

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
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;

public class MemberDaojdbcTest {

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
	public void testFindCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAsCommunityMember() {
		SocialPerson person = new SocialPerson("sss", "sss", "sss");
		
//		CommunityMember communityMember = new CommunityMember(1, person, "1");
//		dao.joinAsCommunityMember(communityMember);
//		
//		assertEquals("sss", communityMember.getEmail());
	}

	@Test
	public void testJoinAsClubMember() {
		SocialPerson person = new SocialPerson("sss", "sss", "sss");
		
//		ClubMember clubMember = new ClubMember(1, person, "1", "1");
//		dao.joinAsClubMember(clubMember);
//		
//		assertEquals("sss", clubMember.getEmail());
	}

	@Test
	public void testDeleteCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateClubMember() {
		fail("Not yet implemented");
	}

}
