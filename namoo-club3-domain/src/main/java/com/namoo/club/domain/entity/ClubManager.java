package com.namoo.club.domain.entity;


public class ClubManager {


	private String clubName;
	private SocialPerson rolePerson;
	
	//--------------------------------------------------------------------------
	
	public ClubManager(String clubName, SocialPerson rolePerson){
		this.clubName = clubName;
		this.rolePerson = rolePerson;
	}
	
	//--------------------------------------------------------------------------
	
	public String getName(){
		return rolePerson.getName();
	}
	
	public String getEmail(){
		return rolePerson.getEmail();
	}
	

}
