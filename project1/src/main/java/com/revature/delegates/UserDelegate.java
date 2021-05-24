package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImplement;
import com.revature.models.User;

public class UserDelegate {

	private UserDao userDao = new UserDaoImplement();

	public void getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if (requestPath.length() == "/api/users".length()) {
			List<User> users = userDao.getAll();
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(users));
			}
		} else {
			String idStr = request.getServletPath().substring(11);
			System.out.println(idStr);
			User u = userDao.getByUserId(Integer.parseInt(idStr));
			if (u == null) {
				response.sendError(404, "No user with given ID");
			} else {
				try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(u));
				}
			}
		}
	}

	public void saveUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userId = request.getParameter("userId");
		userDao.updateUserInfo(username, password, firstName, lastName, 
				email, Integer.parseInt(userId));
	}

}
