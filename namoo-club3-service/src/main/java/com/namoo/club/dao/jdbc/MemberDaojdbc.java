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
			
			while(resultSet.next()){
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

	@Override
	public List<Integer> readManagedid(String email) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<Integer> idList = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager FROM member WHERE kind = 1 AND mainManager = 1 AND email = ?";
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
			quiet(resultSet, pstmt, conn);
		}
		return idList;
	}
	public SocialPerson findPerson(String email){
				
		SocialPersonDao dao = MariaDBDaoFactory.createFactory(DbType.MariaDB).getSocialPersonDao();
		SocialPerson person = dao.readPerson(email);
		
		return person;
	}
	@Override
	public List<SocialPerson> readClubMembers(int clId) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<SocialPerson> clubMembers = new ArrayList<>();
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, id, kind, mainManager, manager FROM member WHERE kind = 2 AND id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clId);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				String email = resultSet.getString("email");
				SocialPerson person = findPerson(email);
				clubMembers.add(person);
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
	public void updateClubMember(int clId, String email, int manager) {
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

