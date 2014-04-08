package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Club;

public interface ClubDao {
	
	List<Club> readAllClub();
	Club readClub(String clId);
	void createClub(Club club);
	void deleteClub(String clId);
	void updateClub(Club club);

}
