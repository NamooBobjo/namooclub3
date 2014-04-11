package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.CommunityCategoryDao;
import com.namoo.club.domain.entity.Category;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class CommunityCategoryDaojdbc extends JdbcDaoTemplate implements CommunityCategoryDao {

	@Override
	public List<Category> readAllCategory(int cmId) {

		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List<Category> categories = new ArrayList<>();

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT cgId, cmId, cgName FROM communitycategory WHERE cmId = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmId);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				categories.add(convertToCategory(resultSet));
			}

		} catch (SQLException e) {
			//
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("readAllCategory 오류");
		} finally {
			closeQuietly(resultSet, pstmt, conn);
		}
		return categories;
	}

	@Override
	public Category readCategory(int cmId, int cgId) {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		Category category = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT cgId, cmId, cgName FROM communitycategory WHERE cmId = ? AND cgId = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cmId);
			pstmt.setInt(2, cgId);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				category = convertToCategory(resultSet);
			}

		} catch (SQLException e) {
			//
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("readCategory 오류");
		} finally {
			closeQuietly(resultSet, pstmt, conn);
		}

		return category;
	}

	@Override
	public void deleteCategory(int cmId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM communitycategory WHERE cmId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("deleteCategory 오류");
		}finally{
			closeQuietly(pstmt, conn);
		}
	}

	@Override
	public void createCategory(int cmId, Category category) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO communitycategory(cmId, cgName) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);

				
				pstmt.setInt(1, cmId);
				pstmt.setString(2, category.getName());
				pstmt.executeUpdate();
				
			ResultSet genKeys = pstmt.getGeneratedKeys();
				if (genKeys.next()) {
					category.setId(genKeys.getInt(1));
				}
		
		} catch (SQLException e) {
			//
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("createCategory 오류");
		} finally {
			closeQuietly(pstmt, conn);
		}
	}


//--------------------------------------------------------------------------
	/**
	 * 카테고리조회결과를 Category 객체로 변환한다.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public Category convertToCategory(ResultSet resultSet) throws SQLException {
		//

		int cgId = resultSet.getInt("cgId");
		int cmId = resultSet.getInt("cmId");
		String cgName = resultSet.getString("cgName");
		
		Category category = new Category(cmId, cgId, cgName);
		category.setId(cgId);
		
		return category;
	}
}

