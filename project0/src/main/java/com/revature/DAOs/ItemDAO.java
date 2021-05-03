package com.revature.DAOs;

import java.util.List;

import com.revature.models.Item;

public interface ItemDAO {

	List<Item> getAvailability(boolean available);

	Item add(Item it);
	
	Integer delete(Integer id);

	List<Item> getAvailableOfferList(Integer id);
	
}
