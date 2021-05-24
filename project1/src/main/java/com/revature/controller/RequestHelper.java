package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.ManagerDelegate;
import com.revature.delegates.ReimbDelegate;
import com.revature.delegates.ResolvedDelegate;
import com.revature.delegates.UserDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	private AuthDelegate authDelegate = new AuthDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	private UserDelegate userDelegate = new UserDelegate();
	private ReimbDelegate reimbDelegate = new ReimbDelegate();
	private ManagerDelegate managerDelegate = new ManagerDelegate();
	private ResolvedDelegate resolvedDelegate = new ResolvedDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// determine if this is a record based request
		String path = request.getServletPath();
		System.out.println("request.getServletPath() " + path);
		if(path.startsWith("/api/")) {
			// we will authenticate the token here
			if(!authDelegate.isAuthorized(request)) {
				response.sendError(401);
				return;
			}
			
			String record = path.substring(5);
			if(record.startsWith("users")) {
				userDelegate.getUsers(request, response);
			}else if(record.startsWith("reimb")) {
				reimbDelegate.getData(request, response);
			}else if(record.startsWith("manager")) {
				managerDelegate.getData(request, response);
			}else if(record.startsWith("resolved")) {
				resolvedDelegate.getData(request, response); 
			}else {
				response.sendError(404, "Request Record(s) Not Found");	
			}
			
		} else {
			viewDelegate.resolveView(request, response);
		}
	}
	
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getServletPath();
		System.out.println("request.getServletPath() path is " + path);
		if (path.startsWith("/api/")) {
			if(!authDelegate.isAuthorized(request)) {
				response.sendError(401);
				return;
			}
			path = path.replace("/api", "");
			System.out.println("path.replace path is " + path);
			switch(path) {
			case "/users":
				userDelegate.saveUser(request, response);
				break;
			case "/reimb":
				reimbDelegate.save(request, response);
				break;
			case "/manager":
				managerDelegate.action(request, response);
				break;
			default:
				response.sendError(405);
			}
		}else {
			switch(path) {
			case "/login":
				authDelegate.authenticate(request, response);
				break;
			default:
				response.sendError(405);
			}
			
		}
	}
}
