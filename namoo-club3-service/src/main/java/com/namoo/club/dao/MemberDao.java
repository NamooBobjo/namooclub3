package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;

public interface MemberDao {

	List<SocialPerson> readCommunityMembers(int cmId);
	List<SocialPerson> readAllClubMember(int clId);
		
	void joinAsCommunityMember(int cmid, int mainManager,SocialPerson person);
	void joinAsClubMember(int clid, int mainManager, SocialPerson person);

	void deleteCommunityMember(int cmId, String email);
	void deleteClubMember(int clId, String email);

	void updateClubMember(ClubMember clubMember);

}
