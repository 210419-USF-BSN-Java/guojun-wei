package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/login":
			request.getRequestDispatcher("/static/Login.html").forward(request, response);
			break;
		case "/home":
			request.getRequestDispatcher("/static/EmployeeHome.html").forward(request, response);
			break;
		case "/EmplyPersonalInfo":
			request.getRequestDispatcher("/static/EmplyPersonalInfo.html").forward(request, response);
			break;
		case "/EmplyEditPersonalInfo":
			request.getRequestDispatcher("/static/EmplyEditPersonalInfo.html").forward(request, response);
			break;
		case "/EmplyRequest":
			request.getRequestDispatcher("/static/EmplyRequest.html").forward(request, response);
			break;
		case "/EmplyViewRequest":
			request.getRequestDispatcher("/static/EmplyViewRequest.html").forward(request, response);
			break;
		case "/ManagerHome":
			request.getRequestDispatcher("/static/ManagerHome.html").forward(request, response);
			break;
		case "/ManagerViewResolvedRequest":
			request.getRequestDispatcher("/static/ManagerViewResolvedRequest.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	}
}

