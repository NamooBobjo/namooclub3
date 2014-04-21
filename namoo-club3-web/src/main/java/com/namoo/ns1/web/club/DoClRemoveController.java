package com.namoo.ns1.web.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/clremove.do")
public class DoClRemoveController extends HttpServlet {

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
		
		int clId = Integer.parseInt(req.getParameter("clId"));
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		
		ClubService service = NamooClubServiceFactory.getInstance().getClubService();
		service.removeClub(clId);
		
		resp.sendRedirect("clList.xhtml?cmId="+cmId);
	}
}
