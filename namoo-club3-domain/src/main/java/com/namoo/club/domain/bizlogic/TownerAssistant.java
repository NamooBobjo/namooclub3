package com.namoo.club.domain.bizlogic;

import com.namoo.club.domain.entity.SocialPerson;


public class TownerAssistant {

	private TownerRepository townerRepo;

	public TownerAssistant(){
		//
		townerRepo = TownerRepository.getInstance();
	}

	/**
	 * 
	 * @param email
	 * @param password
	 */
	public boolean loginAsTowner(String email, String password){
		//
		SocialPerson towner = townerRepo.findPerson(email);
		if (towner != null && towner.getPassword().equals(password)) {
			return true;
		}
		
		return false;
	}

}