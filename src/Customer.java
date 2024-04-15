//This file contains all classes and methods that perform the order functionality.

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Displays menu and takes user input for choice of food item and quantity
class Menu{
	List<CustomerOrder> orderFromMenu() {
		List<CustomerOrder> orders = new ArrayList<>();//Store order items and their quantities
		int userSelection = 5;
		System.out.println("\n========================================================");
		while(true) {
			//UI for food item selection
			System.out.println("->What do you want to order?");
			System.out.println("1.Burrito");
			System.out.println("2.Fries");
			System.out.println("3.Soda");
			System.out.println("4.Meal");
			System.out.println("5.Done/Back");
			System.out.print("Enter a number from the available options: ");
			
			Scanner input = new Scanner(System.in);
			String userInput = input.next();//User Input
			try {
				userSelection = Integer.parseInt(userInput);//Converts input to float
				if(userSelection < 1 || userSelection > 5) throw new WrongOptionException();//Throws exeption in case user selects a number other than the given chices
				break;
			}
			catch (WrongOptionException e) {//If user selects number outside of given options
				System.out.println("Choice does not exists. Please enter a number between 1 and 5!");
			}
			catch (Exception e){//For any other exception such as entering an invalid input
				System.out.print("Invalid Number. Please enter correct number!\n");
			}
		}
		
		if((int)userSelection == 5)return orders;//Back to main menu/User done ordering and wants to checkout
			
		String userSelectionItem = null;
		switch ((int)userSelection) {//Decides item based on user input
		case 1:
			userSelectionItem = "burrito";
			break;
		case 2:
			userSelectionItem = "fries";
			break;
		case 3:
			userSelectionItem = "soda";
			break;
		}
		
		float quantity;//Quantity of food item
		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter Quantity: ");
			String newPrice = input.next();//User Input
			try {
				quantity = Float.parseFloat(newPrice);//Convert input to float
				if(quantity < 0) throw new NegativeQuantityException();//We do not take negative quantities. If quantity is less than 0 it throws error
				break;
			}
			catch (NegativeQuantityException e) {//If user enters negative number
				System.out.println("Quantity cannot be negative!");
			}
			catch (Exception e){//For any other exception such as entering an invalid input
				System.out.print("Invalid Number. Please enter correct quantity!\n");
			}
		}
		
		if(userSelection == 4) {//Handling meals
			//We add each of the food items to the list with the entered quantity
			//We also give an discount of 1$ on each item
			CustomerOrder order1 = new CustomerOrder("burrito", quantity, 0);
			orders.add(order1);
			CustomerOrder order2 = new CustomerOrder("fries",quantity, 0);
			orders.add(order2);
			CustomerOrder order3 = new CustomerOrder("soda", quantity, 0);
			orders.add(order3);
			return orders;
		}
		//Creating the CustomerOrder Object and returning for further processing
		CustomerOrder order = new CustomerOrder(userSelectionItem, quantity, 0);
		orders.add(order);
		return orders;
	}
}

//Customer class processes the order actions once we get order from customer
class Customer extends Menu{
	List<CustomerOrder> orderList = new ArrayList<>();//Stores orders from customers
	
	//Processes orders by calling relevant methods
	void processOrder(HashMap<String, ItemDetails> liveStatusRestaurant) {
		getCustomerOrder();//Adds orders from customers to the list
		OrderDetails orderDetails = generateSale(liveStatusRestaurant);//Updates the logistics of restaurant and return order details
		
		if(orderDetails.totalAmount <= 0) return;//If there is no order
		
		//Once the customer places the order, the following is used to collect payment
		float amountRecieved;
		while(true) {
			System.out.printf("Total Amount for the Order: %.2f \n", orderDetails.totalAmount);//Shows customer how much their meal will cost
			System.out.print("Enter amount you wish to pay: ");
			Scanner input = new Scanner(System.in);
			String amount = input.next();//User Input
			try {
				amountRecieved = Float.parseFloat(amount);
				if(amountRecieved < orderDetails.totalAmount) throw new InsufficientAmountException();//If user enters amount less than the bill
				break;
			}
			catch (InsufficientAmountException e) {//If user enters amount less than bill			
				System.out.println("Insufficient Funds!");
			}
			catch (Exception e){//Any other exceptions like using incorrect input
				System.out.println("Invalid input. Please enter a valid amount!");
			}
		}
		//Displayed to customer on paying
		System.out.printf("\nYou will recieve $%.2f", amountRecieved - orderDetails.totalAmount);
		System.out.printf("\nYour food will be ready in %.0f minutes\n", orderDetails.waitTime);
//	Order Complete!
	}
	
	OrderDetails generateSale(HashMap<String, ItemDetails> liveStatusRestaurant) {//Change live status of restaurant
		OrderDetails orderDetails = new OrderDetails();
		Map<String, Float> prepTime = new HashMap<>();
		
		float totalPrice = 0;
		for (CustomerOrder order : this.orderList) {//Iterates over all orders
			if(order.item != null) {
				ItemDetails item = liveStatusRestaurant.get(order.item);//Get details of particular item from live restaurant status
				
				float netPrice = item.itemPrice - order.discount;//Subtracts discount if any
				totalPrice += (netPrice*order.quantity);
				item.itemSold += order.quantity;//Adds item sold
				item.netSales += totalPrice;//Add amount to sales
				
				if(item.itemAvailableInInventory >= order.quantity) {//Sufficient items are available in inventory
					item.itemAvailableInInventory -= order.quantity;
					prepTime.putIfAbsent(order.item, (float)0);
				}
				else {//Insufficient inventory. Cook new food.
					order.quantity -= item.itemAvailableInInventory;
					item.itemAvailableInInventory = 0;
					item.itemAvailableInInventory = (float)Math.floor(order.quantity%item.itemBatchSize);
					float preparation = order.quantity*(float)Math.floor(item.itemBatchPreparationTime/item.itemBatchSize);
					if(prepTime.containsKey(order.item)) {
						float currentTime = prepTime.get(order.item);
						prepTime.replace(order.item, currentTime + preparation);
					}
					else {
						prepTime.put(order.item, preparation);
					}
				}
			}
		}
		orderDetails.totalAmount = totalPrice;
		float maxTime = 0;
		for(String item : prepTime.keySet()) {//Checks the maximum wait time			
			maxTime = Math.max(prepTime.get(item), maxTime);
		}
		orderDetails.waitTime = maxTime;
		return orderDetails;//To be processed and shown to customers
	}
	
	//Runs recursively to get orders from customers
	void getCustomerOrder() {
		List<CustomerOrder> orders = orderFromMenu();
		if(orders.size() != 0 && orders.get(0).item != null) {//Checks if customer added another item
			for(CustomerOrder order : orders) {//Iterates over the list of orders received 
				this.orderList.add(order);//Adds orders to the list for processing
			}
			getCustomerOrder();//Recursive Call
		}
	}	
}