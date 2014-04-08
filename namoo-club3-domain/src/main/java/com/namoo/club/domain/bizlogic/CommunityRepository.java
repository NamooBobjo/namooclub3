package com.namoo.club.domain.bizlogic;
import java.util.HashMap;
import java.util.Map;

import com.namoo.club.domain.entity.Community;


public class CommunityRepository {

	private static CommunityRepository instance = new CommunityRepository();
	
	public Map<String, Community> communitiesMap;

	private CommunityRepository(){
		//
		communitiesMap = new HashMap<String, Community>();
	}
	
	public static CommunityRepository getInstance() {
		//
		return instance;
	}

	/**
	 * 
	 * @param community
	 */
	public void addCommunity(Community community){
		//
		communitiesMap.put(community.getName(), community);
	}

	/**
	 * 
	 * @param communityName
	 */
	public void removeCommunity(String communityName){
		//
		communitiesMap.remove(communityName);
	}

	/**
	 * @param communityName
	 * @return
	 */
	public Community findCommunity(String communityName) {
		// 
		return communitiesMap.get(communityName);
	}

}