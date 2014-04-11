package com.namoo.club.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.MemberDaojdbc;
import com.namoo.club.domain.entity.SocialPerson;

public class MemberDaoTest extends DbCommonTest{

	private MemberDao dao;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		dao = new MemberDaojdbc();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testReadCommunityMember() {
		//
		List<SocialPerson> test = dao.readCommunityMembers(1);
		assertEquals(1, test.size());
	}

	@Test
	public void testReadClubMember() {
		List<SocialPerson> test = dao.readClubMembers(1);
		assertEquals(1, test.size());
	}

	@Test
	public void testJoinAsCommunityMember() {
		SocialPerson person = new SocialPerson("yong", "yong@nate.com", "1234");
		
		dao.joinAsCommunityMember(1, 1, person);
		List<SocialPerson> test = dao.readCommunityMembers(1);
		assertEquals(2, test.size());
	}

	@Test
	public void testJoinAsClubMember() {
		SocialPerson person = new SocialPerson("yong", "yong@nate.com", "1234");		
		dao.joinAsClubMember(1, 0, person);
		List<SocialPerson> test = dao.readClubMembers(1);
		assertEquals(2, test.size());
	}

	@Test
	public void testDeleteCommunityMember() {
		dao.deleteCommunityMember(1, "jjj@nate.com");
		assertNull(dao.readCommunityMembers(1));
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
