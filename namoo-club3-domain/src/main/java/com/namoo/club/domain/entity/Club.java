package com.namoo.club.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Club  {

	private int id;
	private int cmid;

	private String name;
	private String description;
	private Date openDate;
	private Category category;
	

	private ClubManager manager;
	private List<ClubMember> members;
	
	//--------------------------------------------------------------------------
		// constructors
	
	public void setMembers(List<ClubMember> members) {
		this.members = members;
	}

	public Club() {
		//
	}
	
	public Club(int cmid, String clubName, String description, Category category) {
		// 
		this.cmid = cmid;
		this.name = clubName;
		this.description = description;
		this.category = category;
		
		this.members = new ArrayList<ClubMember>();
		this.openDate = new Date();
	}
	
	public Club(int cmid, String clubName, String description, Category category, SocialPerson admin) {
		// 
		this(cmid, clubName, description, category);
		
		setManager(admin);
		addMember(admin);
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public Club(int cmId, String clubName, String description,String category) {
		// 
		this.cmid = cmId;
		this.name = clubName;
		this.description = description;
		this.category.setName(category);
		this.members = new ArrayList<ClubMember>();
		this.openDate = new Date();
	}

	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getCmid() {
		return cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

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
	public ClubManager getManager() {
		return manager;
	}
	
	public List<ClubMember> getMembers() {
		return members;
	}
	
		
		public Date getOpenDate() {
		return openDate;
	}


	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	//--------------------------------------------------------------------------
		// public methods
		public ClubMember findMember(String email) {
			//
			for (ClubMember member : members) {
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
			ClubManager manager = new ClubManager(name, rolePerson);
			this.manager = manager;
		}
		/**
		 * 
		 * @param rolePerson
		 */
		public void addMember(SocialPerson rolePerson){
			//
			ClubMember member = new ClubMember(name, rolePerson);
			this.members.add(member);
		}
	
		public void removeMember(String email) {
			// 
			ClubMember found = null;
			for (ClubMember member : members) {
				if (member.getEmail().equals(email)) {
					found = member;
				}
			}
			if (found != null) {
				members.remove(found);
			}
		}
}
