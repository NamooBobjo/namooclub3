package com.namoo.club.service.logic;

import java.util.List;

import com.namoo.club.dao.CommunityCategoryDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.domain.entity.Category;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class CommunityServiceLogic implements CommunityService {

	private CommunityDao communityDao;
	private SocialPersonDao personDao;
	private MemberDao memberDao;
	private CommunityCategoryDao categoryDao;
	
	public CommunityServiceLogic() {
		//
		communityDao = DaoFactory.createFactory(DbType.MariaDB).getCommunityDao();
		personDao = DaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
		memberDao = DaoFactory.createFactory(DbType.MariaDB).getMemberDao();
		categoryDao = DaoFactory.createFactory(DbType.MariaDB).getCommunityCategoryDao();
	}
	
	private boolean isExistCommunityByName(String communityName) {
		//
		for(Community community : communityDao.readAllCommunity()){
			if(community.getName().equals(communityName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int registCommunity(String communityName, String description, String email, List<Category> category) {
		//
		if (isExistCommunityByName(communityName)) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 커뮤니티입니다.");
		}
		
		SocialPerson towner = personDao.readPerson(email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		// 커뮤니티 생성
		Community community = new Community(communityName, description, towner);
		int cmId = communityDao.createCommunity(community);
		
		// 커뮤니티 클럽카테고리 생성
		for(Category cate : category){
			categoryDao.createCategory(cmId, cate);
			System.out.println(cate);
		}

		memberDao.joinAsCommunityMember(cmId, 1, towner);
		return cmId;
	}

	private SocialPerson createPerson(String name, String email, String password) {
		// 
		SocialPerson person = new SocialPerson(name, email, password);
		personDao.createPerson(person);
		
		return person;
	}

	@Override
	public Community findCommunity(int communityId){
		//
		Community community = communityDao.readCommunity(communityId);
		List<Category> category = categoryDao.readAllCategory(communityId);
		community.setCategory(category);
		
		// 관리자 정보
		community.setManager(memberDao.readCommunityManager(communityId));
		
		// 멤버정보
		community.setMembers(memberDao.readCommunityMembers(communityId));
		
		return community;
	}
	
	@Override
	public void joinAsMember(int communityId, String name, String email, String password){
		//
		Community findCm = communityDao.readCommunity(communityId);
		
		if (findCm == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}

		if (personDao.readPerson(email) != null) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}
		
		SocialPerson towner = createPerson(name, email, password);
		memberDao.joinAsCommunityMember(findCm.getId(), 1, towner);
		
		communityDao.createCommunity(findCm);
	}

	@Override
	public void joinAsMember(int communityId, String email) {
		// 
		Community findCm = communityDao.readCommunity(communityId);
		
		if (findCm == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		SocialPerson towner = personDao.readPerson(email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		memberDao.joinAsCommunityMember(findCm.getId(), 1, towner);
	}

	@Override
	public CommunityMember findCommunityMember(int communityId, String email) {
		// 
		return memberDao.readCommunityMember(communityId, email);
	}

	@Override
	public List<CommunityMember> findAllCommunityMember(int communityId) {
		// 
		return memberDao.readCommunityMembers(communityId);
	}

	@Override
	public int countMembers(int communityId){
		//
		return memberDao.readCommunityMembers(communityId).size();
	}

	@Override
	public void removeCommunity(int communityId) {
		// 
		communityDao.deleteCommunity(communityId);
	}

	@Override
	public List<Community> findAllCommunities() {
		//
		return communityDao.readAllCommunity();
	}

	@Override
	public List<Community> findJoinedCommunities(String email) {
		// 
		return communityDao.readJoinedCommunities(email);
	}

	@Override
	public List<Community> findManagedCommnities(String email) {
		// 
		return communityDao.readManagedCommunities(email);
	}

	@Override
	public void withdrawalCommunity(int communityId, String email) {
		//
		memberDao.deleteCommunityMember(communityId, email);
	}

	@Override
	public List<Community> findAllUnjoinedCommunities(String email) {
		// 
		return communityDao.readUnjoinedCommunities(email);
	}
}