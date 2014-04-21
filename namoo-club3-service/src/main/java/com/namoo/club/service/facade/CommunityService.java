package com.namoo.club.service.facade;

import java.util.List;

import com.namoo.club.domain.entity.Category;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.CommunityMember;


public interface CommunityService {
	
	/**
	 * [주민으로 등록된 경우] 커뮤니티 개설
	 * 
	 * 이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 존재하지 않는 주민인 경우 예외가 발생한다. 
	 * 
	 * @param communityName
	 * @param description
	 * @param email
	 * @param category
	 * @return 
	 * 
	 * @throws NamooRuntimeException
	 */
	public int registCommunity(String communityName, String description, String email, List<Category> category);

	/**
	 * 
	 * @param communityId
	 */
	public Community findCommunity(int communityId);

	/**
	 * [주민으로 등록되지 않은 경우] 커뮤니티 가입
	 * 
	 * 주민 가입을 처리하고 나서 커뮤니티에 가입한다.
	 * 이미 존재하는 주민인 경우 예외가 발생한다.
	 * 
	 * @param communityId
	 * @param name
	 * @param email
	 * @param password
	 * 
	 * @throws NamooRuntimeException
	 */
	public void joinAsMember(int communityId, String name, String email, String password);
	
	/**
	 * [주민으로 등록된 경우] 커뮤니티 가입
	 * 
	 * 이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 존재하지 않는 주민인 경우 예외가 발생한다. 
	 * 
	 * @param communityId
	 * @param email
	 * 
	 * @throws NamooRuntimeException
	 */
	public void joinAsMember(int communityId, String email);

	/**
	 * @return
	 */
	public List<Community> findAllCommunities();
	
	
	/**
	 * 이메일로 커뮤니티 회원 찾기
	 * 
	 * @param communityId
	 * @param email
	 * @return
	 */
	public CommunityMember findCommunityMember(int communityId, String email);
	
	
	/**
	 * 커뮤니티 회원목록 조회
	 * 
	 * @param communityId
	 * @return
	 */
	public List<CommunityMember> findAllCommunityMember(int communityId);
	
	/**
	 * 
	 * @param communityId
	 */
	public int countMembers(int communityId);
	
	/**
	 * @param communityId
	 */
	public void removeCommunity(int communityId);
	
	/**
	 * 자신이 회원으로 있는 커뮤니티 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Community> findJoinedCommunities(String email);

	/**
	 * 가입하지 않은 커뮤니티 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Community> findAllUnjoinedCommunities(String email);
	
	/**
	 * 자신이 관리하는 커뮤니티 목록조회
	 * 
	 * @param email
	 * @return
	 */
	public List<Community> findManagedCommnities(String email);

	/**
	 * 커뮤니티에서 탈퇴하기
	 * 
	 * @param communityId
	 * @param email
	 */
	public void withdrawalCommunity(int communityId, String email);

}