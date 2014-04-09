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
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/home.jsp");
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		TownerService townerservice = NamooClubServiceFactory.getInstance().getTownerService();
		HttpSession session = req.getSession();
		
		
		List<Community> belongCommunities = null;
		List<Community> communities = null;
		List<Community> managedCommunities = null;
			
		String loginID = (String) session.getAttribute("loginID");
		if(!service.findBelongCommunities(loginID).isEmpty() &&!service.findAllCommunities().isEmpty() && !service.findManagedCommnities(loginID).isEmpty() ){
			belongCommunities = service.findBelongCommunities(loginID);	
			communities = service.findAllCommunities();
			managedCommunities = service.findManagedCommnities(loginID);
			
			for(Community community : belongCommunities){
				communities.remove(community);
			}			
			
			for(Community community : managedCommunities){
				belongCommunities.remove(community);
			}

		}
		
		
		String loginUser = townerservice.findTowner(loginID).getName();
		
		req.setAttribute("loginUser", loginUser);
		req.setAttribute("communities", communities);
		req.setAttribute("managedCommunities", managedCommunities);
		req.setAttribute("belongCommunities", belongCommunities);
		
		dispatcher.forward(req, resp);
		return;
	}
}
