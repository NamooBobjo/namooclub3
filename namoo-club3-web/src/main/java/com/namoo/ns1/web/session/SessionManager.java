package com.namoo.ns1.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

public class SessionManager {
	//
	private static final String LOGIN_TOWNER = "loginTowner";

	private HttpSession session;

	private SessionManager(HttpServletRequest req) {
		//
		this.session = req.getSession();
	}

	//--------------------------------------------------------------------------

	public static SessionManager getInstance(HttpServletRequest req) {
		//
		return new SessionManager(req);
	}

	public boolean isLogin() {
		//
		return (session.getAttribute(LOGIN_TOWNER) != null) ? true : false;
	}

	public boolean login(String email, String password) {
		//
		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();
		if (townerService.loginAsTowner(email, password)) {
			SocialPerson loginTowner = townerService.findTowner(email);
			session.setAttribute(LOGIN_TOWNER, loginTowner);
			return true;
		} else {
			session.invalidate();
			return false;
		}
	}

	public void logout() {
		//
		session.invalidate();
	}

	public String getLoginEmail() {
		// 
		if (isLogin()) {
			SocialPerson loginTowner = (SocialPerson) session.getAttribute(LOGIN_TOWNER);
			return loginTowner.getEmail();
		}
		return null;
	}
}