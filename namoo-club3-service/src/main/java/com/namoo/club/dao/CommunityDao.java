package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Community;

public interface CommunityDao {

	List<Community> readAllCommunity();
	Community readCommunity(String cmId);
	void createCommunity(Community community);
	void deleteCommunity(String cmId);
	void updateCommunity(Community community);

}
