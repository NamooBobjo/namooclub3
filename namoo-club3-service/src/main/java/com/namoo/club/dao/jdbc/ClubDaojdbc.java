package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.domain.entity.Club;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class ClubDaojdbc implements ClubDao {

	@Override
	public List<Club> readAllClub(int cmid) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Club> clubs = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT clid, cmId, cgId, clName, clDescription, clDate FROM club WHERE cmId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmid);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				
				int id = resultSet.getInt("clid");
				int cgId = resultSet.getInt("cgId");
				String clubName = resultSet.getString("clName");
				String description = resultSet.getString("clDescription");
				
				Club club = new Club(cmid, id, cgId, clubName, description);
				clubs.add(club);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			quiet(resultSet, pstmt, conn);
		}
		
		return clubs;
	}

	@Override
	public Club readClub(int clid) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Club club = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT clid, cmId, cgId, clName, clDescription, clDate FROM club WHERE clid =?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, clid);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int clId = resultSet.getInt("clid");
				int cmId = resultSet.getInt("cmId");
				int cgId = resultSet.getInt("cgId");
				String clName = resultSet.getString("clName");
				String clDescription = resultSet.getString("clDescription");

				club = new Club(cmId, clId, cgId, clName, clDescription);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			quiet(resultSet, pstmt, conn);
		}
		return club;
	}

	@Override
	public void createClub(Club club) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO club ( cmId, cgId, clName, clDescription, clDate) VALUES (?,?,?,?, sysdate())";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println( Integer.parseInt(club.getCmid()) +" / " +club.getCgid());

			pstmt.setInt(1, Integer.parseInt(club.getCmid()));
			pstmt.setInt(2, club.getCgid());
			pstmt.setString(3, club.getName());
			pstmt.setString(4, club.getDescription());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			quiet(pstmt, conn);
		}
	}

	@Override
	public void deleteClub(int clid) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM club WHERE clid=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, clid);
			pstmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽 삭제중 오류가 발생하였습니다.");
		} finally {
			quiet(pstmt, conn);
		}
	}

	@Override
	public void updateClub(Club club) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE club SET clName=?, clDescription=? WHERE clid=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, club.getName());
			pstmt.setString(2, club.getDescription());
			pstmt.setInt(3, Integer.parseInt(club.getId()));

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			quiet(pstmt, conn);
		}
	}

	// 소멸 메소드
	private void quiet(ResultSet resultSet, PreparedStatement pstmt,
			Connection conn) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		quiet(pstmt, conn);
	}

	private void quiet(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Club readClubByName(String clName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Club club = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT clid, cmId, cgId, clName, clDescription, clDate FROM club WHERE clName =?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, clName);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int clId = resultSet.getInt("clid");
				int cmId = resultSet.getInt("cmId");
				int cgId = resultSet.getInt("cgId");
				String clubName = resultSet.getString("clName");
				String clDescription = resultSet.getString("clDescription");

				club = new Club(cmId, clId, cgId, clubName, clDescription);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			quiet(resultSet, pstmt, conn);
		}
		return club;
	}

	@Override
	public List<Club> readAllClub() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Club> clubs = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT clid, cmId, cgId, clName, clDescription, clDate FROM club";
			pstmt = conn.prepareStatement(sql);
				
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				int cmid = resultSet.getInt("cmId");
				int id = resultSet.getInt("clid");
				int cgId = resultSet.getInt("cgId");
				String clubName = resultSet.getString("clName");
				String description = resultSet.getString("clDescription");
				
				Club club = new Club(cmid, id, cgId, clubName, description);
				clubs.add(club);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			quiet(resultSet, pstmt, conn);
		}
		
		return clubs;
	}
}
