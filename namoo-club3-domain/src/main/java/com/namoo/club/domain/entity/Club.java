package com.namoo.club.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Club  {

	

	private String cmid;
	private String id;
	private int cgid;
	

	private String name;
	private String description;
	private Date openDate;
	private String category;
	

	private ClubManager manager;
	private List<ClubMember> members;
	
	//--------------------------------------------------------------------------
		// constructors
	
	/**
	 * 
	 * @param clubName
	 * @param admin
	 */
	
	public Club (String cmid, String id, String category, String clubName, String description, SocialPerson admin) {
		//
		this.cmid=cmid;
		this.id = id;
		this.name = clubName;
		this.description= description;
		this.members = new ArrayList<ClubMember>();
		this.openDate = new Date();
		this.category = category;
		
		setManager(admin);
		addMember(admin);
		
	}
	
	public Club (String cmid, String id, String clubName, String description, SocialPerson admin) {
		//
		this.cmid=cmid;
		this.id = id;
		this.name = clubName;
		this.description= description;
		this.members = new ArrayList<ClubMember>();
		this.openDate = new Date();
		
		
		setManager(admin);
		addMember(admin);
		
	}
	public Club(int cmid, int id, int cgId, String clubName,String description) {
		// TODO Auto-generated constructor stub
		this.cmid = Integer.toString(cmid);
		this.id = Integer.toString(id);
		this.name = clubName;
		this.description = description;
		this.cgid = cgId;
		
		this.members = new ArrayList<ClubMember>();
		this.openDate = new Date();
	}


	
	@Deprecated
	public Club (String clubName, String description, SocialPerson admin) {
		//
		this.name = clubName;
		this.description= description;
		this.members = new ArrayList<ClubMember>();
		
		setManager(admin);
		addMember(admin);
	}
	
	//--------------------------------------------------------------------------
		// getter/setter
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCmid() {
		return cmid;
	}
	
	public String getId() {
		return id;
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
	public int getCgid() {
		return cgid;
	}

	public void setCgid(int cgid) {
		this.cgid = cgid;
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
