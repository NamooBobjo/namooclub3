package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Club;

public interface ClubDao {
	
	List<Club> readAllClub(int cmid);
	Club readClub(int clid);
	void createClub(Club club);
	void deleteClub(int clid);
	void updateClub(Club club);

}
