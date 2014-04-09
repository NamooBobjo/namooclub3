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


public class CommunityDaojdbc implements CommunityDao {
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
				int cmId = resultSet.getInt("cmid");
				String cmName = resultSet.getString("cmName");
				String cmDescription = resultSet.getString("cmDescription");
				Date cmDate = resultSet.getDate("cmDate");
				
				Community community = new Community(cmId, cmName, cmDescription, cmDate);
				communities.add(community);
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
			e.printStackTrace();
		}finally{
			quiet(resultSet, pstmt, conn);
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
		}finally{
			quiet(resultSet, pstmt, conn);
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
				cmId = result.getInt("cmid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			quiet(result, pstmt, conn);
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
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
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
		}finally{
			if(conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(pstmt != null) 
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
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

	
}
