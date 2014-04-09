package com.namoo.ns1.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/regist.do")
public class RegistUserController extends HttpServlet {

	private static final long serialVersionUID = 5293638727757717469L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TownerService townerservice = NamooClubServiceFactory.getInstance().getTownerService();
		
		String name = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if(name.equals("") || email.equals("")|| password.equals("")){
			resp.sendRedirect("view/user/join.xhtml");
			return;
		}
		
		
		
		townerservice.registTowner(name, email, password);
		
		resp.sendRedirect("main.xhtml");
		
	}

}
