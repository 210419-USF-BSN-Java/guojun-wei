package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.DAOs.UserDAOPostgres;
import com.revature.models.User;

public class UserServiceImplementation implements UserService{
	private UserDAO ud; 
	
	public UserServiceImplementation() {
		ud = new UserDAOPostgres();
	}
	
	@Override
	public User verifyUser(String userName, String password) {
		User user = ud.getByUserName(userName);
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}
	
	@Override
	public User registration(User user) {
		User newRegister = ud.add(user);
		return newRegister;
	}

	@Override
	public User addEmployee(User user) {
		User newEmployee = ud.addEmployee(user);
		return newEmployee;
	}

	public Boolean deleteEmployee(Integer employeeID) {
		Boolean result = ud.deleteEmployee(employeeID);
		return result;
	}
}
