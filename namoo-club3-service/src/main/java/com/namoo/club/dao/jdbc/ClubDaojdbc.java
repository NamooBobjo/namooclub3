package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.ClubDao;
import com.namoo.club.domain.entity.Category;
import com.namoo.club.domain.entity.Club;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class ClubDaojdbc extends JdbcDaoTemplate implements ClubDao {

	@Override
	public List<Club> readAllClub(int cmid) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Club> clubs = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.clid, a.cmId, a.cgId, a.clName, a.clDescription, a.clDate, b.cgName FROM club a " +
					"LEFT JOIN communityCategory b ON a.cgId = b.cgId WHERE a.cmId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmid);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				clubs.add(convertToClub(resultSet));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽목록조회 중 오류가 발생하였습니다.");
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		
		return clubs;
	}

	@Override
	public Club readClub(int clubId) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Club club = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.clid, a.cmId, a.cgId, a.clName, a.clDescription, a.clDate, b.cgName FROM club a " +
						"LEFT JOIN communityCategory b ON a.cgId = b.cgId WHERE a.clid = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, clubId);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				club = convertToClub(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽 조회중 오류가 발생하였습니다.");
		} finally {
			closeQuietly(resultSet, pstmt, conn);
		}
		return club;
	}

	@Override
	public Integer createClub(Club club) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO club ( cmId, cgId, clName, clDescription, clDate) VALUES (?,?,?,?, sysdate())";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println( club.getCmid() +" / " + club.getCategory().getId());

			pstmt.setInt(1, club.getCmid());
			pstmt.setInt(2, club.getCategory().getId());
			pstmt.setString(3, club.getName());
			pstmt.setString(4, club.getDescription());
			pstmt.executeUpdate();
			
			ResultSet genKeys = pstmt.getGeneratedKeys();
			if (genKeys.next()) {
				club.setId(genKeys.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽 생성중 오류가 발생하였습니다.");
		} finally {
			closeQuietly(pstmt, conn);
		}
		return club.getId();
	}

	@Override
	public void deleteClub(int clid) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM club WHERE clid = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, clid);
			pstmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽 삭제중 오류가 발생하였습니다.");
		} finally {
			closeQuietly(pstmt, conn);
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
			pstmt.setInt(3, club.getId());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽수정 중 오류가 발생하였습니다.");
		} finally {
			closeQuietly(pstmt, conn);
		}
	}


	
	@Override
	public Club readClubByName(String clName) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Club club = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.clid, a.cmId, a.cgId, a.clName, a.clDescription, a.clDate, b.cgName FROM club a " +
						"LEFT JOIN communityCategory b ON a.cgId = b.cgId WHERE a.clName = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, clName);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				club = convertToClub(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽 조회중 오류가 발생하였습니다.");
		} finally {
			closeQuietly(resultSet, pstmt, conn);
		}
		return club;
	}

	@Override
	public List<Club> readAllClub() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Club> clubs = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT a.clid, a.cmId, a.cgId, a.clName, a.clDescription, a.clDate, b.cgName FROM club a " +
					"LEFT JOIN communityCategory b ON a.cgId = b.cgId";
			pstmt = conn.prepareStatement(sql);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				clubs.add(convertToClub(resultSet));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("클럽목록조회 중 오류가 발생하였습니다.");
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		
		return clubs;
	}
	
	//--------------------------------------------------------------------------
	/**
	 * 클럽조회결과를 Club 객체로 변환한다.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public Club convertToClub(ResultSet resultSet) throws SQLException {
		//
		int clId = resultSet.getInt("clid");
		int cmId = resultSet.getInt("cmId");

		int cgId = resultSet.getInt("cgId");
		String cgName = resultSet.getString("cgName");
		Category category = new Category(cgId, cgName);
		
		String clName = resultSet.getString("clName");
		String clDescription = resultSet.getString("clDescription");

		Club club = new Club(cmId, clName, clDescription, category);
		club.setId(clId);
		
		return club;
	}
}
