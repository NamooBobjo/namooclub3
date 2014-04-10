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
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("loginID");
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		
		CommunityService cmservice = NamooClubServiceFactory.getInstance().getCommunityService();
		Community community = cmservice.findCommunity(cmId);
		ClubService clservice = NamooClubServiceFactory.getInstance().getClubService();
		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();
		
		List<Club> clubs = new ArrayList<>(cmservice.findCommunity(cmId).getClubs());
		List<Club> belongclubs = new ArrayList<>(clservice.findBelongClub(cmId, email));
		List<Club> managedclubs = new ArrayList<>(clservice.findManagedClub(cmId, email));
		
		clubs = filter(clubs, belongclubs);
		belongclubs = filter(belongclubs, managedclubs);
		
		String cmname = community.getName();
		
		req.setAttribute("managedclubs", managedclubs);
		req.setAttribute("clubs", clubs);
		req.setAttribute("cmName", cmname);
		req.setAttribute("cmId", cmId);		
		req.setAttribute("belongclubs", belongclubs);
		req.setAttribute("belongclubs", belongclubs);
		
		String loginID = (String) session.getAttribute("loginID");
		String loginUser = townerService.findTowner(loginID).getName();
		req.setAttribute("loginUser", loginUser);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/home.jsp");
		dispatcher.forward(req, resp);
		return;
	}

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
}
