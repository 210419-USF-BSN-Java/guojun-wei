package com.revature.services;

import java.util.List;

import com.revature.models.Item;

public interface ItemService {

	List<Item> getAvailableItem(boolean avai);

	Item addItem(Item t);
	
	Integer deleteItem(Integer id);

	List<Item> getAvailableOfferList(Integer id);
}
