package com.namoo.club.dao.factory;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.dao.CommunityCategoryDao;
import com.namoo.club.dao.CommunityDao;
import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;



public abstract class DaoFactory {
	public static enum DbType{
		MariaDB
	}
	
	public static DaoFactory createFactory(DbType dbType){
		
		if(dbType == DbType.MariaDB){
			return new MariaDBDaoFactory();
		}	
		return null;
	}
	
	public abstract ClubDao getClubDao();
	public abstract CommunityDao getCommunityDao();
	public abstract MemberDao getMemberDao();
	public abstract SocialPersonDao getSocialPersonDao();
	public abstract CommunityCategoryDao getCommunityCategoryDao();
}
