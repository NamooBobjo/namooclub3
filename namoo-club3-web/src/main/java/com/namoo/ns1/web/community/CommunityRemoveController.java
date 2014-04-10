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


@WebServlet("/cmRemove.xhtml")
public class CommunityRemoveController extends HttpServlet {

	private static final long serialVersionUID = -5204297478981537446L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		
		CommunityService cmservice = NamooClubServiceFactory.getInstance().getCommunityService();
		Community community = cmservice.findCommunity(cmId);
		String cmName = community.getName();
		
		req.setAttribute("cmName", cmName);
		req.setAttribute("cmId", cmId);
		
		HttpSession session = req.getSession();
		String loginID = (String) session.getAttribute("loginID");
		TownerService townerService=NamooClubServiceFactory.getInstance().getTownerService();
		String loginUser= townerService.findTowner(loginID).getName();
		req.setAttribute("loginUser", loginUser);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/community/remove.jsp");
		dispatcher.forward(req, resp);
	}

}
