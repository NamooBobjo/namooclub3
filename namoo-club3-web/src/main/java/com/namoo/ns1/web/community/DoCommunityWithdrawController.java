package com.namoo.ns1.web.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/cmwithdraw.do")
public class DoCommunityWithdrawController extends HttpServlet{

	private static final long serialVersionUID = -8421419443329457788L;

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
		HttpSession session = req.getSession();
		CommunityService cmservice = NamooClubServiceFactory.getInstance().getCommunityService();
		
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		String email = (String)session.getAttribute("loginID");	
		
		cmservice.withdrawalCommunity(cmId, email);
		
		resp.sendRedirect("cmList.xhtml");
		
	}
}
