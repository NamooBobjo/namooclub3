package com.namoo.club.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Community  {

	
	private int cmId;
	private String name;
	private String description;
	private List<Category> category;

	private Date openDate;
	private List<Club> clubs;

	private CommunityManager manager;
	private List<CommunityMember> members;

	//--------------------------------------------------------------------------
	// constructors
	
	public Community(){
		//
	}
	
//	/**
//	 * 
//	 * @param communityId
//	 * @param admin
//	 */
//	
	public Community(String communityName, String description, SocialPerson admin, Date date){
		//
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.openDate = date;
		this.clubs = new ArrayList<Club>();
		
		setManager(admin);
		addMember(admin);
	}
//	
	public Community(int id, String communityName, String description, Date date){
		//
		this.cmId = id;
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.openDate = date;
		this.clubs = new ArrayList<Club>();
	}
//	
//	public Community(int id, String communityName, String description, SocialPerson admin){
//		//
//		this.cmId = id;
//		this.name = communityName;
//		this.description = description;
//		this.members = new ArrayList<CommunityMember>();
//		this.openDate = new Date();
//		this.clubs = new ArrayList<Club>();
//		
//		setManager(admin);
//		addMember(admin);
//	}
//	
	public Community(int communityId, String description, SocialPerson admin, List<Category> category){
		//
		this.cmId = communityId;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.openDate = new Date();
		this.setCategory(category);
		this.clubs = new ArrayList<Club>();
		
		setManager(admin);
		addMember(admin);
	}
	
	public Community(int id, String communityName, String description, SocialPerson admin, List<Category> category){
		//
		this.cmId = id;
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.openDate = new Date();
		this.setCategory(category);
		this.clubs = new ArrayList<Club>();
		
		setManager(admin);
		addMember(admin);
	}
	
	public int getId() {
		return cmId;
	}	

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

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}


	public List<Club> getClubs() {
		return clubs;
	}

	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	
	public void setMembers(List<CommunityMember> members) {
		this.members = members;
	}
	
	public void setManager(CommunityManager manager) {
		this.manager = manager;
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
		CommunityManager manager = new CommunityManager(cmId, rolePerson);
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