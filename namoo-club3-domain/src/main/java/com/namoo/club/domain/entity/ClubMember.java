package com.namoo.club.domain.entity;


public class ClubMember  {
	
		
	private String clubName;
	private SocialPerson rolePerson;
	
	//--------------------------------------------------------------------------
	
	public ClubMember(String clubName, SocialPerson rolePerson){
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
