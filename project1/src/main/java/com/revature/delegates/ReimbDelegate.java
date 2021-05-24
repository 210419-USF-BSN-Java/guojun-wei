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

public class ReimbDelegate {

	private ReimbDao reimbDao = new ReimbDaoImplement();

	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if (requestPath.length() == "/api/reimb".length()) {
			// search data to table
			String authToken = request.getHeader("Authorization");
			String userId = authToken.split(":")[0];
			List<Reimbursement> list = reimbDao.getEmployeeStatusById(Integer.parseInt(userId));
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

	public void save(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Double reimbAmount = Double.valueOf(request.getParameter("reimbAmount"));
		String reimbDescription = request.getParameter("reimbDescription");
		Integer reimbTypeId = Integer.valueOf(request.getParameter("reimbTypeId"));
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		
		Reimbursement reimb = new Reimbursement();
		reimb.setReimbAmount(reimbAmount);
		reimb.setReimbDescription(reimbDescription);
		reimb.setReimbTypeId(reimbTypeId);
		reimb.setReimbAuthor(userId);
		reimb.setReimbStatusId(Reimbursement.PENDING);
		reimbDao.add(reimb);
	}

}
