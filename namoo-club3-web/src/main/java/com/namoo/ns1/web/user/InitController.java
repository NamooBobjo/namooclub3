package com.namoo.ns1.web.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main.xhtml")
public class InitController extends HttpServlet{

	private static final long serialVersionUID = 8897210701561934660L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("loginId")!=null){
			resp.sendRedirect("cmList.xhtml");
			return;
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/user/login.jsp");
		dispatcher.forward(req, resp);
		
	}

}
