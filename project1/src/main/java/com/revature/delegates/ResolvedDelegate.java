package com.revature.delegates;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbDao;
import com.revature.daos.ReimbDaoImplement;
import com.revature.models.Reimbursement;

public class ResolvedDelegate {
	private ReimbDao reimbDao = new ReimbDaoImplement();

	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if (requestPath.length() == "/api/resolved".length()) {
			// search data to table
			String authToken = request.getHeader("Authorization");
			String userId = authToken.split(":")[0];
			List<Reimbursement> list = reimbDao.getEmployeeStatusByStatusId(2);
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
	
}
