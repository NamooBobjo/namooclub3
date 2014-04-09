package com.namoo.ns1.web.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/clWithdraw.xhtml")
public class ClWithdrawController extends HttpServlet{

	private static final long serialVersionUID = -2090652797436121470L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		ClubService clservice = NamooClubServiceFactory.getInstance().getClubService();
	
		String clubId = req.getParameter("clId");
		String cmId = req.getParameter("cmId");
		String clubName=clservice.findClub(clubId).getName();
		
		req.setAttribute("clubId", clubId);
		req.setAttribute("cmId", cmId);
		req.setAttribute("clubName", clubName);
		
		HttpSession session = req.getSession();
		String loginID = (String) session.getAttribute("loginID");
		TownerService townerService=NamooClubServiceFactory.getInstance().getTownerService();
		String loginUser= townerService.findTowner(loginID).getName();
		req.setAttribute("loginUser", loginUser);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/withdraw.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	

}
