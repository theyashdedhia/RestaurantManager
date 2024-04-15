//This file contains custom classes which help in easy storage and retrieval of data and custom exceptions that are used for handling input.

//Custom Class to store details given by customer of each order
class CustomerOrder{
	String item;//Food item name
	float quantity;//Food item quantity
	float discount;//Discount on item
	public CustomerOrder() {
		
	}
	public CustomerOrder(String item, float quantity, float discount){
		this.item = item;
		this.quantity = quantity;
		this.discount = discount;
	}
}

//Custom Class to store order details generated
class OrderDetails{
	float totalAmount;//Bill for the order
	float waitTime;//Time till food is ready
}

//Custom Class to store essential details of an item
class ItemDetails{
	String itemName;
	float itemPrice;
	float itemBatchPreparationTime;
	float itemBatchSize;
	float itemAvailableInInventory;
	int itemSold = 0;//item sold initially will be 0
	float netSales = 0;//net sales initially will be 0
	
	ItemDetails(String itemName, float itemPrice, float itemBatchPreparationTime, float itemBatchSize, float itemAvailableInInventory) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemBatchPreparationTime = itemBatchPreparationTime;
		this.itemBatchSize = itemBatchSize;
		this.itemAvailableInInventory = itemAvailableInInventory;
	}
}

//-->Exceptions
class InsufficientAmountException extends Exception {//Amount less than bill
    public InsufficientAmountException() {
        super("Amount received is less than the total amount.");
    }
}

class WrongOptionException extends Exception {//Input outside available options
	public WrongOptionException() {
		super("Enter number from the given options");
	}
}

class NegativeQuantityException extends Exception {//Negative number entered
	public NegativeQuantityException() {
		super("Quantity cannot be negative");
	}
}

