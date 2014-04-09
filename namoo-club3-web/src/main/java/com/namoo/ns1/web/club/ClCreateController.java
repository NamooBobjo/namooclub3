package com.namoo.ns1.web.club;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/create.xhtml")
public class ClCreateController extends HttpServlet{

	private static final long serialVersionUID = 4461129352411687538L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String cmId = req.getParameter("cmId");		
		req.setAttribute("cmId", cmId);		
		CommunityService cmservice = NamooClubServiceFactory.getInstance().getCommunityService();
		
		Community community = cmservice.findCommunity(cmId);
		List<String> category = community.getCategory();		
		
		HttpSession session = req.getSession();
		String loginID = (String) session.getAttribute("loginID");
		TownerService townerService=NamooClubServiceFactory.getInstance().getTownerService();
		String loginUser= townerService.findTowner(loginID).getName();
		req.setAttribute("loginUser", loginUser);
		req.setAttribute("category", category);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/create.jsp");
		dispatcher.forward(req, resp);
	}
	
	
}
