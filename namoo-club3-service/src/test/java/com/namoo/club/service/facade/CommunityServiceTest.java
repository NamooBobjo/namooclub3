package com.namoo.club.service.facade;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.domain.entity.Category;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.service.logic.CommunityServiceLogic;
import com.namoo.club.shared.DbCommonTest;

public class CommunityServiceTest extends DbCommonTest {
	
	private CommunityService service;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		service = new CommunityServiceLogic();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testRegistCommunity_InCategory() {
		//
		String communityName = "세계요리 커뮤니티";
		String description = "세계의 요리를 배워요";
		String email = "jjj@nate.com";
		List<Category> categories = new ArrayList<>();
		categories.add(new Category("A"));
		categories.add(new Category("B"));
		int communityId = service.registCommunity(communityName, description, email, categories);
		
		Community community = service.findCommunity(communityId);
		assertEquals(communityId, community.getId());
		assertEquals(description, community.getDescription());
	}

	@Test
	public void testJoinAsMember_firstJoin() {
		//
		int communityid = 1;
		String name = "ksj";
		String email = "ksj@naver.com";
		String password = "1234";
		service.joinAsMember(communityid, name, email, password);
		
		Community community = service.findCommunity(1);
		assertEquals(email, community.getManager().getEmail());
	}

	@Test
	public void testJoinAsMember() {
		//
		int communityId = 1;
		String email = "ksy5350@nate.com";
		
		assertEquals(1, service.findAllCommunityMember(1).size());
		service.joinAsMember(communityId, email);
		
		assertEquals(2, service.findAllCommunityMember(1).size());
	}

	@Test
	public void testFindAllCommunities() {
		int count = service.findAllCommunities().size();
		
		assertEquals(2, count);
	}

	@Test
	public void testFindCommunityMember() {
		//
		int communityId = 1;
		String email = "jjj@nate.com"; 
		CommunityMember member = service.findCommunityMember(communityId, email);
		
		assertEquals("정효진", member.getName());
	}

	@Test
	public void testFindAllCommunityMembers() {
		int communityId = 1;
		int count = service.findAllCommunityMember(communityId).size();
		
		assertEquals(1, count);
	}

	@Test
	public void testCountMembers() {
		//
		int communityId = 1;
		int count = service.countMembers(communityId);
		
		assertEquals(1, count);
	}

	@Test
	public void testRemoveCommunity() {
		//
		int communityId = 1;
		service.removeCommunity(communityId);
		int count = service.findAllCommunities().size();
		assertEquals(1, count);
	}

	@Test
	public void testFindJoinedCommunities() {
		//
		String email = "jjj@nate.com";
		int count = service.findJoinedCommunities(email).size();
		
		assertEquals(1, count);
	}

	@Test
	public void testFindAllUnjoinedCommunities() {
		String email = "jjj@nate.com";
		int count = service.findAllUnjoinedCommunities(email).size();
		
		assertEquals(1, count);
	}

	@Test
	public void testFindManagedCommnities() {
		String email = "jjj@nate.com";
		int count = service.findManagedCommnities(email).size();
		
		assertEquals(1, count);
	}

	@Test
	public void testWithdrawalCommunity() {
		int communityId = 1;
		String email = "jjj@nate.com";
		service.withdrawalCommunity(communityId, email);
		
		int count = service.findAllCommunityMember(communityId).size();
		assertEquals(0, count);
	}
}
