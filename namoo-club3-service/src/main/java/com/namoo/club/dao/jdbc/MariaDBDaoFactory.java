package com.namoo.club.dao.jdbc;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityCategoryDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory;


public class MariaDBDaoFactory extends DaoFactory {

	@Override
	public ClubDao getClubDao() {
	
		return new ClubDaojdbc();
	}

	@Override
	public CommunityDao getCommunityDao() {
		// TODO Auto-generated method stub
		return new CommunityDaojdbc();
	}

	@Override
	public MemberDao getMemberDao() {
		// TODO Auto-generated method stub
		return new MemberDaojdbc();
	}

	@Override
	public SocialPersonDao getSocialPersonDao() {
		// TODO Auto-generated method stub
		return new SocialPersonDaojdbc();
	}

	@Override
	public CommunityCategoryDao getCommunityCategoryDao() {
		// 
		return new CommunityCategoryDaojdbc();
	}



}
