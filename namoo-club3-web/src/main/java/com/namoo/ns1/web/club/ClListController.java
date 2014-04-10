package com.namoo.ns1.web.club;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.domain.entity.Club;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/clList.xhtml")
public class ClListController extends HttpServlet{

	private static final long serialVersionUID = 3879339579100526413L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/home.jsp");
		dispatcher.forward(req, resp);
		CommunityService cmservice = NamooClubServiceFactory.getInstance().getCommunityService();
		ClubService clservice = NamooClubServiceFactory.getInstance().getClubService();
		TownerService townerService=NamooClubServiceFactory.getInstance().getTownerService();
		HttpSession session = req.getSession();
		
		List<Club> clubs = null;
		List<Club> belongclubs = null;
		List<Club> managedclubs = null;
		
		String loginID = (String) session.getAttribute("loginID");
		
		if(!clservice.findAllClubs().isEmpty()) {
			clubs= clservice.findAllClubs();
		}
		
	
		
		
		String cmId = req.getParameter("cmId");
		//Community community = cmservice.findCommunity(cmId);
		
		String email = (String)session.getAttribute("loginID");
		//String cmname = community.getName();
		
		
		
		
		String loginUser= townerService.findTowner(loginID).getName();
		
		
		req.setAttribute("managedclubs", managedclubs);
		req.setAttribute("clubs", clubs);
		req.setAttribute("loginUser", loginUser);
		//req.setAttribute("cmName", cmname);
		req.setAttribute("cmId", cmId);		
		req.setAttribute("belongclubs", belongclubs);
		req.setAttribute("belongclubs", belongclubs);
		
		dispatcher.forward(req, resp);
		return;
	}
	//clubs = filter(clubs, belongclubs);
	//belongclubs = filter(belongclubs, managedclubs);
	

	/*
	private List<Club> filter(List<Club> all, List<Club> filters) {
		//
		List<Club> removed = new ArrayList<>();
		for (Club filter : filters) {
			for (Club club : all) {
				if (filter.getId().equals(club.getId())) {
					removed.add(club);
					break;
				}
			}
		}
		if (!removed.isEmpty()) {
			all.removeAll(removed);
		}
		return all;
	}
	*/
}
