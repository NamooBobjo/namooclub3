package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.CommunityManager;

public interface CommunityDao {

	List<Community> readAllCommunity();
	Community readCommunity(int communityId);
	CommunityManager readManager(String email, int communityId);
	
	int createCommunity(Community community);
	void deleteCommunity(int cmId);
	void updateCommunity(Community community);
	
	List<Community> readJoinedCommunities(String email);
	List<Community> readManagedCommunities(String email);
	List<Community> readUnjoinedCommunities(String email);
}
