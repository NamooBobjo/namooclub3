package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Category;

public interface CommunityCategoryDao {

	List<Category> readAllCategory(int cmId);
	Category readCategory(int cmId, int cgId);
	void deleteCategory(int cmId);
	void createCategory(int cmId, Category category);
	
}
