package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.MemberDao;
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;

public class MemberDaojdbc extends JdbcDaoTemplate implements MemberDao {

	@Override
	public List<CommunityMember> readCommunityMembers(int cmId) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<CommunityMember> communityMembers = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.email, a.id, c.cmName, a.kind, a.mainManager, b.username FROM member a " +
					"INNER JOIN socialPerson b ON a.email = b.email " + 
					"INNER JOIN community c ON a.id = c.cmId " + 
					"WHERE kind = 1 and id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cmId);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				SocialPerson rolePerson = new SocialPerson();
				rolePerson.setEmail(resultSet.getString("email"));
				rolePerson.setName(resultSet.getString("username"));
				
				String communityName = resultSet.getString("cmName");
				CommunityMember member = new CommunityMember(communityName, rolePerson);
				
				communityMembers.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		return communityMembers;
	}

	@Override
	public List<Integer> readManagedid(String email) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<Integer> idList = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager FROM member WHERE kind = '1' AND mainManager = '1' AND email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				idList.add(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		return idList;
	}
	
	@Override
	public List<ClubMember> readClubMembers(int clId) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<ClubMember> clubMembers = new ArrayList<>();
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.email, a.id, c.clName, a.kind, a.mainManager, a.manager, b.username FROM member a " +
					"INNER JOIN socialPerson b ON a.email = b.email " +
					"INNER JOIN club c ON a.id = c.clId " +
					"WHERE kind = '2' AND id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clId);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				SocialPerson rolePerson = new SocialPerson();
				rolePerson.setEmail(resultSet.getString("email"));
				rolePerson.setName(resultSet.getString("username"));
				
				String clubName = resultSet.getString("clName");
				ClubMember member = new ClubMember(clubName, rolePerson); 
				
				clubMembers.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		
		return clubMembers;
	}


	@Override
	public ClubMember readClubMember(int clId, String email) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		ClubMember member = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.email, a.id, c.clName, a.kind, a.mainManager, a.manager, b.username FROM member a " +
					"INNER JOIN socialPerson b ON a.email = b.email " +
					"INNER JOIN club c ON a.id = c.clId " +
					"WHERE a.kind = '2' AND a.id = ? AND a.email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clId);
			pstmt.setString(2, email);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				SocialPerson rolePerson = new SocialPerson();
				rolePerson.setEmail(resultSet.getString("email"));
				rolePerson.setName(resultSet.getString("username"));
				String clubName = resultSet.getString("clName");
				member = new ClubMember(clubName, rolePerson); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		
		return member;
	}
	
	@Override
	public void joinAsCommunityMember(int cmId, int mainManager, SocialPerson person) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO member(email, id, kind, mainManager,manager) VALUES(?,?,'1',?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getEmail());
			pstmt.setInt(2, cmId);
			pstmt.setInt(3, mainManager);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(pstmt, conn);
		}
	}

	@Override
	public void joinAsClubMember(int clId, int mainManager, SocialPerson person) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO member(email, id, kind, mainManager, manager) VALUES(?,?,2,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getEmail());
			pstmt.setInt(2, clId);
			pstmt.setInt(3, mainManager);
				
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(pstmt, conn);
		}
	}

	@Override
	public void deleteCommunityMember(int cmId, String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM member WHERE email = ? AND kind = '1' AND id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, cmId);			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(pstmt, conn);
		}
	}
	
	@Override
	public void deleteClubMember(int clId, String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM member WHERE email = ? AND kind = 2 AND id = ?";
			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, email);
			pstmt.setInt(2, clId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(pstmt, conn);
		}
	}


	@Override
	public void updateClubManager(int clId, String email, int manager) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE member SET manager = ? WHERE kind = 2 AND id = ? AND email = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, manager);
			pstmt.setInt(2, clId);
			pstmt.setString(3, email);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeQuietly(pstmt, conn);
		}
	}

}

