package org.neuroph.contrib.samples.stockmarket;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStockSocketReader {

	@Test
	public void test() {
		StockSocketReader stockSocketReader = new StockSocketReader();
		stockSocketReader.run();
		//fail("Not yet implemented");
	}

}
