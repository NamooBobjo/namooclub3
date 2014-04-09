package com.namoo.ns1.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.web.session.LoginRequired;
import com.namoo.ns1.web.session.SessionManager;


public abstract class DefaultController extends HttpServlet {
	//
	private static final long serialVersionUID = 2999352839231443343L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		// 로그인 필수인 경우
		if (this.getClass().getAnnotation(LoginRequired.class) != null) {
			//
			if (!SessionManager.getInstance(req).isLogin()) {
				//
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/user/error.jsp");
				dispatcher.forward(req, resp);
			}
		}

		process(req, resp);
	}

	protected abstract void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}