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
	public void registration(User user) {
		User newRegister = ud.add(user);
	}

	@Override
	public void addEmployee(User user) {
		User newEmployee = ud.addEmployee(user);
	}

	public Integer deleteEmployee(Integer employeeID) {
		Integer num = ud.deleteEmployee(employeeID);
		return num;
	}
}
