package com.revature.DAOs;

import java.util.List;

import com.revature.models.Item;

public interface ItemDAO {

	List<Item> getAvailability(boolean available);

	Item add(Item it);
	
	Boolean delete(Integer id);

	List<Item> getAvailableOfferList(Integer id);

	Boolean update(Boolean bool, Integer itemID);
	
	List<Item> getOwnedItems(Integer status, Integer userID);
	
	Boolean updatePayment(Integer status, Integer userID, Integer itemID);
}
