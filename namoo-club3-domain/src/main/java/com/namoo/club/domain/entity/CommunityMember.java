package com.namoo.club.domain.entity;

public class CommunityMember   {

	
	private String communityName;
	private SocialPerson rolePerson;

	//--------------------------------------------------------------------------
	// constructor
	
	/**
	 * 
	 * @param rolePerson
	 */
	public CommunityMember(String communityName, SocialPerson rolePerson){
		//
		this.communityName = communityName;
		this.rolePerson = rolePerson;
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getName() {
		return rolePerson.getName();
	}
	
	public String getEmail() {
		return rolePerson.getEmail();
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

}