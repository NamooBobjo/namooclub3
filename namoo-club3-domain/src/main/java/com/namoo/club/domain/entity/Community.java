package com.namoo.club.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Community  {

	
	private String id;
	private String name;
	private String description;
	private List<String> category;

	private Date openDate;
	private List<Club> clubs;

	private CommunityManager manager;
	private List<CommunityMember> members;

	//--------------------------------------------------------------------------
	// constructors
	
	/**
	 * 
	 * @param communityName
	 * @param admin
	 */
	
	public Community(String id, String communityName, String description, SocialPerson admin){
		//
		this.id = id;
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.openDate = new Date();
		this.clubs = new ArrayList<Club>();
		
		setManager(admin);
		addMember(admin);
	}
	
	public Community(String id, String communityName, String description, SocialPerson admin, List<String> category){
		//
		this.id = id;
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.openDate = new Date();
		this.setCategory(category);
		this.clubs = new ArrayList<Club>();
		
		setManager(admin);
		addMember(admin);
	}
	
	public String getId() {
		return id;
	}	

	@Deprecated
	public Community(String communityName, String description, SocialPerson admin){
		//
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		
		setManager(admin);
		addMember(admin);
	}

	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CommunityManager getManager() {
		return manager;
	}

	public List<CommunityMember> getMembers() {
		return members;
	}
	
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}


	public List<Club> getClubs() {
		return clubs;
	}

	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	//--------------------------------------------------------------------------
	// public methods
	
	public CommunityMember findMember(String email) {
		//
		for (CommunityMember member : members) {
			if(member.getEmail().equals(email)) {
				return member;
			};
		}
		return null;
	}
	
	/**
	 * 
	 * @param rolePerson
	 */
	public void setManager(SocialPerson rolePerson){
		//
		CommunityManager manager = new CommunityManager(name, rolePerson);
		this.manager = manager;
	}

	/**
	 * 
	 * @param rolePerson
	 */
	public void addMember(SocialPerson rolePerson){
		//
		CommunityMember member = new CommunityMember(name, rolePerson);
		this.members.add(member);
	}



	public void removeMember(String email) {
		// 
		CommunityMember found = null;
		for (CommunityMember member : members) {
			if (member.getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}

	public void addClub(Club club) {
		// 
		this.clubs.add(club);
	}

}