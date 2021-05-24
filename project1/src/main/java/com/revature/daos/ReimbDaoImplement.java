package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;

import util.ConnectionUtil;

public class ReimbDaoImplement implements ReimbDao{

	@Override
	public Reimbursement add(Reimbursement reimb) throws Exception {
		Reimbursement addedReimb = null;
		String sql = "insert into ers_reimbursement \r\n"
				+ "(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id)\r\n"
				+ "values (?, ?, ?, ?, ?) returning reimb_id;";
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, reimb.getReimbAmount());
			ps.setString(2, reimb.getReimbDescription());
			//set date?
			ps.setInt(3, reimb.getReimbAuthor());
			ps.setInt(4, reimb.getReimbStatusId());
			ps.setInt(5, reimb.getReimbTypeId());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				addedReimb = reimb;
				addedReimb.setReimbId(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addedReimb;
	}
	
	
	@Override
	public Boolean updateApprove(Integer reimbId, Integer userId) throws Exception{
		Boolean result = false;
		String sql = "update ers_reimbursement \r\n"
				+ "set reimb_resolver = ?,\r\n"
				+ "	reimb_status_id = ?,\r\n"
				+ "	reimb_resolved = now()\r\n"
				+ "where reimb_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, 2);
			ps.setInt(3, reimbId);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Boolean updateReject(Integer reimbId, Integer userId) throws Exception{
		Boolean result = false;
		String sql = "update ers_reimbursement \r\n"
				+ "set reimb_resolver = ?,\r\n"
				+ "	reimb_status_id = ?,\r\n"
				+ "	reimb_resolved = now()\r\n"
				+ "where reimb_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, 3);
			ps.setInt(3, reimbId);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public List<Reimbursement> getEmployeeStatusById(Integer userId) throws Exception{
		List<Reimbursement> reimbLists = new ArrayList<>();
		String sql = "select r.*,s.reimb_status ,t.reimb_type ,\r\n" + 
				"u.user_first_name au_first_name,u.user_last_name au_last_name,\r\n" + 
				"rv.user_first_name rv_first_name,rv.user_last_name rv_last_name\r\n" + 
				"from ers_reimbursement r\r\n" + 
				"left join ers_reimbursement_status s on r.reimb_status_id  = s.reimb_status_id \r\n" + 
				"left join ers_reimbursement_type t on t.reimb_type_id  = r.reimb_type_id\r\n" + 
				"left join ers_users u on u.ers_users_id  = r.reimb_author \r\n" + 
				"left join ers_users rv on rv.ers_users_id  = r.reimb_resolver \r\n" + 
				"where reimb_author = ?;";

		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbLists.add(new Reimbursement(rs.getInt("reimb_id"), 
						rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getString("reimb_receipt"), //get bytea type
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_status"),
						rs.getString("reimb_type"),
						rs.getString("au_first_name")+" " +rs.getString("au_last_name"),
						rs.getString("rv_first_name")+" " +rs.getString("rv_last_name")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbLists;
	}


	@Override
	public List<Reimbursement> getEmployeeStatusByStatusId(Integer statusId) throws Exception {
		List<Reimbursement> reimbLists = new ArrayList<>();
		String sql = "select r.*,s.reimb_status ,t.reimb_type ,\r\n" + 
				"u.user_first_name au_first_name,u.user_last_name au_last_name,\r\n" + 
				"rv.user_first_name rv_first_name,rv.user_last_name rv_last_name\r\n" + 
				"from ers_reimbursement r\r\n" + 
				"left join ers_reimbursement_status s on r.reimb_status_id  = s.reimb_status_id \r\n" + 
				"left join ers_reimbursement_type t on t.reimb_type_id  = r.reimb_type_id\r\n" + 
				"left join ers_users u on u.ers_users_id  = r.reimb_author \r\n" + 
				"left join ers_users rv on rv.ers_users_id  = r.reimb_resolver \r\n" + 
				"where r.reimb_status_id = ?;";

		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statusId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbLists.add(new Reimbursement(rs.getInt("reimb_id"), 
						rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getString("reimb_receipt"), //get bytea type
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_status"),
						rs.getString("reimb_type"),
						rs.getString("au_first_name")+" " +rs.getString("au_last_name"),
						rs.getString("rv_first_name")+" " +rs.getString("rv_last_name")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbLists;
	}

}
