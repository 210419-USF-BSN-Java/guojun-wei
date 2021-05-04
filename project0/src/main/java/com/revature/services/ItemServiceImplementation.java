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
	public Boolean deleteItem(Integer id) {
		Boolean res = itemD.delete(id);
		return res;
	}

	@Override
	public List<Item> getAvailableOfferList(Integer id){
		List<Item> items = itemD.getAvailableOfferList(id);
		return items;
	}

	@Override
	public Boolean update(Boolean bool, Integer itemID) {
		Boolean x = itemD.update(bool, itemID);
		return x;
	}

	@Override
	public List<Item> getOwnedItems(Integer status, Integer userID) {
		List<Item> items = itemD.getOwnedItems(status, userID);
		return items;
	}

	@Override
	public Boolean updatePayment(Integer status, Integer userID, Integer itemID) {
		Boolean updatePay = itemD.updatePayment(status, userID, itemID);
		return updatePay;
	}
}
