package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbDao;
import com.revature.daos.ReimbDaoImplement;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImplement;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ManagerDelegate {

	private ReimbDao reimbDao = new ReimbDaoImplement();

	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if (requestPath.length() == "/api/manager".length()) {
			// search data to table
			List<Reimbursement> list = reimbDao.getEmployeeStatusByStatusId(Reimbursement.PENDING);
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(list));
			}
		} else {
			// "/api/reimb/2"
			int start = requestPath.lastIndexOf("/");
			String idStr = requestPath.substring(start+1,requestPath.length());
			System.out.println(idStr);
			/* TOTO get data by PK id;
			 * if (u == null) { response.sendError(404, "No user with given ID"); } else {
			 * try (PrintWriter pw = response.getWriter()) { pw.write(new
			 * ObjectMapper().writeValueAsString(u)); } }
			 */
		}
	}

	public void action(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		String type = request.getParameter("type");
		Integer reimbId = Integer.valueOf(request.getParameter("reimbId"));
		String authToken = request.getHeader("Authorization");
		Integer userId = Integer.valueOf(authToken.split(":")[0]);
		if ("approve".equals(type)) {
			reimbDao.updateApprove(reimbId, userId);
		}
		if ("reject".equals(type)) {
			reimbDao.updateReject(reimbId, userId);
		}
	}

}
