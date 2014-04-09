package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.SocialPerson;

public interface MemberDao {

	List<SocialPerson> readCommunityMembers(int cmId);
	List<SocialPerson> readClubMembers(int clId);
	
	List<Integer> readManagedid(String email);
		
	void joinAsCommunityMember(int cmid, int mainManager,SocialPerson person);
	void joinAsClubMember(int clid, int mainManager, SocialPerson person);

	
	
	void deleteCommunityMember(int cmId, String email);
	void deleteClubMember(int clId, String email);

	void updateClubMember(int clId, String email, int manager);

}
