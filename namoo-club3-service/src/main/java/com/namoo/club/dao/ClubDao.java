package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.domain.entity.Club;

public interface ClubDao {
	
	List<Club> readAllClub();
	List<Club> readAllClub(int cmid);
	Club readClub(int clid);
    Club readClubByName(String clName);
	Integer createClub(Club club);
	void deleteClub(int clid);
	void updateClub(Club club);

}
