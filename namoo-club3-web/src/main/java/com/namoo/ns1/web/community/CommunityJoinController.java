package com.namoo.ns1.web.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.domain.entity.Community;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;


@WebServlet("/cmjoin.xhtml")
public class CommunityJoinController extends HttpServlet {

	private static final long serialVersionUID = 6830767991447143387L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		CommunityService cmservice = NamooClubServiceFactory.getInstance().getCommunityService();
		String cmId = req.getParameter("cmId");		
		Community community = cmservice.findCommunity(cmId);
		
		
		String cmName = community.getName();
	
		req.setAttribute("cmId", cmId);		
		req.setAttribute("cmName", cmName);
		
		HttpSession session = req.getSession();
		String loginID = (String) session.getAttribute("loginID");
		TownerService townerService=NamooClubServiceFactory.getInstance().getTownerService();
		String loginUser= townerService.findTowner(loginID).getName();
		req.setAttribute("loginUser", loginUser);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/community/join.jsp");
		dispatcher.forward(req, resp);
		
	}

	
}
