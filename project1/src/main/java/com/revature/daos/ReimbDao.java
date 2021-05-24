package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbDao {
	Reimbursement add(Reimbursement reimb) throws Exception;

	Boolean updateApprove(Integer reimbId, Integer userId) throws Exception;

	Boolean updateReject(Integer reimbId, Integer userId) throws Exception;

	List<Reimbursement> getEmployeeStatusById(Integer userId) throws Exception;
	
	List<Reimbursement> getEmployeeStatusByStatusId(Integer statusId) throws Exception;
}
