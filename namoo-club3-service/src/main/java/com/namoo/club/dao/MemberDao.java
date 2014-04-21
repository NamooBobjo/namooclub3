package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.ClubManager;
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.CommunityManager;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;

public interface MemberDao {

	List<CommunityMember> readCommunityMembers(int communityId);
	CommunityMember readCommunityMember(int communityId, String email);
	List<ClubMember> readClubMembers(int clubId);
	ClubMember readClubMember(int clubId, String email);
	CommunityManager readCommunityManager(int communityId);
	List<ClubManager> readClubManagers(int clubId);
	
	void joinAsCommunityMember(int communityId, int mainManager,SocialPerson person);
	void joinAsClubMember(int clubId, int mainManager, SocialPerson person);
	void deleteCommunityMember(int communityId, String email);
	void deleteClubMember(int clubId, String email);
	void updateClubMember(int clubId, String email, boolean isManager);
}
