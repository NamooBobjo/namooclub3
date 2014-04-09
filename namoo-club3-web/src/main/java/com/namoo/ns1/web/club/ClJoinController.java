package com.namoo.ns1.web.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/clJoin.xhtml")
public class ClJoinController extends HttpServlet {

	private static final long serialVersionUID = -1562159333306774813L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String cmId = req.getParameter("cmId");
		String clId = req.getParameter("clId");
		
		req.setAttribute("cmId", cmId);
		req.setAttribute("clId", clId);
		
		ClubService clservice = NamooClubServiceFactory.getInstance().getClubService();
		String clubName = clservice.findClub(clId).getName();
		
		req.setAttribute("clubName", clubName);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/join.jsp");
		dispatcher.forward(req, resp);
		
	}

}
