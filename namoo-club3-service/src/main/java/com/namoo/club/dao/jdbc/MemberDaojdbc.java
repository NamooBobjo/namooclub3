package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.MemberDao;
import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.domain.entity.ClubMember;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.domain.entity.CommunityMember;
import com.namoo.club.domain.entity.SocialPerson;

public class MemberDaojdbc implements MemberDao {

	@Override
	public List<SocialPerson> readCommunityMembers(int cmId) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<SocialPerson> communityMembers = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager FROM member WHERE kind = 1 and id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cmId);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				String email = resultSet.getString("email");
				SocialPerson person = findPerson(email);
				communityMembers.add(person);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			quiet(resultSet, pstmt, conn);
		}
		return communityMembers;
	}
	
	public SocialPerson findPerson(String email){
				
		SocialPersonDao dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
		SocialPerson person = dao.readPerson(email);
		
		return person;
	}
	@Override
	public List<SocialPerson> readAllClubMember(int clId) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<SocialPerson> clubMembers = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager, manager FROM member WHERE kind = ? and id = ?";
			clubMembers = new ArrayList<>();
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				String email = resultSet.getString("email");
				int id = resultSet.getInt("id");
				String mainManager = resultSet.getString("mainManager");
				String manager = resultSet.getString("manager");
				
				SocialPerson rolePerson = new SocialPerson();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			quiet(resultSet, pstmt, conn);
		}
		return clubMembers;
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
			quiet(pstmt, conn);
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
			quiet(pstmt, conn);
		}
	}

	@Override
	public void deleteCommunityMember(int cmId, String email) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM member WHERE email = ? AND kind = 1 AND id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, cmId);			
			pstmt.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			quiet(pstmt, conn);
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
			
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			quiet(pstmt, conn);
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
			quiet(pstmt, conn);
			}
	}
	
	
	//소멸 메소드
	private void quiet(ResultSet resultSet, PreparedStatement pstmt, Connection conn) {
		if(resultSet!=null){
			try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
		}
		quiet(pstmt,conn);
	}
	
	private void quiet(PreparedStatement pstmt, Connection conn) {
	
		if(pstmt !=null){	
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		if(conn!=null){
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}	
	}
}

