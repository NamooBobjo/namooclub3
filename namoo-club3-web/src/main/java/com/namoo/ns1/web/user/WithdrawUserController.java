package com.namoo.ns1.web.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.domain.entity.SocialPerson;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;


@WebServlet("/user/withdraw.xhtml")
public class WithdrawUserController extends HttpServlet{

	private static final long serialVersionUID = -8367245656906918663L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	doPost(req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TownerService townerService= NamooClubServiceFactory.getInstance().getTownerService();
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("loginID");
		
		SocialPerson person= townerService.findTowner(email);
		String userName =person.getName();
		
		req.setAttribute("userName", userName);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/withdraw.jsp");
		dispatcher.forward(req, resp);
		
	}

	
}
