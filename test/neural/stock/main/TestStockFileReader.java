package neural.stock.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStockFileReader {

	@Test
	public void test() {
		
		String filenameStr = "X:/Cs/Java/Neural/StockMarket/data/dataset_01.txt";
		StockFileReader stockFileReader = new StockFileReader();
		stockFileReader.read(filenameStr);
		//stockFileReader.readFile(filenameStr);
		//fail("Not yet implemented");
	}

}
