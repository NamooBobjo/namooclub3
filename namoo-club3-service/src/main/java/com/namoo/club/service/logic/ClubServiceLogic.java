package com.namoo.club.service.logic;

import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityCategoryDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.domain.entity.Club;
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class ClubServiceLogic implements ClubService {

	private ClubDao clubdao; 
	private SocialPersonDao socialdao;
	private MemberDao memberdao;
	private CommunityDao communitydao;
	private CommunityCategoryDao categorydao;
			
	public ClubServiceLogic() {
		clubdao = DaoFactory.createFactory(DbType. MariaDB).getClubDao();
		socialdao = DaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
		memberdao = DaoFactory.createFactory(DbType.MariaDB).getMemberDao();
		communitydao = DaoFactory.createFactory(DbType.MariaDB).getCommunityDao();
		categorydao = DaoFactory.createFactory(DbType.MariaDB).getCommunityCategoryDao();
	}

	private boolean isExistClubByName(int cmId, String clubName) {
		// 
		List<Club> clubs = clubdao.readAllClub(cmId);
		for(Club club : clubs){
			if(club.getName().equals(clubName)){
				return true;
			}
		}		
		return false;
	}


	private SocialPerson createPerson(String name, String email,
			String password) {
		//
		SocialPerson person = new SocialPerson(name, email, password);
		socialdao.createPerson(person);
		
		return person;
	}

	@Override
	public void registClub(int cmId, String category, String clubName, String description, String email) {
		//
		if (isExistClubByName(cmId, clubName)) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 클럽입니다.");
		}
		
		SocialPerson towner = socialdao.readPerson(email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		
		Club club = new Club(cmId, category, clubName, description, towner);
		clubdao.createClub(club);	
		
	}

	@Override
	public Club findClub(String clubName) {
		//
		return clubdao.readClubByName(clubName);
	}

	@Override
	public void joinAsMember(String clubName, String name, String email,String password) {
		
		Club club = clubdao.readClubByName(clubName);
		
		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		
		int clId = Integer.parseInt(club.getId());
		
		if (isExistMember(clId, email)) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}

		SocialPerson towner = createPerson(name, email, password);
		club.addMember(towner);		
		
		memberdao.joinAsClubMember(clId, 0, towner);
	}

	private boolean isExistMember(int clId, String email) {
		
		List<SocialPerson> persons = memberdao.readClubMembers(clId);
		for(SocialPerson person : persons){
			if(person.getEmail().equals(email)){
				return true;
			}
		}		
		return false;
	}


	@Override
	public void joinAsMember(String clubName, String email) {
		//
		Club club = clubdao.readClubByName(clubName);
		
		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		
		SocialPerson towner = socialdao.readPerson(email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		int clid = Integer.parseInt(club.getId());
		club.addMember(towner);
		memberdao.joinAsClubMember(clid, 0, towner);
	}

	@Override
	public List<Club> findAllClubs() {
		// 
		return clubdao.readAllClub();
	}

	@Override
	public ClubMember findClubMember(String clubName, String email) {
		//
		Club club =  clubdao.readClubByName(clubName);
		
		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		
		for (ClubMember member : club.getMembers()) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		return null;
	}

	@Override
	public List<ClubMember> findAllClubMember(String clubName) {
		// 
		Club club = clubdao.readClubByName(clubName);
		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		
		int clId = Integer.parseInt(club.getId());
		List<SocialPerson> persons = memberdao.readClubMembers(clId);
		List<ClubMember> clubmembers= new ArrayList<>();

		for(SocialPerson person : persons){
			ClubMember member = new ClubMember(clubName, person);
			clubmembers.add(member);
		}		
		return clubmembers;
	}

	@Override
	public int countMembers(String clubName) {
		//
		Club club =clubdao.readClubByName(clubName);
		if (club != null) {
			return club.getMembers().size();
		}
		return 0;
	}

	@Override
	public void removeClub(String clubName) {
		
		Club club = clubdao.readClubByName(clubName);
		int clid = Integer.parseInt(club.getId());
		clubdao.deleteClub(clid);
	}

	@Override
	public List<Club> findBelongClub(int cmId, String email) {
		//
		List<Club> clubs = clubdao.readAllClub(cmId);
		if (clubs == null) return null;
		
		List<Club> belongs = new ArrayList<>();
		for (Club club : clubs) {
			if (club.findMember(email) != null&&club.getCmid().equals(cmId)) {
				belongs.add(club);
			}
		}
		return belongs;
	}

	@Override
	public List<Club> findClubsById(String id) {
		// 
		int cmid = Integer.parseInt(id);
		List<Club> clubs = clubdao.readAllClub(cmid);
		return clubs;
	}
	
	@Override
	public List<Club> findManagedClub(int cmId, String email) {
		//
		List<Club> clubs = clubdao.readAllClub(cmId);
		if (clubs == null) return null;
		
		List<Club> manages = new ArrayList<>();
		for (Club club : clubs) {
			if (club.getManager().getEmail().equals(email)&&club.getCmid().equals(cmId)) {
				manages.add(club);
			}
		}
		return manages;
	}

	@Override
	public void withdrawalClub(String clubName, String email) {
		//
		Club club =clubdao.readClubByName(clubName);
		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		
		int clid = Integer.parseInt(club.getId());
		memberdao.deleteClubMember(clid, email);
			
	}
}
