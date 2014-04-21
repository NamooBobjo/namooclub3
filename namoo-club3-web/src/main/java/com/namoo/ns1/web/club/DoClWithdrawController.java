package com.namoo.ns1.web.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/clwithdraw.do")
public class DoClWithdrawController extends HttpServlet {

	private static final long serialVersionUID = 2511040536502316369L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("loginID");
		ClubService clservice = NamooClubServiceFactory.getInstance().getClubService();
		int clubId = Integer.parseInt(req.getParameter("clubId"));
		int cmId = Integer.parseInt(req.getParameter("cmId"));
	
		System.out.println(clubId);
		
		clservice.withdrawalClub(clubId, email);
		resp.sendRedirect("clList.xhtml?cmId="+cmId);
	}

	
}
