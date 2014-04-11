package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.ClubManager;
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;

public interface MemberDao {

	List<CommunityMember> readCommunityMembers(int cmId);
	List<ClubMember> readClubMembers(int clId);
	List<Integer> readManagedid(String email);
	ClubMember readClubMember(int clId, String email);
	
	void joinAsCommunityMember(int cmid, int mainManager,SocialPerson person);
	void joinAsClubMember(int clid, int mainManager, SocialPerson person);
	void deleteCommunityMember(int cmId, String email);
	void deleteClubMember(int clId, String email);
	void updateClubMember(int clubId, String email, boolean isManager);
	List<ClubManager> readClubManagers(int clubId);
}
