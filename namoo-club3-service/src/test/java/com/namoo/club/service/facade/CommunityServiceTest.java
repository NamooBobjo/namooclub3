package com.namoo.club.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.domain.entity.Category;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.service.logic.CommunityServiceLogic;
import com.namoo.club.service.logic.exception.NamooRuntimeException;
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

	// 커뮤니티가 존재할경우
	@Test(expected=NamooRuntimeException.class)
	public void testRegistCommunityNameWithException() {
		//
		int id = 1;
		String email = "j@nate.com";
		String adminName = "효진";
		String description = "세계의 요리를 배워요";
		String password = "1234";
		
		service.registCommunity(id, description, adminName, email, password);
	}
	
	// 해당 유저가 존재할경우
	@Test(expected=NamooRuntimeException.class)
	public void testRegistCommunityUserWithException() {
		int id = 3;
		String email = "jjj@nate.com";
		String adminName = "정효진";
		String description = "세계의 요리를 배워요";
		String password = "1234";
		
		service.registCommunity(id, description, adminName, email, password);
	}
	
	@Test
	public void testRegistCommunity() {
		//
		int id = 3;
		String email = "j@nate.com";
		String adminName = "효진";
		String description = "세계의 요리를 배워요";
		String password = "1234";
		
		int communityId = service.registCommunity(id, description, adminName, email, password);

		// 검증
		Community community = service.findCommunity(communityId);
		assertEquals(id, community.getId());
		assertEquals(description, community.getDescription());
	}

	@Test
	public void testRegistCommunity_InCategory() {
		//
		int communityId = 3;
		String description = "세계의 요리를 배워요";
		String email = "jjj@nate.com";
		List<Category> categories = new ArrayList<>();
		categories.add(new Category("A"));
		categories.add(new Category("B"));
		service.registCommunity(communityId, description, email, categories);
		
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
	public void testJoinAsMemberStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllCommunityMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCommunity() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindJoinedCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllUnjoinedCommunities() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindManagedCommnities() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithdrawalCommunity() {
		fail("Not yet implemented");
	}
}
