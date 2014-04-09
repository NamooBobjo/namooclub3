package com.namoo.ns1.web.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = -8502310861719199625L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		TownerService townerservice = NamooClubServiceFactory.getInstance().getTownerService();
		
		String userID = req.getParameter("userID");
		String userPS = req.getParameter("userPS");
		
		//로그인 된 경우
		if(townerservice.loginAsTowner(userID, userPS)){
			session.setAttribute("loginID", userID);
			resp.sendRedirect("cmList.xhtml");
			return;
		}
		
		//로그인 되지 않은 경우		
		else{
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/user/error.jsp");
			dispatcher.forward(req, resp);
			return;
		}
	}

	
}
