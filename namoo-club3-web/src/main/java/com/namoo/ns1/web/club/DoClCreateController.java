package com.namoo.ns1.web.club;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.club.domain.entity.Club;
import com.namoo.club.domain.entity.Community;
import com.namoo.club.service.facade.ClubService;
import com.namoo.club.service.facade.CommunityService;
import com.namoo.club.service.factory.NamooClubServiceFactory;

@WebServlet("/clCreate.do")
public class DoClCreateController extends HttpServlet{

	private static final long serialVersionUID = -1246207217819762829L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		ClubService clservice = NamooClubServiceFactory.getInstance().getClubService();
		CommunityService cmservice = NamooClubServiceFactory.getInstance().getCommunityService();
		HttpSession session = req.getSession();
		
		String clubName = req.getParameter("clName");
		String description = req.getParameter("content");
		String email = (String)session.getAttribute("loginID");
		int cmId = Integer.parseInt(req.getParameter("cmId"));
		String category = req.getParameter("category");
		
		Community community =	cmservice.findCommunity(cmId);
		List<Club> clubs = community.getClubs();
		System.out.println(community.getClubs());
		
		for(Club club : clubs){
			System.out.println(category);
			System.out.println("22");
			System.out.println("클럽이름 " +club.getName());
			System.out.println("카테고리 : " +club.getCategory());
			if(category.equals(club.getCategory())){
				resp.sendRedirect("create.xhtml?cmId="+cmId);
				return;
			}
		}
		System.out.println("1");
		
		
		clservice.registClub(cmId, category, clubName, description, email);
		resp.sendRedirect("clList.xhtml?cmId="+cmId);
	}

	
}
