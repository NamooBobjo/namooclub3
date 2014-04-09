package com.namoo.ns1.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view/*")

public class JspViewResolverController extends HttpServlet{

	private static final long serialVersionUID = -5731486433378278445L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 
		String prefix = req.getContextPath() + req.getServletPath();
		String fullUrl = req.getRequestURI();

		String realPath = fullUrl.replace(prefix, "/WEB-INF/views").replace(".xhtml", ".jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher(realPath);
		dispatcher.forward(req, resp);
	}
	
	
}
