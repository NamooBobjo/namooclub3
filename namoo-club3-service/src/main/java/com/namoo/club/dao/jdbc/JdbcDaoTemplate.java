package com.namoo.club.dao.jdbc;

public class JdbcDaoTemplate {
	//
	protected void closeQuietly(AutoCloseable ... targets) {
		//
		for (AutoCloseable target : targets) {
			if (target != null) {
				try { target.close(); } catch (Exception e) { }
			}
		}
	}
}
