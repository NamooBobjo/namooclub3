package com.namoo.club.service.facade;

import java.util.List;

import com.namoo.club.domain.entity.Club;
import com.namoo.club.domain.entity.ClubMember;


public interface ClubService {
	
	

	/**
	 * [주민으로 등록된 경우] 클럽 개설
	 */
	public void registClub(int cmId,String category,String clubName, String description, String email);

	/**
	 * 
	 * @param clubName
	 */
	public Club findClub(String clubName);

	/**
	 * [주민으로 등록되지 않은 경우] 클럽 가입
	 */
	public void joinAsMember(String clubName, String name, String email, String password);
	
	/**
	 * [주민으로 등록된 경우] 클럽 가입
	 */
	public void joinAsMember(String clubName, String email);

	/**
	 * @return
	 */
	public List<Club> findAllClubs();
	/**
	 * 이메일로 클럽 회원 찾기
	 */
	public ClubMember findClubMember(String clubName, String email);
	
	public List<Club> findClubsById(String id);
	/**
	 * 해당 커뮤니티에 속해있는 클럽 조회
	 */
	
	/**
	 * 클럽 회원목록 조회
	 */
	public List<ClubMember> findAllClubMember(String clubName);
	
	/**
	 * @param clubName
	 */
	public int countMembers(String clubName);
	
	/**
	 * @param clubName
	 */
	public void removeClub(String clubName);
	
	/**
	 * 자신이 회원으로 있는 클럽 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Club> findBelongClub(int cmId, String email);
	
	/**
	 * 자신이 관리하는 클럽 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Club> findManagedClub(int cmId, String email);

	/**
	 * 클럽에서 탈퇴하기
	 * 
	 * @param clubName
	 * @param email
	 */
	public void withdrawalClub(String clubName, String email);
}