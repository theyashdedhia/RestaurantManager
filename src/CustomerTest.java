import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class CustomerTest {

	@Test
	public void testOrderFromMenu() {
        Menu menu = new Menu();
        
        List<CustomerOrder> orders = menu.orderFromMenu();
       

        assertEquals(1, orders.size());
        assertEquals("burrito", orders.get(0).item);
        assertEquals(1.0, orders.get(0).quantity, 0.0001);
        assertEquals(0, orders.get(0).discount, 0.0001);

    }
	
	@Test
    public void testGenerateSale() {
        Customer customer = new Customer();
        HashMap<String, ItemDetails> liveStatusRestaurant = new HashMap<>();
        liveStatusRestaurant.put("burrito", new ItemDetails("Burrito", 7.0f, 9.0f, 2.0f, 4.0f));
        liveStatusRestaurant.put("fries", new ItemDetails("Fries", 4.0f, 8.0f, 5.0f, 10.0f));
        liveStatusRestaurant.put("soda", new ItemDetails("Soda", 2.5f, 0.0f, 0.0f, 20.0f));

        customer.orderList.add(new CustomerOrder("burrito", 3.0f, 0.0f));
        customer.orderList.add(new CustomerOrder("fries", 2.0f, 0.0f));

        OrderDetails orderDetails = customer.generateSale(liveStatusRestaurant);

        assertEquals(29.0f, orderDetails.totalAmount, 0.0001);
        assertEquals(0.0f, orderDetails.waitTime, 0.0001);
    }

}
