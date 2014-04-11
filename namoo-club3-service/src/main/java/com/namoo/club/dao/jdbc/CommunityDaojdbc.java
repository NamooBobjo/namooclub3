package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.club.dao.CommunityDao;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class CommunityDaojdbc extends JdbcDaoTemplate implements CommunityDao {
	//
	@Override
	public List<Community> readAllCommunity() {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<Community> communities = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT cmid, cmName, cmDescription, cmDate FROM community";
			communities = new ArrayList<>();
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				Community community = convertToCommunity(resultSet);
				communities.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("readAllCommunity 오류");
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		return communities;
	}

	/**
	 * 조회결과를 커뮤니티 객체로 변환한다.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Community convertToCommunity(ResultSet resultSet) throws SQLException {
		// 
		int cmId = resultSet.getInt("cmid");
		String cmName = resultSet.getString("cmName");
		String cmDescription = resultSet.getString("cmDescription");
		Date cmDate = resultSet.getDate("cmDate");
		
		Community community = new Community(cmId, cmName, cmDescription, cmDate);
		return community;
	}

	@Override
	public List<Community> readJoinedCommunities(String email) {
		// 
		String sql = "SELECT cmid, cmName, cmDescription, cmDate FROM community WHERE email = ?";
		
		return readCommunitiesWithSql(sql, email);
	}

	@Override
	public List<Community> readManagedCommunities(String email) {
		// 
		String sql = "SELECT cmid, cmName, cmDescription, cmDate FROM community WHERE email = ?";
		
		return readCommunitiesWithSql(sql, email);
	}

	@Override
	public List<Community> readUnjoinedCommunities(String email) {
		// 
		String sql = "SELECT cmid, cmName, cmDescription, cmDate FROM community WHERE email = ?";
		
		return readCommunitiesWithSql(sql, email);
	}
	
	private List<Community> readCommunitiesWithSql(String sql, String email) {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<Community> communities = null;
		try {
			conn = DbConnection.getConnection();
			communities = new ArrayList<>();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				Community community = convertToCommunity(resultSet);
				communities.add(community);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("readCommunitiesWithSql 오류");
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		return communities;
	}
	
	@Override
	public Community readCommunity(int communityId) {
		//
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		Community community = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT cmid, cmName, cmDescription, cmDate FROM community WHERE cmid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, communityId);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()){
				int cmId = resultSet.getInt("cmid");
				String cmName = resultSet.getString("cmName");
				String cmDescription = resultSet.getString("cmDescription");
				Date cmDate = resultSet.getDate("cmDate");
				
				community = new Community(cmId, cmName, cmDescription, cmDate);
			}
			
		} catch (SQLException e) {
			throw NamooExceptionFactory.createRuntime("readCommunity 오류");
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		return community;
	}
	
	@Override
	public Community readCommunity(String cmName) {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		Community community = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT cmId, cmName, cmDescription, cmDate FROM community WHERE cmName = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cmName);
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next()){
				int cmId = resultSet.getInt("cmId");
				String cmDescription = resultSet.getString("cmDescription");
				Date cmDate = resultSet.getDate("cmDate");
				
				community = new Community(cmId, cmName, cmDescription, cmDate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("readCommunity 오류");
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		return community;
	}

	@Override
	public int createCommunity(Community community) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int cmId =0;
		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO community(cmName, cmDescription, cmDate) VALUES(?,?,sysdate())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, community.getName());
			pstmt.setString(2, community.getDescription());
			
			pstmt.executeUpdate();
			
			result = pstmt.getGeneratedKeys();
			if(result.next()) {
				cmId = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("createCommunity 오류");
		}finally{
			closeQuietly(result, pstmt, conn);
		}
		return cmId;
	}

	@Override
	public void deleteCommunity(int cmId) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM Community WHERE cmId = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cmId);
			
			int count = pstmt.executeUpdate();
			System.out.println(count);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("deleteCommunity 오류");
		}finally{
			closeQuietly(pstmt, conn);
		}
	}

	@Override
	public void updateCommunity(Community community) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE community SET cmName = ?, cmDescription = ? WHERE cmId = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, community.getName());
			pstmt.setString(2, community.getDescription());
			pstmt.setInt(3, community.getId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("updateCommunity 오류");
		}finally{
			closeQuietly(pstmt, conn);
		}
	}

}
