package com.revature.DAOs;

import com.revature.models.User;

public interface UserDAO extends GenericDAO<User>{

	Integer deleteEmployee(Integer employeeID);
	
}
