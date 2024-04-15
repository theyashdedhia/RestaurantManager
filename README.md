# Burrito King Application

The Burrito King Application is a Java-based console application that simulates a restaurant ordering system. It allows customers to place orders for food items, generates sales reports, and enables the manager to update item prices. The application follows an object-oriented programming approach and leverages various programming concepts, including inheritance, abstraction, and exception handling.

## Class Structure

The application consists of the following classes:

1. **Main**: This class serves as the entry point of the application and contains the main method. It initializes the necessary objects and handles the main menu functionality.

2. **Customer**: This class extends the `Menu` class and handles the order processing for customers. It provides methods to get customer orders, generate sales, and process payments.

3. **Menu**: This class displays the menu options and takes user input for the choice of food items and quantities.

4. **Manager**: This class contains the initial status of the restaurant, including item prices, preparation times, batch sizes, and available inventory. It also provides methods to retrieve and update item prices.

5. **GenerateReport**: This class extends the `Manager` class and generates sales reports based on the live status of the restaurant.

6. **UpdatePrice**: This class extends the `Manager` class and allows updating the prices of food items.

7. **CustomerOrder**: This class represents a customer's order, including the item name, quantity, and any applicable discount.

8. **OrderDetails**: This class stores the total amount and wait time for an order.

9. **ItemDetails**: This class holds the details of a food item, such as name, price, batch preparation time, batch size, available inventory, items sold, and net sales.

10. **Custom Exceptions**: The application includes custom exceptions to handle specific scenarios, such as `InsufficientAmountException`, `WrongOptionException`, and `NegativeQuantityException`.

## Object-Oriented Programming Principles

The application follows the principles of object-oriented programming, including:

1. **Inheritance**: The `Customer` class inherits from the `Menu` class, and the `GenerateReport` and `UpdatePrice` classes inherit from the `Manager` class.

2. **Abstraction**: The `Manager` class provides an abstraction layer for item prices, preparation times, batch sizes, and inventory, allowing for easy modification and maintenance.

3. **Encapsulation**: The application encapsulates data and functionality within classes, ensuring data integrity and providing controlled access to class members.

4. **Polymorphism**: The application uses method overriding and dynamic binding to handle different scenarios, such as updating prices for different food items.

## Running the Application

To run the Burrito King Application, follow these steps:

1. Compile the Java files using the following command in the terminal or command prompt:

```
javac Objects.java Customer.java Manager.java Main.java
```

2. Run the application with the following command:

```
java Main
```

The application will start, and you can interact with the main menu by entering the corresponding numbers for the desired operations.

## Running Test Cases

The application includes test cases.

Run the test cases with the following commands:

```
java -cp .:junit-4.XX.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore GenerateReportTest ManagerTest UpdatePriceTest
```

The test cases will execute, and the results will be displayed in the console.
