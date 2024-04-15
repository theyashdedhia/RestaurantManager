import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class GenerateReportTest {

	@Test
    public void testGenerateReport() {
        HashMap<String, ItemDetails> liveStatusRestaurant = new HashMap<>();
        ItemDetails burrito = new ItemDetails("Burrito", 7.0f, 9.0f, 2.0f, 0.0f);
        burrito.itemSold = 5;
        burrito.netSales = 35.0f;
        ItemDetails fries = new ItemDetails("Fries", 4.0f, 8.0f, 5.0f, 0.0f);
        fries.itemSold = 3;
        fries.netSales = 12.0f;
        ItemDetails soda = new ItemDetails("Soda", 2.5f, 0.0f, 0.0f, 0.0f);
        soda.itemSold = 8;
        soda.netSales = 20.0f;

        liveStatusRestaurant.put("burrito", burrito);
        liveStatusRestaurant.put("fries", fries);
        liveStatusRestaurant.put("soda", soda);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        GenerateReport report = new GenerateReport();
        report.generateReport(liveStatusRestaurant);

        String expectedOutput = "========================================================\n" +
                                "Total Sales:\n" +
                                "Burritos- Quantity: 5 Amount: 35.00" +
                                "Fries- Quantity: 3 Amount: 12.00" +
                                "Soda- Quantity: 8 Amount: 20.00" +
                                "Total Sales: 67.00";

        assertEquals(outContent.toString().replaceAll("\n","").replaceAll(" ",""), expectedOutput.replaceAll("\n","").replaceAll(" ",""));
	}
}
