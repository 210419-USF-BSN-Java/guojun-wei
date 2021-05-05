package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.DAOs.OfferInfoDAOPostgres;
import com.revature.models.Count;
import com.revature.models.Item;
import com.revature.models.OfferInfo;
import com.revature.models.User;
import com.revature.services.ItemServiceImplementation;
import com.revature.services.OfferServiceImplementation;
import com.revature.services.UserServiceImplementation;

public class Controller {

	private static UserServiceImplementation userService = new UserServiceImplementation();
	
	private static ItemServiceImplementation itemService = new ItemServiceImplementation();
	
	private static OfferServiceImplementation offerService = new OfferServiceImplementation();
	
	private static User user;
			
	private static Scanner sc = new Scanner(System.in);
	
	private static Logger logger = LogManager.getLogger(Controller.class);
	
	public static void main(String[] args) {
		menu();
	}
	
	private static void menu() {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the FIFA soccer player card shopping system, please make options!");
		System.out.println("press 1 for login");
		System.out.println("No account yet? No problem! press 2 for customer registration!");
		System.out.println("press 3 to exit");
		String userInput = sc.nextLine();
		userLogin(userInput);
	}

	public static void userLogin(String userInput) {
		if (userInput.equals("1")) {
			logger.info("This is information message");
			//verify customer login
			userInfo();
		} else if (userInput.equals("2")) {
			//register customer login
			logger.info("new user registration");
			userRegistration();
		}  else if (userInput.equals("3")) {
			//register customer login
			logger.info("exit");
			System.out.println("Exit the app!");
			System.exit(0);
		} else {
			System.out.println("illegal input, please re-entry!");
			logger.error("This is an error message");
			System.out.println();
			menu();
		}
	}
	
	public static void userInfo() {
		System.out.println("username: ");
		String userName = sc.nextLine();
		System.out.println("password: ");
		String password = sc.nextLine();
		logger.info("Entering information message");
		user = userService.verifyUser(userName, password);
		if (user != null){	
			if (user.getType() == 1) {
				logger.info("enter customerUI");
				customerUI();
			} else if (user.getType() == 2) {
				logger.info("enter employeeUI");
				employeeUI();
			} else {
				logger.info("enter managerUI");
				managerUI();
			}
		}else {
			System.out.println("illegal username/password, please reentry!");
			System.out.println();
			menu();
		}
		//return User;
	}
	
	public static void userRegistration() {
		System.out.println("username: ");
		String userName = sc.nextLine();
		System.out.println("password: ");
		String password = sc.nextLine();
		System.out.println("first name: ");
		String firstName = sc.nextLine();
		System.out.println("last name: ");
		String lastName = sc.nextLine();
		User newUser = new User(null, firstName, lastName, userName,password, 1);
		userService.registration(newUser);
		System.out.println("Registration successfully! ");
		menu();
	}
	
	public static void customerUI() {
		System.out.println("Welcome back! " + user.getUserName() + " , what would you like to do?");
		System.out.println();
		System.out.println("press 1 to view available items");
		System.out.println("press 2 to make an offer for an item");
		System.out.println("press 3 to view the item that I own");
		System.out.println("press 4 to make a payment");
		System.out.println("press 5 to exit");
		String userInput = sc.nextLine();
		customerOp(userInput);
		
	}

	private static void customerOp(String userInput) {
		if (userInput.equals("1")) {
			logger.info("enter viewItems");
			viewItems();
		} else if (userInput.equals("2")) {
			logger.info("enter makeOffer");
			makeOffer();
		} else if (userInput.equals("3")) {
			logger.info("enter viewOwnedItems");
			viewOwnedItems();
		} else if (userInput.equals("4")) {
			logger.info("enter makePayments");
			makePayments();
		} else if (userInput.equals("5")){
			System.exit(0);
			logger.error("if not exit");
		} else {
			System.out.println("illegal input");
			customerUI();
		}
		
	}

	
	private static void makePayments() {
		System.out.println("This is what you own! ");
		List<Item> items = itemService.getOwnedItems(2, user.getUserId());
		System.out.println(items);
		if (items == null) {
			System.out.println("You don't own any players yet, bye bye!");
			System.exit(0);
		}
		System.out.println("Please enter the itemID that you want to pay ");
		Integer itemID = Integer.parseInt(sc.nextLine());
		Boolean updatePay = offerService.updatePayment(4, user.getUserId(), itemID);
		//Boolean updatePay = itemService.updatePayment(4, user.getUserId(), itemID);
		System.out.println("Payment done!");
		customerUI();
	}

	private static void viewOwnedItems() {
		System.out.println("This is what you own! ");
		List<Item> items = itemService.getOwnedItems(2, user.getUserId());
		System.out.println(items);
	}

