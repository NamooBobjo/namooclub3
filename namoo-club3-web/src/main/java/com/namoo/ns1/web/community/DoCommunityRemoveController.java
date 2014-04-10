package com.namoo.ns1.web.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/remove.do")
public class DoCommunityRemoveController extends HttpServlet {

	private static final long serialVersionUID = -5204297478981537446L;

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
		
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		service.removeCommunity(cmId);
		
		resp.sendRedirect("cmList.xhtml");
	}
}
