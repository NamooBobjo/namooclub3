package com.namoo.club.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.namoo.club.dao.SocialPersonDao;
import com.namoo.club.domain.entity.SocialPerson;

public class SocialPersonDaojdbc implements SocialPersonDao {

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
				String userId = resultSet.getString("email");
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");				
				SocialPerson person = new SocialPerson(userName, userId, password);
				persons.add(person);
			}}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			quiet(resultSet, pstmt, conn);
		}
		return persons;
	}

	@Override
	public SocialPerson readPerson(String userId) {
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
			
			while(resultSet.next()){
				String email = resultSet.getString("email");
				String name = resultSet.getString("userName");
				String password = resultSet.getString("password");
				
				person = new SocialPerson(name, email, password);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			quiet(resultSet, pstmt, conn);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			quiet(pstmt,conn);
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
		}finally{
			quiet(pstmt,conn);
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
			pstmt.executeQuery();
			
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
