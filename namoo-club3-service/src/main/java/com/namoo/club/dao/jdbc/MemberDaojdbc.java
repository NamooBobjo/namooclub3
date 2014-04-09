package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.MemberDao;
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;

public class MemberDaojdbc implements MemberDao {

	@Override
	public List<CommunityMember> findCommunityMember(String kind, String cmId) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<CommunityMember> communityMembers = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager FROM member WHERE kind = ? and id = ?";
			communityMembers = new ArrayList<>();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, cmId);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				String email = resultSet.getString("email");
				int id = resultSet.getInt("id");
				String mainManager = resultSet.getString("mainManager");
				
//				SocialPerson rolePerson = new SocialPerson();
//				Community community = new Community(id, communityName, description, date);
//				
//				CommunityMember communityMember = new CommunityMember(id, rolePerson);
//				communityMembers.add(communityMember);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(resultSet != null)
				try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return communityMembers;
	}
	
	@Override
	public List<ClubMember> findClubMember(String kind, String clId) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<ClubMember> clubMembers = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager, manager FROM member WHERE kind = ? and id = ?";
			clubMembers = new ArrayList<>();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, clId);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				String email = resultSet.getString("email");
				int id = resultSet.getInt("id");
				String mainManager = resultSet.getString("mainManager");
				String manager = resultSet.getString("manager");
				
				SocialPerson rolePerson = new SocialPerson();
				
//				ClubMember clubMember = new ClubMember(id, rolePerson, mainManager, manager);
//				clubMembers.add(clubMember);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(resultSet != null)
				try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return clubMembers;
	}

	@Override
	public CommunityMember readCommunityMember(String kind, String cmId, String email) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		CommunityMember communityMember = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager FROM member WHERE kind = ? and id = ? and email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, cmId);
			pstmt.setString(3, email);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				String userEmail = resultSet.getString("email");
				int id = resultSet.getInt("id");
				String mainManager = resultSet.getString("mainManager");
				
				SocialPerson rolePerson = new SocialPerson();
				
//				communityMember = new CommunityMember(id, rolePerson, mainManager);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(resultSet != null)
				try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return communityMember;
	}
	
	@Override
	public ClubMember readClubMember(String kind, String clId, String email) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		ClubMember clubMember = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager, manager FROM member WHERE kind = ? and id = ? and email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, clId);	
			pstmt.setString(3, email);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				String userEmail = resultSet.getString("email");
				int id = resultSet.getInt("id");
				String mainManager = resultSet.getString("mainManager");
				String manager = resultSet.getString("manager");
				
				SocialPerson rolePerson = new SocialPerson();
				
//				clubMember = new ClubMember(id, rolePerson, mainManager, manager);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(resultSet != null)
				try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return clubMember;
	}

	@Override
	public void joinAsCommunityMember(CommunityMember communityMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO member(email, id, kind, mainManager) VALUES(?,?,1,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, communityMember.getEmail());
//			pstmt.setInt(2, communityMember.getCommunityId());
//			pstmt.setString(3, communityMember.getMainManager());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void joinAsClubMember(ClubMember clubMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO member(email, id, kind, mainManager, manager) VALUES(?,?,2,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clubMember.getEmail());
//			pstmt.setInt(2, clubMember.getClubId());
//			pstmt.setString(3, clubMember.getMainManager());
//			pstmt.setString(4, clubMember.getManager());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void deleteCommunityMember(String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM member WHERE email = ? and kind = 1";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			int count = pstmt.executeUpdate();
			System.out.println(count);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	@Override
	public void deleteClubMember(String kind, String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM member WHERE email = ? and kind = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, kind);
			
			int count = pstmt.executeUpdate();
			System.out.println(count);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void updateCommunityMember(CommunityMember communityMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE member SET mainManager = ? WHERE kind = 1 and id = ? and  email = ? ";
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, communityMember.getMainManager());
//			pstmt.setInt(2, communityMember.getCommunityId());
//			pstmt.setString(3, communityMember.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void updateClubMember(ClubMember clubMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE member SET mainManager = ?, manager = ? WHERE kind = 2 and id = ? and  email = ? ";
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, clubMember.getMainManager());
//			pstmt.setString(2, clubMember.getManager());
//			pstmt.setInt(3, clubMember.getClubId());
			pstmt.setString(4, clubMember.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
