package com.namoo.club.domain.entity;

public class Category {
	//
	private int id;
	private String name;
	private int cmId;
	
	//--------------------------------------------------------------------------
	// constructor
	
	public Category() {
		//
	}
	
	public Category(int id, String name) {
		// 
		this.id = id;
		this.name = name;
	}
	
	public Category(int cmId, int cgId, String cgName) {
		//
		this.cmId = cmId;
		this.id = cgId;
		this.name = cgName;
	}
	
	public Category(String categoryName) {
		// 
		this.name = categoryName;
	}
	
	//--------------------------------------------------------------------------

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCmId() {
		return cmId;
	}

	public void setCmId(int cmId) {
		this.cmId = cmId;
	}
}
