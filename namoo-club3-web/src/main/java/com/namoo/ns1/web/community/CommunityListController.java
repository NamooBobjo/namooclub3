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
		CommunityService service = NamooClubServiceFactory.getInstance().getCommunityService();
		TownerService townerservice = NamooClubServiceFactory.getInstance().getTownerService();
		HttpSession session = req.getSession();
		
		
		List<Community> belongCommunities = null;
		List<Community> communities = null;
		List<Community> managedCommunities = null;
			
		String loginID = (String) session.getAttribute("loginID");
		
		
		// 전체 커뮤니티 목록 조회
		if(!service.findAllCommunities().isEmpty()){
			communities = service.findAllCommunities();
		}		
		
		// 가입된 커뮤니티 목록 조회
		belongCommunities = service.findBelongCommunities(loginID);
		
		// 미가입 커뮤니티
		if(!belongCommunities.isEmpty()){
			for(Community community : belongCommunities){
				communities.remove(community);
			}	
		}		
		req.setAttribute("communities", communities);

		// 관리중인 커뮤니티
		managedCommunities = service.findManagedCommnities(loginID);
		req.setAttribute("managedCommunities", managedCommunities);

		// 관리하지 않는 가입중인 커뮤니티
		if(!managedCommunities.isEmpty()){
			if(!belongCommunities.isEmpty()){
				for(Community community : managedCommunities){
					belongCommunities.remove(community);
				}
			}
		}
		req.setAttribute("belongCommunities", belongCommunities);
		
		// 로그인 사용자 이름
		String loginUser = townerservice.findTowner(loginID).getName();
		req.setAttribute("loginUser", loginUser);
		
		// 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/home.jsp");
		dispatcher.forward(req, resp);
		return;
	}
}
