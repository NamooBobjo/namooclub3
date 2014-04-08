package com.namoo.club.domain.entity;


public class CommunityManager  {

	
	private String communityName;
	private SocialPerson rolePerson;

	//--------------------------------------------------------------------------
	// constructor
	
	/**
	 * 
	 * @param rolePerson
	 */
	public CommunityManager(String communityName, SocialPerson rolePerson){
		//
		this.communityName = communityName;
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
	

}