package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllayer.ControlPrice;
import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import modellayer.PPrice;

public class TestUpdatePrice {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void updatePrice() throws DatabaseLayerException {
		//Arrange
		ControlPrice ctrlPrice = new ControlPrice();
		PPrice expected = ctrlPrice.getCurrentPrice();
		
		
		//Act
		DBConnection.closeConnection();
		PPrice price = ctrlPrice.getPriceRemote(2);
		
		
		
		// Assert
		assertNotNull("Price can't be fetched from the database", price);
		
		assertEquals("Price is not what we expected", expected.getParkingPrice(), price.getParkingPrice());
		
	}

}
