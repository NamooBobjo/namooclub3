package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.SocialPerson;

public interface MemberDao {

	List<SocialPerson> findCommunityMember(String kind, String cmId);
	List<SocialPerson> findClubMember(String kind, String clId);

	SocialPerson readCommunityMember(String kind, String cmId, String email);
	SocialPerson readClubMember(String kind, String clId, String email);

	void joinAsCommunityMember(String kind, String cmId, String mainManager);
	void joinAsClubMember(String kind, String cmId, String mainManager, String manager);

	void deleteCommunityMember(String email);
	void deleteClubMember(String kind, String email);

	void updateCommunityMember(String kind, String email, String mainManager);
	void updateClubMember(String kind, String email, String mainManager, String manager);

}
