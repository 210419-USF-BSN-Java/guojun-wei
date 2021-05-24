package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class FrontController extends DefaultServlet{
	
	private RequestHelper requestHelper = new RequestHelper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println("servlet path is " + path);
		if (path.startsWith("/static/")) {
			super.doGet(request, response);
		} else {
			try {
				requestHelper.processGet(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			} //get data from database, js/java
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			requestHelper.processPost(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
}
