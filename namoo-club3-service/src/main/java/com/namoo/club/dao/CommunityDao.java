package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Community;

public interface CommunityDao {

	List<Community> readAllCommunity();
	Community readCommunity(int communityId);
	int createCommunity(Community community);
	void deleteCommunity(int cmId);
	void updateCommunity(Community community);

}
