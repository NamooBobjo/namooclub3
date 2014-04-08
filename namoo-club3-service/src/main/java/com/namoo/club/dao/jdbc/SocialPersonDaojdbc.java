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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SocialPerson readPerson(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createPerson(SocialPerson person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePerson(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePerson(SocialPerson person) {
		// TODO Auto-generated method stub

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
