package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;

import util.ConnectionUtil;

public class UserDaoImplement implements UserDao{

	@Override
	public User getUserInfo(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUserInfo(String userName, String password, String firstName, String lastName, String email, Integer userId) throws Exception {
		Boolean result = false;
		String sql = "update ers_users urs \r\n"
				+ "set ers_username = ?,\r\n"
				+ "	ers_password = ?,\r\n"
				+ "	user_first_name = ?,\r\n"
				+ "	user_last_name = ?,\r\n"
				+ "	user_email = ?\r\n"
				+ "where ers_users_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, email);
			ps.setInt(6, userId);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//done!
	@Override
	public List<User> getAllEmployee(Integer userRoleId) throws Exception {
		User employee = null;
		List<User> employees = new ArrayList<>();
		String sql = "select * from ers_users \r\n"
				+ "join ers_user_roles \r\n"
				+ "on ers_users.user_role_id = ers_user_roles.ers_user_role_id \r\n"
				+ "where ers_user_roles.user_role = 'employee';";
		
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employee = new User(rs.getInt("ers_users_id"), 
						rs.getString("ers_username"), 
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws Exception {
		User user = null;
		String sql = "select * from ers_users where ers_username = ? and ers_password = ?;";	
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("ers_users_id"), 
						rs.getString("ers_username"), 
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
//		if (user.getUsername() != null && user.getUsername().equals(username)) {
//			if (user.getPassword() != null && user.getPassword().equals(password)) {
//				return user;
//			}
//		}
//		
//		return null;
	}

	//done!
	@Override
	public User getByUserId(int id) throws Exception {
		User user = null;
		String sql = "select * from ers_users where ers_users_id = ?;";
		
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("ers_users_id"), 
						rs.getString("ers_username"), 
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAll() throws Exception {
		User user = null;
		List<User> users = new ArrayList<>();
		String sql = "select * from ers_users;";
		
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("ers_users_id"), 
						rs.getString("ers_username"), 
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

}
