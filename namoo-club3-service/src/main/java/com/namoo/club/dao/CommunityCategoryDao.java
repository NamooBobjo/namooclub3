package com.namoo.club.dao;

import java.util.List;

public interface CommunityCategoryDao {

	List<String> readAllCategory(int cmId);
	String readCategory(int cmId, int cgId);
	void deleteCategory(int cmId);
	void createCategory(int cmId, List<String> category);
	
}
