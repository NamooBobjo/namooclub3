package com.namoo.club.domain.entity;


public class ClubManager {


	private String clubName;
	private SocialPerson rolePerson;
	private boolean reprentative;
	
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

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public boolean isReprentative() {
		return reprentative;
	}

	public void setReprentative(boolean reprentative) {
		this.reprentative = reprentative;
	}
}
