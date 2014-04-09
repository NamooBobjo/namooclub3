package com.namoo.ns1.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.service.logic.exception.NamooExceptionFactory;

@WebServlet("/user/withdraw.do")
public class DoWithdrawUserController extends HttpServlet {

	private static final long serialVersionUID = -8367245656906918663L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		TownerService townerService= NamooClubServiceFactory.getInstance().getTownerService();
		
		String email = (String) session.getAttribute("loginID");
		String password = req.getParameter("password");
		if (!townerService.findTowner(email).getPassword().equals(password)) {
			throw NamooExceptionFactory.createRuntime("패스워드를 확인해 주세요.");
		}
		
		townerService.removeTowner(email);//삭제
		
		resp.sendRedirect("../main.xhtml");		
		
	}

}
