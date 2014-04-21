package com.namoo.club.service.facade;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.domain.entity.Club;

public class ClubServiceTest {

	private ClubService service;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegistClub() {
		int cmId = 1;
		String category = "한국요리";
		int clid = 3;
		String description = "12314124123";
		String email = "123123";
		service.registClub(cmId, category, clid, description, email);
		
		Club club = service.findClub(3);
	}

	@Test
	public void testFindClubString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClubInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAsMemberIntStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAsMemberIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllClubs() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClubsById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllClubMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBelongClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindManagedClub() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithdrawalClub() {
		fail("Not yet implemented");
	}

}
