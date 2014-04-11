package com.namoo.ns1.web.community;

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


@WebServlet("/cmList.xhtml")
public class CommunityListController extends HttpServlet{

	private static final long serialVersionUID = -4847940940258971662L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		TownerService townerservice = NamooClubServiceFactory.getInstance().getTownerService();
		HttpSession session = req.getSession();
		
		// 
		String loginEmail = (String) session.getAttribute("loginID");

		List<Community> unjoinedCommunities = service.findAllUnjoinedCommunities(loginEmail);
		req.setAttribute("unjoinedCommunities", unjoinedCommunities);
		
		List<Community> joinedCommunities = service.findJoinedCommunities(loginEmail);
		req.setAttribute("joinedCommunities", joinedCommunities);
		
		List<Community> managedCommunities = service.findManagedCommnities(loginEmail);
		req.setAttribute("managedCommunities", managedCommunities);
		
		// 로그인 사용자 이름
		String loginUser = townerservice.findTowner(loginEmail).getName();
		req.setAttribute("loginUser", loginUser);
		
		// 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/home.jsp");
		dispatcher.forward(req, resp);
		return;
	}
}
