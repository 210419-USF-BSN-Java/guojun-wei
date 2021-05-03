package com.revature.services;

import java.util.List;

import com.revature.DAOs.ItemDAO;
import com.revature.DAOs.ItemDAOPostgres;
import com.revature.models.Item;

public class ItemServiceImplementation implements ItemService{
	private ItemDAO itemD;
	
	public ItemServiceImplementation() {
		itemD = new ItemDAOPostgres();
	}
	
	@Override
	public List<Item> getAvailableItem(boolean avai) {
		List<Item> items = itemD.getAvailability(avai);
		return items;
	}
	
	@Override
	public Item addItem(Item t) {
		Item item = itemD.add(t);
		return item;
	}

	@Override
	public Integer deleteItem(Integer id) {
		Integer num = itemD.delete(id);
		return num;
	}

	@Override
	public List<Item> getAvailableOfferList(Integer id){
		List<Item> items = itemD.getAvailableOfferList(id);
		return items;
	}
}
