package com.revature.services;

import com.revature.models.User;

public interface UserService {

	void registration(User user);

	User verifyUser(String userName, String password);
	
	void addEmployee(User user);
	
}
