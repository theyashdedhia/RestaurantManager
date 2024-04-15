import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class UpdatePriceTest {

	@Test
	public void testUpdatePrice() {
        HashMap<String, ItemDetails> liveStatusRestaurant = new HashMap<>();
        liveStatusRestaurant.put("burrito", new ItemDetails("Burrito", 7.0f, 9.0f, 2.0f, 0.0f));
        liveStatusRestaurant.put("fries", new ItemDetails("Fries", 4.0f, 8.0f, 5.0f, 0.0f));
        liveStatusRestaurant.put("soda", new ItemDetails("Soda", 2.5f, 0.0f, 0.0f, 0.0f));

        UpdatePrice updatePrice = new UpdatePrice();
        updatePrice.updatePrice(liveStatusRestaurant);

        assertEquals(5.0f, liveStatusRestaurant.get("fries").itemPrice, 0.0001);
    }

}
