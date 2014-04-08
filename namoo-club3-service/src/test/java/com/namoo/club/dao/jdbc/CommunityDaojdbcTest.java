package com.namoo.club.dao.jdbc;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.domain.entity.Community;

public class CommunityDaojdbcTest {
	
	private CommunityDao dao = null;
	
	int cmId = 3;
	String communityName = "firstCommunity";
	String description = "firstCommunity's description";
	Date date = new Date();

	@Before
	public void setUp() throws Exception {
		dao =  new CommunityDaojdbc();
	}

	@After
	public void tearDown() throws Exception {
		dao.deleteCommunity(cmId);
	}

	@Test
	public void testReadAllCommunity() {
		createCommunity();
		int count1 = dao.readAllCommunity().size();
		
		Community community1 = new Community(1, "communityName", "description", date);
		dao.createCommunity(community1);
		int count2 = dao.readAllCommunity().size();
		
		assertEquals(count1 + 1, count2);
	}

	@Test
	public void testCreateCommunity() {
		//
		createCommunity();
		
		Community community = dao.readCommunity(cmId);
		assertEquals(cmId, community.getId());
	}

	private void createCommunity(){
		//
		Community community = new Community(cmId, communityName, description, date);
		dao.createCommunity(community);
	}
	
	@Test
	public void testDeleteCommunity() {
		createCommunity();
		
		dao.deleteCommunity(cmId);
		
		assertNull(dao.readCommunity(cmId));
	}

	@Test
	public void testUpdateCommunity() {
		createCommunity();
		
		Community community = dao.readCommunity(cmId);
		community.setDescription("update description");
		
		dao.updateCommunity(community);
		community = dao.readCommunity(cmId);
		
		assertEquals("update description", community.getDescription());
	}
}