	private static void makeOffer() {
		System.out.println("These stars are available!");
		//see which item is available
		List<Item> items = itemService.getAvailableOfferList(user.getUserId());
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
		System.out.println("Offer has been made!");
		customerUI();
	}

	private static void viewItems() {
		List<Item> items = itemService.getAvailableItem(true);
		System.out.println(items);
		System.out.println();
		customerUI();
	}

	public static void employeeUI() {
		System.out.println("Welcome back! " + user.getUserName() + " , what would you like to do?");
		System.out.println();
		System.out.println("press 1 to add an item to the shop ");
		System.out.println("press 2 to remove an item from the shop ");
		System.out.println("press 3 to accept or reject a pending offer for an item ");
		System.out.println("press 4 to view all payments");
		System.out.println("press 5 to edit existing items");
		System.out.println("press 6 to exit");
		String userInput = sc.nextLine();
		employeeOp(userInput);
	}

	private static void employeeOp(String userInput) {
		if (userInput.equals("1")) {
			logger.info("enter addItem");
			addItem();
		} else if (userInput.equals("2")) {
			logger.info("enter removeItem");
			removeItem();
		} else if (userInput.equals("3")) {
			logger.info("enter acceptOrRejectOffer");
			acceptOrRejectOffer();
		} else if (userInput.equals("4")) {
			logger.info("enter viewAllOffersEmployee");
			viewAllOffersEmployee();
		} else if (userInput.equals("5")){
			System.out.println("EDIT");
		} else if ((userInput.equals("6"))) {
			System.exit(0);
		}
		
	}

	private static void viewAllOffersEmployee() {
		OfferInfoDAOPostgres offerInfos = new OfferInfoDAOPostgres();
		System.out.println(offerInfos.getAll());	
		employeeUI();
	}

	private static void acceptOrRejectOffer() {
		System.out.println("The pending offers are listed below:");
		List<OfferInfo> pendingOffers = offerService.getOfferByStatus(1);
		System.out.println(pendingOffers);
		System.out.println("Accept offer by offerID and itemID ");
		System.out.println("offerID: ");
		Integer offerID = Integer.parseInt(sc.nextLine());
		System.out.println("itemID: ");
		Integer itemID = Integer.parseInt(sc.nextLine());
		//update offer_info table payment status
		Boolean updateOfferTable = offerService.update(offerID, itemID);
		//update item table availability false
		Boolean updateItemTable = itemService.update(false, itemID);
		System.out.println("table updated! ");
		employeeUI();
	}

	private static void addItem() {
		System.out.println("item name: ");
		String itemName = sc.nextLine();
		Item item = new Item(null, itemName, true, null);
		itemService.addItem(item);
		System.out.println("item successfully added!");
		employeeUI();
	}
	
	private static void removeItem() {
		System.out.println("item id: ");
		Integer itemID = Integer.parseInt(sc.nextLine());
		Boolean bool  = itemService.deleteItem(itemID);
		System.out.println("item removed! ");
		employeeUI();
	}

	public static void managerUI() {
		System.out.println("Welcome back! " + user.getUserName() + " , what would you like to do?");
		System.out.println();
		List<Count> pay = offerService.calWeeklyPayment();
		System.out.println("WEEKLY PAYMENT IS ");
		System.out.println(pay);
		System.out.println("press 1 to make employee accounts ");
		System.out.println("press 2 to fire employees ");
		System.out.println("press 3 to view sales of history of all offers ");
		System.out.println("press 4 exit ");
		String userInput = sc.nextLine();
		managerOp(userInput);
	}

	private static void managerOp(String userInput) {
		if (userInput.equals("1")) {
			logger.info("enter addEmployee");
			addEmployee();
		} else if (userInput.equals("2")) {
			logger.info("enter removeEmployee");
			removeEmployee();
		} else if (userInput.equals("3")) {
			logger.info("enter viewAllOfferManager");
			viewAllOffersManager();
		} else if (userInput.equals("4")){
			System.exit(0);
		} else {
			System.out.println("illegal input! ");
			managerUI();
		}
		
	}

	private static void viewAllOffersManager() {
		System.out.println(offerService.getAll());	
		managerUI();
	}

	private static void removeEmployee() {
		System.out.println("employee id: ");
		Integer employeeID = Integer.parseInt(sc.nextLine());
		Boolean bool = userService.deleteEmployee(employeeID);
		System.out.println("Employee successfully deleted! ");
		managerUI();
	}

	private static void addEmployee() {
		System.out.println("username: ");
		String userName = sc.nextLine();
		System.out.println("password: ");
		String password = sc.nextLine();
		System.out.println("first name: ");
		String firstName = sc.nextLine();
		System.out.println("last name: ");
		String lastName = sc.nextLine();
		User newUser = new User(null, firstName, lastName, userName,password, 2);
		userService.addEmployee(newUser);
		System.out.println("Employee successfully added! ");
		managerUI();
	}

}
