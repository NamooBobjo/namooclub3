package com.namoo.club.service.logic;
import java.util.Date;
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

	private CommunityDao cmDao;
	private SocialPersonDao spDao;
	private MemberDao mbDao;
	private CommunityCategoryDao cateDao;
	
	public CommunityServiceLogic() {
		//
		cmDao = DaoFactory.createFactory(DbType.MariaDB).getCommunityDao();
		spDao = DaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
		mbDao = DaoFactory.createFactory(DbType.MariaDB).getMemberDao();
		cateDao = DaoFactory.createFactory(DbType.MariaDB).getCommunityCategoryDao();
	}
	
	@Override
	public int registCommunity(String communityName, String description, String adminName, String email, String password) {
		//
		if (isExistCommunityByName(communityName)) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 커뮤니티입니다.");
		}
		
		if (spDao.readPerson(email) != null) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}

		SocialPerson admin = createPerson(adminName, email, password);
		Date date = new Date();
		Community community = new Community(communityName, description, date, admin);
		
		return cmDao.createCommunity(community);
	}

	private boolean isExistCommunityByName(String communityName) {
		//
		for(Community community : cmDao.readAllCommunity()){
			if(community.getName().equals(communityName)){
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
		
		SocialPerson towner = spDao.readPerson(email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		Community community = new Community(communityName, description, towner, category);
		
		int cmId = cmDao.createCommunity(community);
		for(Category cate : category){
			cateDao.createCategory(cmId, cate);
			System.out.println(cate);
		}

		mbDao.joinAsCommunityMember(cmId, 1, towner);
		
		return cmId;
	}

	private SocialPerson createPerson(String name, String email, String password) {
		// 
		SocialPerson person = new SocialPerson(name, email, password);
		spDao.createPerson(person);
		
		return person;
	}

	@Override
	public Community findCommunity(int cmId){
		//
		Community community = cmDao.readCommunity(cmId);
		List<Category> category = cateDao.readAllCategory(cmId);
		community.setCategory(category);
		
		return community;
	}
	
	@Override
	public void joinAsMember(String communityName, String name, String email, String password){
		//
		Community findCm = cmDao.readCommunity(communityName);
		
		if (findCm == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}

		if (spDao.readPerson(email) != null) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}
		
		SocialPerson towner = createPerson(name, email, password);
		mbDao.joinAsCommunityMember(findCm.getId(), 1, towner);
		
		cmDao.createCommunity(findCm);
	}

	@Override
	public void joinAsMember(String communityName, String email) {
		// 
		Community findCm = null;
		for(Community community : cmDao.readAllCommunity()){
			if(community.getName().equals(communityName)){
				findCm = cmDao.readCommunity(community.getId());
			}
		}
		
		if (findCm == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		SocialPerson towner = spDao.readPerson(email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		mbDao.joinAsCommunityMember(findCm.getId(), 1, towner);
		
		cmDao.createCommunity(findCm);
	}

	@Override
	public CommunityMember findCommunityMember(String communityName, String email) {
		// 
		Community findCm = null;
		for(Community community : cmDao.readAllCommunity()){
			if(community.getName().equals(communityName)){
				findCm = cmDao.readCommunity(community.getId());
			}
		}
		
		if (findCm == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		for (CommunityMember member : findCm.getMembers()) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		
		return null;
	}

	@Override
	public List<CommunityMember> findAllCommunityMember(String communityName) {
		// 
		Community findCm = null;
		for(Community community : cmDao.readAllCommunity()){
			if(community.getName().equals(communityName)){
				findCm = cmDao.readCommunity(community.getId());
			}
		}
		
		if (findCm == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		return findCm.getMembers();
	}

	@Override
	public int countMembers(String communityName){
		//
		Community findCm = null;
		for(Community community : cmDao.readAllCommunity()){
			if(community.getName().equals(communityName)){
				findCm = cmDao.readCommunity(community.getId());
			}
		}

		if (findCm != null) {
			return findCm.getMembers().size();
		}
		
		return 0;
	}

	@Override
	public void removeCommunity(int communityName) {
		// 
		cmDao.deleteCommunity(communityName);
	}

	@Override
	public List<Community> findAllCommunities() {
		//
		return cmDao.readAllCommunity();
	}

	@Override
	public List<Community> findJoinedCommunities(String email) {
		// 
		return cmDao.readJoinedCommunities(email);
	}

	@Override
	public List<Community> findManagedCommnities(String email) {
		// 
		return cmDao.readManagedCommunities(email);
	}

	@Override
	public void withdrawalCommunity(String communityName, String email) {
		//
		Community findCm = null;
		for(Community community : cmDao.readAllCommunity()){
			if(community.getName().equals(communityName)){
				findCm = cmDao.readCommunity(community.getId());
			}
		}

		if (findCm == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		
		findCm.removeMember(email);
		cmDao.createCommunity(findCm);
	}

	@Override
	public List<Community> findAllUnjoinedCommunities(String email) {
		// 
		return cmDao.readUnjoinedCommunities(email);
	}
}