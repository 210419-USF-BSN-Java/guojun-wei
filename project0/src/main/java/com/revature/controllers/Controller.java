package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import com.revature.DAOs.OfferInfoDAOPostgres;
import com.revature.models.Item;
import com.revature.models.OfferInfo;
import com.revature.models.User;
import com.revature.services.ItemServiceImplementation;
import com.revature.services.OfferServiceImplementation;
import com.revature.services.UserServiceImplementation;

public class Controller {

	private static UserServiceImplementation userService = new UserServiceImplementation();
	
	private static User user;
	
	private static ItemServiceImplementation itemService = new ItemServiceImplementation();
	
	private static OfferServiceImplementation offerService = new OfferServiceImplementation();
	
	private static Item item;
	
	private static List<Item> items;
	
	//Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		menu(sc);
	}
	
	private static void menu(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the FIFA soccer player card shopping system, please make options!");
		System.out.println("press 1 for login");
		System.out.println("No account yet? No problem! press 2 for customer registration!");
		String userInput = sc.nextLine();
		userLogin(userInput, sc);
	}

	public static void userLogin(String userInput, Scanner sc) {
		if (userInput.equals("1")) {
			//verify customer login
			userInfo(sc);
		} else if (userInput.equals("2")) {
			//register customer login
			userRegistration(sc);
		} else {
			System.out.println("illegal input, please reentry!");
			System.out.println();
			menu(sc);
		}
	}
	
	public static void userInfo(Scanner sc) {
		System.out.println("username: ");
		String userName = sc.nextLine();
		System.out.println("password: ");
		String password = sc.nextLine();
		user = userService.verifyUser(userName, password);
//			System.out.println("LOGIN SUSCESSFULLY!! YAY");
		if (user != null){	
			if (user.getType() == 1) {
				customerUI(sc);
			} else if (user.getType() == 2) {
				employeeUI(sc);
			} else {
				managerUI(sc);
			}
		}else {
			System.out.println("illegal username/password, please reentry!");
			System.out.println();
			menu(sc);
		}
		//return User;
	}
	
	public static void userRegistration(Scanner sc) {
		System.out.println("username: ");
		String userName = sc.nextLine();
		System.out.println("password: ");
		String password = sc.nextLine();
		System.out.println("first name: ");
		String firstName = sc.nextLine();
		System.out.println("last name: ");
		String lastName = sc.nextLine();
		user = new User(null, firstName, lastName, userName,password, 1);
		userService.registration(user);
	}
	
	public static void customerUI(Scanner sc) {
		System.out.println("Welcome back! " + user.getUserName() + " , what would you like to do?");
		System.out.println();
		System.out.println("press 1 to view available items");
		System.out.println("press 2 to make an offer for an item");
		System.out.println("press 3 to view the item that I own");
		System.out.println("press 4 to make a payment");
		String userInput = sc.nextLine();
		customerOp(userInput, sc);
		
	}

	private static void customerOp(String userInput, Scanner sc) {
		if (userInput.equals("1")) {
			viewItems();
		} else if (userInput.equals("2")) {
			makeOffer(sc);
		} else if (userInput.equals("3")) {
			
		} else if (userInput.equals("4")) {
			
		} else {
			
		}
		
	}

	
	private static void makeOffer(Scanner sc) {
		System.out.println("These stars are available!");
		//see which item is available
		items = itemService.getAvailableOfferList(user.getUserId());
		for (Item it:items) {
			System.out.println("item id: " 
					+ it.getItemId()
					+ " item name: "
					+ it.getItemName());
		}
		//make offer to one player
		System.out.println("Please enter item id and price for making an offer ");
		System.out.println("item id: ");
		Integer itemID = Integer.parseInt(sc.nextLine());
		System.out.println("price: ");
		Double price = Double.parseDouble(sc.nextLine());
		OfferInfo offer = new OfferInfo(null, user.getUserId(), itemID, price, 1, null);
		offerService.add(offer);
		
	}

	private static void viewItems() {
		items = itemService.getAvailableItem(true);
		System.out.println(items);
	}

	public static void employeeUI(Scanner sc) {
		System.out.println("Welcome back! " + user.getUserName() + " , what would you like to do?");
		System.out.println();
		System.out.println("press 1 to add an item to the shop ");
		System.out.println("press 2 to remove an item from the shop ");
		System.out.println("press 3 to view all payments");
		System.out.println("press 4 to accept or reject a pending offer for an item ");
		System.out.println("press 5 to edit existing items");
		String userInput = sc.nextLine();
		employeeOp(userInput, sc);
	}

	private static void employeeOp(String userInput, Scanner sc) {
		if (userInput.equals("1")) {
			addItem(sc);
		} else if (userInput.equals("2")) {
			removeItem(sc);
		} else if (userInput.equals("3")) {
			acceptOrRejectOffer(sc);
		} else if (userInput.equals("4")) {
			
		} else {
			
		}
		
	}

	private static void acceptOrRejectOffer(Scanner sc) {
		offerService.getAll();
		
	}

	private static void addItem(Scanner sc) {
		System.out.println("item name: ");
		String itemName = sc.nextLine();
		item = new Item(null, itemName, true, null);
		itemService.addItem(item);
	}
	
	private static void removeItem(Scanner sc) {
		System.out.println("item id: ");
		Integer itemID = Integer.parseInt(sc.nextLine());
		Integer x = itemService.deleteItem(itemID);
		//System.out.println("check point! ");
	}

	public static void managerUI(Scanner sc) {
		System.out.println("Welcome back! " + user.getUserName() + " , what would you like to do?");
		System.out.println();
		System.out.println("press 1 to make employee accounts ");
		System.out.println("press 2 to fire employees ");
		System.out.println("press 3 to view sales of history of all offers ");
		String userInput = sc.nextLine();
		managerOp(userInput, sc);
	}

	private static void managerOp(String userInput, Scanner sc) {
		if (userInput.equals("1")) {
			addEmployee(sc);
		} else if (userInput.equals("2")) {
			removeEmployee(sc);
		} else if (userInput.equals("3")) {
			viewAllOffers();
		} else {
			
		}
		
	}

	private static void viewAllOffers() {
		OfferInfoDAOPostgres offerInfos = new OfferInfoDAOPostgres();
		System.out.println(offerInfos.getAll());	
	}

	private static void removeEmployee(Scanner sc) {
		System.out.println("employee id: ");
		Integer employeeID = Integer.parseInt(sc.nextLine());
		Integer x = userService.deleteEmployee(employeeID);
	}

	private static void addEmployee(Scanner sc) {
		System.out.println("username: ");
		String userName = sc.nextLine();
		System.out.println("password: ");
		String password = sc.nextLine();
		System.out.println("first name: ");
		String firstName = sc.nextLine();
		System.out.println("last name: ");
		String lastName = sc.nextLine();
		user = new User(null, firstName, lastName, userName,password, 2);
		userService.addEmployee(user);
		System.out.println("DONE! ");
	}

}
