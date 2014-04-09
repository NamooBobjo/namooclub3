package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.CommunityMember;

public interface MemberDao {

	List<CommunityMember> findCommunityMember(String kind, String cmId);
	List<ClubMember> findClubMember(String kind, String clId);

	CommunityMember readCommunityMember(String kind, String cmId, String email);
	ClubMember readClubMember(String kind, String clId, String email);

	void joinAsCommunityMember(CommunityMember communityMember);
	void joinAsClubMember(ClubMember clubMember);

	void deleteCommunityMember(String email);
	void deleteClubMember(String kind, String email);

	void updateCommunityMember(CommunityMember communityMember);
	void updateClubMember(ClubMember clubMember);

}
