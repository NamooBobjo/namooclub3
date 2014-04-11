package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

public class SocialPersonDaojdbc extends JdbcDaoTemplate implements SocialPersonDao {

	@Override
	//전체 목록 조회
	public List<SocialPerson> readAllPerson() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<SocialPerson> persons = new ArrayList<SocialPerson>();
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, userName, password FROM socialperson";
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				persons.add(convertSocialPerson(resultSet));
			}}
		catch (SQLException e) {
			//
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("멤버목록조회 중 오류가 발생하였습니다.");
		} finally{
			closeQuietly(resultSet, pstmt, conn);
		}
		return persons;
	}

	@Override
	public SocialPerson readPerson(String userId) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		SocialPerson person = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT email, userName, password FROM socialperson WHERE email = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next()){
				person = convertSocialPerson(resultSet);
			}
		
		} catch (SQLException e) {
			//
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("멤버조회 중 오류가 발생하였습니다.");
		}finally{
			closeQuietly(resultSet, pstmt, conn);
		}		
		
		return person;
	}

	@Override
	public void createPerson(SocialPerson person) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "INSERT INTO socialperson(email, userName, password) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, person.getEmail());
			pstmt.setString(2, person.getName());
			pstmt.setString(3, person.getPassword());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// 
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("멤버가입 중 오류가 발생하였습니다.");
		}finally{
			closeQuietly(pstmt,conn);
		}
		
	}

	@Override
	public void deletePerson(String userId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "DELETE FROM socialperson WHERE email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("멤버삭제 중 오류가 발생하였습니다.");
		}finally{
			closeQuietly(pstmt,conn);
		}
	}

	@Override
	public void updatePerson(SocialPerson person) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbConnection.getConnection();
			String sql = "UPDATE socialperson SET  userName=?, password=? WHERE email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getPassword());
			pstmt.setString(3, person.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooExceptionFactory.createRuntime("정보수정 중 오류가 발생하였습니다.");
		}finally{
			closeQuietly(pstmt, conn);
		}
	}
//--------------------------------------------------------------------------
	/**
	 * 멤버조회결과를 SocialPerson 객체로 변환한다.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public SocialPerson convertSocialPerson(ResultSet resultSet) throws SQLException {
		//
		String email = resultSet.getString("email");
		String name = resultSet.getString("userName");
		String password = resultSet.getString("password");				
		
		SocialPerson person = new SocialPerson(name, email, password);
		
		return person;
	}
}



	
