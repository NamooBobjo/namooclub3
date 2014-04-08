package com.namoo.club.domain.bizlogic;

import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.SocialPerson;


public class CommunityAssistant {

	private CommunityRepository communityRepo;

	public CommunityAssistant(){
		//
		this.communityRepo = CommunityRepository.getInstance();
	}

	/**
	 * 
	 * @param communityName
	 * @param adminName
	 * @param email
	 * @param password
	 */
	public void createCommunity(String communityName, String adminName, String email, String password){
		//
		SocialPerson admin = createPerson(adminName, email, password);
		Community community = new Community(communityName, "", admin);
		
		communityRepo.addCommunity(community);
	}

	/**
	 * 
	 * @param name
	 * @param email
	 * @param password
	 */
	public SocialPerson createPerson(String name, String email, String password){
		//
		SocialPerson towner = new SocialPerson(name, email, password);
		TownerRepository townerRepo = TownerRepository.getInstance();
		townerRepo.addTowner(towner);
		
		return towner;
	}

	/**
	 * 
	 * @param communityName
	 * @param name
	 * @param email
	 * @param password
	 */
	public void joinAsMember(String communityName, String name, String email, String password){
		//
		TownerRepository townerRepo = TownerRepository.getInstance();
		SocialPerson towner = townerRepo.findPerson(email);
		
		if (towner == null) {
			towner = createPerson(name, email, password);
		}
		
		joinAsMember(communityName, towner);
	}

	/**
	 * 
	 * @param communityName
	 * @param member
	 */
	public void joinAsMember(String communityName, SocialPerson member){
		//
		Community community = communityRepo.findCommunity(communityName);
		community.addMember(member);
	}

	/**
	 * 
	 * @param communityName
	 */
	public void dropCommunity(String communityName){
		//
		communityRepo.removeCommunity(communityName);
	}

}