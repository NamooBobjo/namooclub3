package com.namoo.club.service.facade;

import static org.junit.Assert.*;

import java.security.Provider.Service;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.service.logic.TownerServiceLogic;
import com.namoo.club.service.logic.exception.NamooRuntimeException;
import com.namoo.club.shared.DbCommonTest;

public class TownerServiceTest extends DbCommonTest {
	//
	private TownerService townerService;
	
	@Before
	public void setUp() throws Exception {
		//
		super.setUp();
		this.townerService = new TownerServiceLogic();
	}	

	@After
	public void tearDown() throws Exception {
		//
		super.tearDown();
	}

	@Test
	public void testLoginAsTowner() {
		//
		assertFalse(townerService.loginAsTowner("aa", "gggg"));
		assertTrue(townerService.loginAsTowner("sss@nate.com", "1234"));
		
	}
	

	@Test(expected=NamooRuntimeException.class)
	public void testRegistTownerWithException() {
		//
		String name = "정효진";
		String email = "jjj@nate.com";
		String password = "1234";
		
		townerService.registTowner(name, email, password);
	}
	
	
	@Test
	public void testRegistTowner() {
		//
		String name = "정효진";
		String email = "kkk@nate.com";
		String password = "1234";
		
		townerService.registTowner(name, email, password);
		
		SocialPerson towner = townerService.findTowner(email);
		
		assertEquals(name, towner.getName());
		assertEquals(email, towner.getEmail());
		assertEquals(password, towner.getPassword());

	}
	
	@Test(expected=NamooRuntimeException.class)
	public void testRemoveTownerWithException() {
		// 커뮤니티의 회원으로 가입된 경우 삭제하지 못한다.
		townerService.removeTowner("jjj@nate.com");
	}

	@Test
	public void testRemoveTowner() {
		//
		String email = "ksy5350@nate.com";
		townerService.removeTowner(email);
		
		assertNull(townerService.findTowner(email));
	}

	@Test
	public void testFindTowner() {
		//
		SocialPerson towner = townerService.findTowner("ksy5350@nate.com");
		assertEquals("ksy5350@nate.com", towner.getEmail());
	}

	@Test
	public void testFindAllTowner() {
		//
		List<SocialPerson> persons = townerService.findAllTowner();
		assertEquals(3, persons.size());
	}

}
