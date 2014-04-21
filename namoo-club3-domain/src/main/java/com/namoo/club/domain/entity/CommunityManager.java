package com.namoo.club.domain.entity;


public class CommunityManager  {

	
	private int communityId;
	private SocialPerson rolePerson;

	//--------------------------------------------------------------------------
	// constructor
	
	/**
	 * 
	 * @param rolePerson
	 */
	public CommunityManager(int communityId, SocialPerson rolePerson){
		//
		this.communityId = communityId;
		this.rolePerson = rolePerson;
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getName() {
		//
		return rolePerson.getName();
	}

	public String getEmail() {
		// 
		return rolePerson.getEmail();
	}

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

}