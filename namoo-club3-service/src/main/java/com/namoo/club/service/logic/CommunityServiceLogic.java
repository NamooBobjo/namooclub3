package com.namoo.club.service.logic;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class CommunityServiceLogic implements CommunityService {

	private CommunityDao cmDao;
	private SocialPersonDao spDao;
	private MemberDao mbDao;
	
	public CommunityServiceLogic() {
		//
		cmDao = DaoFactory.createFactory(DbType.MariaDB).getCommunityDao();
		spDao = DaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
		mbDao = DaoFactory.createFactory(DbType.MariaDB).getMemberDao();
	}
	
	@Override
	@Deprecated
	public void registCommunity(String communityName, String adminName, String email, String password){
		//
		SocialPerson admin = createPerson(adminName, email, password);
		Community community = new Community(communityName, "", admin);
		
		cmDao.createCommunity(community);
	}

	@Override
	public void registCommunity(String communityName, String description, String adminName, String email, String password) {
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
		
		cmDao.createCommunity(community);
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
	public void registCommunity(String communityName, String description, String email, List<String> category) {
		//
		if (isExistCommunityByName(communityName)) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 커뮤니티입니다.");
		}
		
		SocialPerson towner = spDao.readPerson(email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		Community community = new Community(communityName, description, towner, category);
		
		cmDao.createCommunity(community);
		Community com = cmDao.readCommunity(communityName);
		mbDao.joinAsCommunityMember(com.getId(), 1, towner);
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
		return cmDao.readCommunity(cmId);
	}
	

	@Override
	public void joinAsMember(String communityName, String name, String email, String password){
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
	public List<Community> findBelongCommunities(String email) {
		// 
		List<Community> commnities = cmDao.readAllCommunity();
		if (commnities == null) return null;
		
		List<Community> belongs = new ArrayList<>();
		for (Community community : commnities) {
			if (community.findMember(email) != null) {
				belongs.add(community);
			}
		}
		return belongs;
	}

	@Override
	public List<Community> findManagedCommnities(String email) {
		// 
		List<Community> communities =  new ArrayList<>();
		List<Integer> idList = mbDao.readManagedid(email);
		for(int i : idList){
			Community community = cmDao.readCommunity(i);
			communities.add(community);
		}
		
		return communities;
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
}