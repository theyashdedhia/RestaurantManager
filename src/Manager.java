//This file contains all classes and methods for generating sales and changing item price
import java.util.*;

class Manager{
	static Scanner input;
//	This is the initial status of the store. It is hard coded for now but can be connected to the database.
//	Price
//	Kept private to provide abstraction
	private float priceOfBurrito = 7;
	private float priceOfFries = 4;
	private float priceOfSoda = 2.50f;
// Preparation Time	
	static float preparationTimeOfBurritoBatch = 9;
	static float preparationTimeOfFriesBatch = 8;
	static float preparationTimeOfSodaBatch = 0;
// Batch Size	
	static float batchSizeOfBurrito = 2;
	static float batchSizeOfFries = 5;
	static float batchSizeOfSoda = 1;
// Item Available
	static float burritoAvailable = 0;
	static float friesAvailable = 0;
	static float sodaAvailable = 0;
	
//	Get Price of Items
	float getBurritoPrice(){
		return this.priceOfBurrito;
	}
	float getFriesPrice(){
		return this.priceOfFries;
	}
	float getSodaPrice(){
		return this.priceOfSoda;
	}
	
//	Set Price of Items
	protected void setBurritoPrice(float newPrice){
		this.priceOfBurrito = newPrice;
	}
	protected void setFriesoPrice(float newPrice){
		this.priceOfFries = newPrice;
	}
	protected void setSodaPrice(float newPrice){
		this.priceOfBurrito = newPrice;
	}
}

//Generate Report from the live status of restaurant
class GenerateReport extends Manager{
	
	void generateReport(HashMap<String, ItemDetails> liveStatusRestaurant) {
		System.out.print("\n========================================================\n");
		System.out.print("Total Sales:\n");
		//	Calculating and Printing Sales of Each Item
		System.out.printf("Burritos- Quantity: %d Amount: %.2f\n", liveStatusRestaurant.get("burrito").itemSold, liveStatusRestaurant.get("burrito").netSales);
		System.out.printf("Fries- Quantity: %d Amount: %.2f\n", liveStatusRestaurant.get("fries").itemSold, liveStatusRestaurant.get("fries").netSales);
		System.out.printf("Soda- Quantity: %d Amount: %.2f\n", liveStatusRestaurant.get("soda").itemSold, liveStatusRestaurant.get("soda").netSales);
		System.out.printf("Total Sales: %.2f", liveStatusRestaurant.get("burrito").netSales + liveStatusRestaurant.get("fries").netSales + liveStatusRestaurant.get("soda").netSales);
	}
}

//Contains logic to update price
class UpdatePrice extends Manager{
//	Takes user input of a specific item and new price
	void updatePrice(HashMap<String, ItemDetails> liveStatusRestaurant) {
		
//		Print Statements for Update Menu Price
		System.out.println("\n========================================================");
		System.out.println("->Which item's price do you want to update?");
		System.out.println("1.Burrito");
		System.out.println("2.Fries");
		System.out.println("3.Soda");
		System.out.println("4.Back to Main Menu");
		System.out.print("Enter a number from the available options: ");
		
//		Select Items
		input = new Scanner(System.in);
		String userInput = input.next();//User Input
		int userSelection;
		try {
			userSelection = Integer.parseInt(userInput);
			if (userSelection < 1 || userSelection > 4)	throw new Exception();//If user selects number outside of given options
		}
		catch (WrongOptionException e) {//If user selects number outside of given options
			System.out.println("Choice does not exists. Please enter a number between 1 and 5!");
			updatePrice(liveStatusRestaurant);
			return;
		}
		catch (Exception e){//Any other exception like user entering wrong input
			System.out.println("Invalid Number. Please enter an available number!\n");
			updatePrice(liveStatusRestaurant);
			return;
		}
		
		if(userSelection == 4) return;//Return to main menu

//		Select New Price
		float newPrice;
		while(true) {			
			System.out.print("Enter New Price: ");
			String Uinput = input.next();
			try {
				newPrice = Float.parseFloat(Uinput);
				if(newPrice<0)throw new NegativeQuantityException();
				break;
			}
			catch (NegativeQuantityException e) {//If user enters negative number
				System.out.println("Quantity cannot be negative!");
			}
			catch (Exception e){
				System.out.print("Invalid Number. Please enter correct number!\n");
			}
		}
		
//		Price Change Logic
//		Changes price in the live restaurant status
		switch (userSelection) {
		case 1:
			ItemDetails priceUpdateBurrito = liveStatusRestaurant.get("burrito");
			priceUpdateBurrito.itemPrice = newPrice;
			setBurritoPrice(newPrice);
			updatePrice(liveStatusRestaurant);
			break;
		case 2:
			ItemDetails priceUpdateFries = liveStatusRestaurant.get("fries");
			priceUpdateFries.itemPrice = newPrice;
			setFriesoPrice(newPrice);
			updatePrice(liveStatusRestaurant);
			break;
		case 3:
			ItemDetails priceUpdateSoda = liveStatusRestaurant.get("soda");
			priceUpdateSoda.itemPrice = newPrice;
			setSodaPrice(newPrice);
			updatePrice(liveStatusRestaurant);
			break;
		default:
			System.out.println("Incorrect Input! Make sure to enter a number from available options\n");
			updatePrice(liveStatusRestaurant);
		}
	}
}
