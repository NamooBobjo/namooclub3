package com.namoo.club.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.domain.entity.Category;
import com.namoo.club.domain.entity.Club;
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.service.logic.ClubServiceLogic;
import com.namoo.club.shared.DbCommonTest;

public class ClubServiceTest extends DbCommonTest{

	private ClubService service;
	
	@Before
	public void setUp() throws Exception {
		//
		super.setUp();
		service = new ClubServiceLogic();
	}

	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();

	}

	@Test
	public void testRegistClub() {
		int communityId = 1;
		String clubName = "New Club";
		String description = "12314124123";
		String email = "jjj@nate.com";
		Category category = new Category();
		category.setName("a");
		
		service.registClub(communityId, category, clubName, description, email);
		
		// 
		
	}

	@Test
	public void testFindClubString() {
		//
		Club club = service.findClub("한식");
		assertEquals("한식", club.getName());
	}

	@Test
	public void testFindClubInt() {
		Club club = service.findClub(1);
		assertEquals("한식", club.getName());
	}

	@Test
	public void testJoinAsMemberIntStringStringString() {
		
		int clubId = 1;
		String name = "김성용";
		String email= "ksy5350@nate.com";
		String password= "1234";
		
		service.joinAsMember(clubId, name, email, password);
		
		ClubMember member = service.findClubMember(clubId, email);
		
		assertEquals(email, member.getEmail());
	}

	@Test
	public void testJoinAsMemberIntString() {
		
		int clubId = 1;
		String email= "ksy5350@nate.com";
		service.joinAsMember(clubId, email);
		ClubMember member = service.findClubMember(clubId, email);
		
		assertEquals("ksy5350@nate.com", member.getEmail());
	}

	@Test
	public void testFindAllClubs() {
		//
		List<Club> clubs = service.findAllClubs();
		assertEquals(2, clubs.size());
		
	}

	@Test
	public void testFindClubMember() {
		
		int clid = 1;
		String email= "jjj@nate.com";
		
		ClubMember member = service.findClubMember(clid, email);
		assertEquals("jjj@nate.com", member.getEmail());
		
	}

	@Test
	public void testFindClubsById() {
		
		int id = 1;
		List<Club> clubs =service.findClubsById(id);
		
		assertEquals(2, clubs.size());
		
		
	}

	@Test
	public void testFindAllClubMember() {
		
		int clid = 1;
		List<ClubMember> clubMembers = service.findAllClubMember(clid); 
		
		assertEquals(1, clubMembers.size());
		
	
	}

	@Test
	public void testCountMembers() {
		
		int clid = 1;
	 service.countMembers(clid);	
	 
	 assertEquals(1, service.countMembers(clid));
	}	

	@Test
	public void testRemoveClub() {
		
		int clid = 1;
		service.removeClub(clid);
		
		assertNull(service.findClub(clid));
	}

	@Test
	public void testFindBelongClub() {
		
		int cmId = 1;
		String email= "jjj@nate.com";
		
		List<Club> clubs =service.findBelongClub(cmId, email);
		assertEquals(0, clubs.size());
		
	}

	@Test
	public void testFindManagedClub() {
		
		String email= "jjj@nate.com";
		int cmId = 1;
		
		List<Club> clubs = service.findManagedClub(cmId, email);
		assertEquals(0, clubs.size());
	}

	@Test
	public void testWithdrawalClub() {
		
		service.withdrawalClub(1,"jjj@nate.com");
		
	}

}
