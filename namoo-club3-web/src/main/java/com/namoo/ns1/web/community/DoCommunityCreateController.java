package com.namoo.ns1.web.community;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.domain.entity.Category;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.facade.TownerService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/cmcreate.do")
public class DoCommunityCreateController extends HttpServlet {

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
		TownerService twservice = NamooClubServiceFactory.getInstance()
				.getTownerService();
		CommunityService cmservice = NamooClubServiceFactory.getInstance()
				.getCommunityService();

		String loginID = (String) session.getAttribute("loginID");
		String email = twservice.findTowner(loginID).getEmail();

		String communityName = req.getParameter("cmName");
		String description = req.getParameter("description");

		String[] values = req.getParameterValues("category");
		List<Category> categories = new ArrayList<>();

		for (String value : values) {
			if (!value.equals("")) {
				Category category = new Category(value);
				categories.add(category);
			}
		}

		cmservice.registCommunity(communityName, description, email, categories);

		resp.sendRedirect("cmList.xhtml");
	}

}
