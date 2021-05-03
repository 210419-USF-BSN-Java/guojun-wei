package com.revature.DAOs;

import java.util.List;

import com.revature.models.OfferInfo;
import com.revature.models.User;

public interface GenericDAO<T> {
	// CRUD operations for all Daos
	public T add(T t);
	
	public T getById(Integer id);

	public List<T> getAll();

	public Integer update(T t);

	public Integer delete(T t);
	
	public T getByUserName(String userName);

	public T addEmployee(T t);

	List<OfferInfo> getOfferByStatus(Integer status);
}
