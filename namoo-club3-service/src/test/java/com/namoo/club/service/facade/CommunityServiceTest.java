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
		String name = "요리커뮤니티";
		String email = "j@nate.com";
		String adminName = "효진";
		String description = "세계의 요리를 배워요";
		String password = "1234";
		
		service.registCommunity(name, description, adminName, email, password);
	}
	
	// 해당 유저가 존재할경우
	@Test(expected=NamooRuntimeException.class)
	public void testRegistCommunityUserWithException() {
		String name = "가나다커뮤니티";
		String email = "jjj@nate.com";
		String adminName = "정효진";
		String description = "세계의 요리를 배워요";
		String password = "1234";
		
		service.registCommunity(name, description, adminName, email, password);
	}
	
	@Test
	public void testRegistCommunity() {
		//
		String name = "가나다커뮤니티";
		String email = "j@nate.com";
		String adminName = "효진";
		String description = "세계의 요리를 배워요";
		String password = "1234";
		
		int communityId = service.registCommunity(name, description, adminName, email, password);

		// 검증
		Community community = service.findCommunity(communityId);
		assertEquals(name, community.getName());
		assertEquals(description, community.getDescription());
	}

	@Test
	public void testRegistCommunityInCategory() {
		String communityName = "가나다커뮤니티";
		String description = "세계의 요리를 배워요";
		String email = "j@nate.com";
		List<Category> categories = new ArrayList<>();
		categories.add(new Category("A"));
		categories.add(new Category("B"));
		service.registCommunity(communityName, description, email, categories);
	}

	@Test
	public void testFindCommunity() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAsMemberStringStringStringString() {
		fail("Not yet implemented");
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
