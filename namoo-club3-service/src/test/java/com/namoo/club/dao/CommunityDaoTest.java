package com.namoo.club.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaojdbc;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.shared.DbCommonTest;

public class CommunityDaoTest extends DbCommonTest{
	
	private CommunityDao dao;

	@Before
	public void setUp() throws Exception {
		//
		super.setUp();
		dao =  new CommunityDaojdbc();
	}

	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}

	@Test
	public void testReadAllCommunity() {
		//
		List<Community> communities = dao.readAllCommunity();
		
		assertEquals(2 ,communities.size());
	}

	@Test
	public void testReadCommunityByName(){
		Community community = dao.readCommunity(1);
		
		assertEquals("요리커뮤니티", community.getName());
	}

	@Test
	public void testCreateCommunity() {
		//
		Community community = new Community();
		community.setName("abdbsdb");
		community.setDescription("dsfsdfsdf");
		
		int communityId = dao.createCommunity(community);
		
		community = dao.readCommunity(communityId);
		
		assertEquals("dsfsdfsdf", community.getDescription());
	}

	@Test
	public void testDeleteCommunity() {
		//
		assertNotNull(dao.readCommunity(1));
		
		dao.deleteCommunity(1);
		
		assertNull(dao.readCommunity(1));
	}

	@Test
	public void testUpdateCommunity() {
		//
		Community community =dao.readCommunity(1);
		community.setName("아아아아아아아");
		
		dao.updateCommunity(community);
		
		community = dao.readCommunity(1);
		assertEquals("아아아아아아아", community.getName());
	}
	
	@Test
	public void testReadJoinedCommunities(){
		//
		String email = "jjj@nate.com";
		List<Community> community = dao.readJoinedCommunities(email);
		
		assertEquals(1, community.size());
	}
	
	@Test
	public void testReadManagedCommunities(){
		//
		String email = "jjj@nate.com";
		List<Community> community = dao.readManagedCommunities(email);
		
		assertEquals(1, community.size());
	}
	
	@Test
	public void testReadUnjoinedCommunities(){
		//
		String email = "sss@nate.com";
		List<Community> community = dao.readUnjoinedCommunities(email);
		
		assertEquals(1, community.size());
	}
}
