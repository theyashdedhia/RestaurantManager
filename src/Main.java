//This is the main file for the entire application.
//It has the main method and driver code for main menu

import java.util.Scanner;
import java.util.HashMap;

//Main class
public class Main {

	static Scanner input;
	public static void main(String[] args) {//Code Entry Point
		System.out.println("\n\n<---Welcome to Burrito King!--->");
		
		Manager manager = new Manager();//Initializing manager class
		
//		Initializing the initial instance of each item
		ItemDetails liveStatusBurrito = new ItemDetails("Burrito", manager.getBurritoPrice(), Manager.preparationTimeOfBurritoBatch, Manager.batchSizeOfBurrito, Manager.burritoAvailable);
		ItemDetails liveStatusFries = new ItemDetails("Fries", manager.getFriesPrice(), Manager.preparationTimeOfFriesBatch, Manager.batchSizeOfFries, Manager.friesAvailable);
		ItemDetails liveStatussoda = new ItemDetails("Soda", manager.getSodaPrice(), Manager.preparationTimeOfSodaBatch, Manager.batchSizeOfSoda, Manager.sodaAvailable);
		
//		Initializing the initial instance of entire restaurant
		HashMap<String, ItemDetails> liveStatusRestaurant = new HashMap<>();
		liveStatusRestaurant.put("burrito", liveStatusBurrito);
		liveStatusRestaurant.put("fries", liveStatusFries);
		liveStatusRestaurant.put("soda", liveStatussoda);
		
		boolean programRunning = true;//flag to keep running program
		
//		Loop to print and get user input for main menu
		while(programRunning) {
			System.out.println("\n========================================================");
			System.out.println("->How can we help you?");
			System.out.println("1. Order Items");
			System.out.println("2. Show Sales Report");
			System.out.println("3. Update Prices");
			System.out.println("4. Exit");
			System.out.print("Enter a number from the available options: ");
			input = new Scanner(System.in);
			String userInput = input.next();//get user's choice
			int userSelection;
			try {
				userSelection = Integer.parseInt(userInput);//convert string to integer
				if(userSelection < 1 || userSelection > 4)throw new WrongOptionException();//If user selects number outside of given options
			}
			catch (WrongOptionException e) {//If user selects number outside of given options
				userSelection = 5;
			}
			catch (Exception e){//Any other exception, like incorrect input
				userSelection = 0;
			}
//			Call the appropriate function based on user's choice
			switch (userSelection) {
				case 1:
					Customer customer = new Customer();//Creating object of customer class
					customer.processOrder(liveStatusRestaurant);//pass the live restaurant status
					break;
				case 2:
					GenerateReport report = new GenerateReport();//Create object for report generation
					report.generateReport(liveStatusRestaurant);//pass the live restaurant status
					break;
				case 3:
					UpdatePrice updatePrice = new UpdatePrice();//Create object for updating price
					updatePrice.updatePrice(liveStatusRestaurant);//pass the live restaurant status
					break;
//				User wants to exit
				case 4:
					System.out.println("\n<---Thank You for chosing us! Visit Again--->");
					programRunning = false;//change flag to stop program
					break;
//				Input out of bounds	
				case 5:
					System.out.println("Choice does not exists. Please enter a number between 1 and 4!");
//				Wrong input from user, incorrect input type
				default:
					System.out.println("Incorrect Input! Make sure to enter a number from available options");
			}
		}
	}
}

