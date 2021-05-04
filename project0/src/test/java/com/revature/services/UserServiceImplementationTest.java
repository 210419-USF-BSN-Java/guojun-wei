package com.revature.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.DAOs.OfferInfoDAOPostgres;
import com.revature.models.Item;
import com.revature.models.OfferInfo;
import com.revature.models.User;

public class UserServiceImplementationTest {

	@Test
	public void testAddEmployee() {
		UserServiceImplementation user = new UserServiceImplementation();
		User user1 = new User(200, "cannot", "findme", "turtle", "total", 2);
		assertEquals(user1, user.addEmployee(user1));
	}
	
	@Test
	public void testDeleteEmployee() {
		UserServiceImplementation user = new UserServiceImplementation();
		User user1 = new User(300, "Will", "Smith", "Wille", "799", 2);
		User tmp = user.addEmployee(user1);
		Boolean bool = user.deleteEmployee(tmp.getUserId());
		assertTrue(bool);
	}
	
	@Test
	public void testUpdateOfferTable() {
		OfferServiceImplementation offer = new OfferServiceImplementation();
		OfferInfo offer1 = new OfferInfo(500, 444, 444, 2000.0, 1, null);
		offer.add(offer1);
		assertTrue(offer.update(500, 444));
	}
	
	@Test
	public void testUpdatePayment() {
		OfferServiceImplementation offer = new OfferServiceImplementation();
		OfferInfo offer1 = new OfferInfo(600, 555, 555, 3000.0, 1, null);
		offer.add(offer1);
		assertTrue(offer.updatePayment(4, 555, 555));
	}
	
	@Test
	public void testUpdateItemTable() {
		ItemServiceImplementation item = new ItemServiceImplementation();
		Item item1 = new Item(200, "new one", true, null);
		item.addItem(item1);
		assertTrue(item.update(false, 200));
	}
	
	@Test
	public void testMakeOffer() {
		OfferServiceImplementation offer = new OfferServiceImplementation();
		OfferInfo offer1 = new OfferInfo(400, 333, 333, 1000.0, 1, null);
		assertEquals(offer1, offer.add(offer1));
	}
	
	@Test
	public void testVerifyUser() {
		UserServiceImplementation user = new UserServiceImplementation();
		User user1 = new User(13, "John", "Smith", "luffy", "456", 2);
		assertEquals(user1, user.verifyUser("luffy", "456"));
	}

	@Test 
	public void testRegistration() {
		UserServiceImplementation user = new UserServiceImplementation();
		User user1 = new User(16, "Zhihao", "Pan", "fish", "pwd", 1);
		assertEquals(user1, user.registration(user1));
	}
	
	@Test
	public void testEmployeeAddItem() {
		ItemServiceImplementation item = new ItemServiceImplementation();
		Item item1 = new Item(8, "Steven Gerrard", true, null);
		assertTrue(item.addItem(item1).isAvailability());
	}
	
	@Test
	public void testEmployeeRemoveItem() {
		ItemServiceImplementation item = new ItemServiceImplementation();
		Item item1 = new Item(100, "no one", true, null);
		Item tmp = item.addItem(item1);
		Boolean bool = item.deleteItem(tmp.getItemId());
		assertTrue(bool); 
	}
	
	@Test
	public void testViewAllOffers() {
		Boolean bool = false;
		OfferServiceImplementation offer = new OfferServiceImplementation();
		OfferInfo offer1 = new OfferInfo(401, 334, 334, 1001.0, 1, null);
		offer.add(offer1);
		List<OfferInfo> list = offer.getAll();
		for (OfferInfo of: list) {
			if (of.getItemId() == 334 && of.getUserId() == 334) {
				bool = true;
			}
		}
		assertTrue(bool);
	}
	
//	@Test
//	public void testViewOwnedItems1() {
//		ItemServiceImplementation itemServe = new ItemServiceImplementation();
//		Item item1 = new Item(101, "testOwnedItems1", true, null);
//		Item item2 = new Item(102, "testOwnedItems2", true, null);
//		itemServe.addItem(item1);
//		itemServe.addItem(item2);
//		OfferInfo offer1 = new OfferInfo(402, 335, 101, 2001.0, 2, null);
//		OfferInfo offer2 = new OfferInfo(403, 335, 102, 1001.0, 1, null);
//		OfferServiceImplementation offerServe = new OfferServiceImplementation();
//		offerServe.add(offer2);
//		offerServe.add(offer1);
//		List<Item> items = itemServe.getOwnedItems(2, 335);
//		//boolean bool = (101 == items.get(0).getItemId());
//		assertEquals("testOwnedItems1", items.get(items.size() - 1).getItemName());	
//	}
	
	@Test
	public void testGetAvailability() {
		Boolean bool = false;
		ItemServiceImplementation itemServe = new ItemServiceImplementation();
		Item item1 = new Item(105, "testGetAvailibility1", true, null);
		itemServe.addItem(item1);
		List<Item> items = itemServe.getAvailableItem(true);
		for (Item it: items) {
			if (it.getItemName().equals("testGetAvailibility1")) {
				bool = true;
			}
		}
		assertTrue(bool);
	}
	
	@Test
	public void testGetOfferByStatus() {
		Boolean bool = false;
		OfferServiceImplementation offerService = new OfferServiceImplementation();
		OfferInfo offer1 = new OfferInfo(null, 345, 345, 2000.0, 1, null);
		offerService.add(offer1);
		List<OfferInfo> offers = offerService.getOfferByStatus(1);
		for (OfferInfo of: offers) {
			if (of.getItemId() ==345 && of.getUserId() == 345) {
				bool = true;
			}
		}
		assertTrue(bool);
	}
}
