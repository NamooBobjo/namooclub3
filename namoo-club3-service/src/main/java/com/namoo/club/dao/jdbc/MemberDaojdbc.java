package com.namoo.club.dao.jdbc;

import java.util.List;

import com.namoo.club.dao.MemberDao;
import com.namoo.club.domain.entity.SocialPerson;

public class MemberDaojdbc implements MemberDao {

	@Override
	public List<SocialPerson> findCommunityMember(String kind, String cmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SocialPerson> findClubMember(String kind, String clId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocialPerson readCommunityMember(String kind, String cmId,
			String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocialPerson readClubMember(String kind, String clId, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinAsCommunityMember(String kind, String cmId,
			String mainManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void joinAsClubMember(String kind, String cmId, String mainManager,
			String manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCommunityMember(String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteClubMember(String kind, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCommunityMember(String kind, String email,
			String mainManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClubMember(String kind, String email, String mainManager,
			String manager) {
		// TODO Auto-generated method stub

	}

}
