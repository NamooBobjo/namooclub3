package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Category;

public interface CommunityCategoryDao {

	List<Category> readAllCategory(String cmId);
	Category readCategory(String cmId, String cgId);
	void deleteCategory(String cmId);
	void createCategory(List<Category> category);
	
}
