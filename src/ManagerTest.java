import static org.junit.Assert.*;

import org.junit.Test;

public class ManagerTest {

	@Test
	public void testGetBurritoPrice() {
		Manager manager = new Manager();
        assertEquals(7.0f, manager.getBurritoPrice(), 0.0001);
	}

	@Test
	public void testGetFriesPrice() {
		Manager manager = new Manager();
        assertEquals(4.0f, manager.getFriesPrice(), 0.0001);
	}

	@Test
	public void testGetSodaPrice() {
		Manager manager = new Manager();
        assertEquals(2.5f, manager.getSodaPrice(), 0.0001);
	}

}
