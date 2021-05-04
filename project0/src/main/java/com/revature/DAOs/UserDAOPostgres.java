package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.OfferInfo;
import com.revature.models.User;

import util.ConnectionUtil;

public class UserDAOPostgres implements UserDAO{

	@Override
	public User add(User u) {
		User user = null;
		String sql = "insert into user_info (user_first_name, user_last_name , user_name, user_pwd, user_type) values (?,?,?,?,?) returning user_id;";
//		String sql = "insert into departments (dept_name, monthly_budget) values (?,?);";
//		String[] keys = {"dept_id"};
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
//			PreparedStatement ps = con.prepareStatement(sql,keys);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getUserName());
			ps.setString(4, u.getPassword());
			ps.setInt(5, 1);
			
			ResultSet rs = ps.executeQuery();
//			ps.executeUpdate();
//			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				user = u;
				user.setUserId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User addEmployee(User u) {
		User user = null;
		String sql = "insert into user_info (user_first_name, user_last_name , user_name, user_pwd, user_type) values (?,?,?,?,?) returning user_id;";
//		String sql = "insert into departments (dept_name, monthly_budget) values (?,?);";
//		String[] keys = {"dept_id"};
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
//			PreparedStatement ps = con.prepareStatement(sql,keys);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getUserName());
			ps.setString(4, u.getPassword());
			ps.setInt(5, 2);
			
			ResultSet rs = ps.executeQuery();
//			ps.executeUpdate();
//			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				user = u;
				user.setUserId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUserName(String userName) {
		User user = null;
		String sql = "select * from user_info where user_name = ?;";

		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("user_id"), 
						rs.getString("user_first_name"), 
						rs.getString("user_last_name"),
						rs.getString("user_name"),
						rs.getString("user_pwd"),
						rs.getInt("user_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Boolean deleteEmployee(Integer employeeID) {
		Boolean result = false;
		String sql = "delete from user_info where user_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, employeeID);
			
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<OfferInfo> getOfferByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
