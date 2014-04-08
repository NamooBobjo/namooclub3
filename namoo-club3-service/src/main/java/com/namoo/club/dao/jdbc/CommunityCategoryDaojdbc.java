package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.namoo.club.dao.CommunityCategoryDao;
import com.namoo.club.domain.entity.Category;

public class CommunityCategoryDaojdbc implements CommunityCategoryDao {

	private static final boolean String = false;

	@Override
	public List<String> readAllCategory(int cmId) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<String> categories = new ArrayList<>();

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT cgId, cmId, cgName FROM communitycategory WHERE cmId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmId);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String cgName = resultSet.getString("cgName");
				categories.add(cgName);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			quiet(resultSet, pstmt, conn);
		}
		return categories;
	}

	@Override
	public String readCategory(int cmId, int cgId) {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String category="";
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT cgId, cmId, cgName FROM communitycategory WHERE cmId = ? AND cgId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmId);
			pstmt.setInt(2, cgId);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				category = resultSet.getString("cgName");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return category;
	}

	@Override
	public void deleteCategory(int cmId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM communitycategory WHERE cmId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmId);
			resultSet = pstmt.executeQuery();		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			quiet(resultSet, pstmt, conn);
		}
	}

	@Override
	public void createCategory(int cmId, List<String> category) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO communitycategory(cmId, cgName) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);

			for (String cate : category) {
				pstmt.setInt(1, cmId);
				pstmt.setString(2, cate);
				pstmt.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}
