package com.namoo.club.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.jdbc.CommunityDaojdbc;
import com.namoo.club.domain.entity.Community;

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
	public void testReadCommunity(){
		Community community = dao.readCommunity(1);
		
		assertEquals("요리커뮤니티", community.getName());
	}

	@Test
	public void testCreateCommunity() {
		//
	}

	@Test
	public void testDeleteCommunity() {
		//
	}

	@Test
	public void testUpdateCommunity() {
		//
	}
}
