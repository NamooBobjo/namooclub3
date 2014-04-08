package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.SocialPerson;

public interface SocialPersonDao {

	List<SocialPerson> readAllPerson();
	SocialPerson readPerson(String userId);
	void createPerson(SocialPerson person);
	void deletePerson(String userId);
	void updatePerson(SocialPerson person);

}
