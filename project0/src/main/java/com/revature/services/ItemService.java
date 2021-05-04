package com.revature.services;

import java.util.List;

import com.revature.models.Item;

public interface ItemService {

	List<Item> getAvailableItem(boolean avai);

	Item addItem(Item t);
	
	Boolean deleteItem(Integer id);

	List<Item> getAvailableOfferList(Integer id);
	
	Boolean update(Boolean bool, Integer itemID);
	
	List<Item> getOwnedItems(Integer status, Integer userID);
	
	Boolean updatePayment(Integer status, Integer userID, Integer itemID);
}
