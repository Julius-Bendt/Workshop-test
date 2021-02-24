package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;
import modellayer.PReceipt;

public class TestBuyTicket {

	ControlPayStation psController;

	@Before
	public void setUp() throws Exception {
		psController = new ControlPayStation();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void shouldDisplay1MinFor1Cent() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 1; // In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;

		// Act
		psController.addPayment(coinValue, coinCurrency, coinType);

		// Assert
		String desc = String.format("Expected %s when paying with %s %s (%s)", expectedParkingTime, coinValue,
				coinCurrency, coinType);
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	@Test // One coin
	public void shouldDisplay40MinFor1Euro() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 40; // In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		psController.addPayment(coinValue, coinCurrency, coinType);

		// Assert
		String desc = String.format("Expected %s when paying with %s %s (%s)", expectedParkingTime, coinValue,
				coinCurrency, coinType);
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	@Test // Multiple coins
	public void shouldDisplay41MinFor1EuroAnd1Cent() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 41; // In minutes

		int coinValueOne = 1;
		int coinValueTwo = 1;
		Currency.ValidCurrency coinCurrencyOne = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinTypeOne = Currency.ValidCoinType.INTEGER;

		Currency.ValidCurrency coinCurrencyTwo = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinTypeTwo = Currency.ValidCoinType.FRACTION;

		// Act
		psController.addPayment(coinValueOne, coinCurrencyOne, coinTypeOne);
		psController.addPayment(coinValueTwo, coinCurrencyTwo, coinTypeTwo);

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	/*
	 * DANISH CROWNS
	 */

	@Test // One coin
	public void shouldDisplay3minFor50Ore() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 3; // In minutes
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;

		// Act
		psController.addPayment(coinValue, coinCurrency, coinType);

		// Assert
		String desc = String.format("Expected %s when paying with %s %s (%s)", expectedParkingTime, coinValue,
				coinCurrency, coinType);
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	@Test // One coin
	public void shouldDisplay6MinFor1Dkk() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 6; // In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		psController.addPayment(coinValue, coinCurrency, coinType);

		// Assert
		String desc = String.format("Expected %s when paying with %s %s (%s)", expectedParkingTime, coinValue,
				coinCurrency, coinType);
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	@Test // Multiple coins
	public void shouldDisplay56MinFor10DkkAnd50Ore() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 56; // In minutes

		int coinValueOne = 10;
		int coinValueTwo = 50;
		Currency.ValidCurrency coinCurrencyOne = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeOne = Currency.ValidCoinType.INTEGER;

		Currency.ValidCurrency coinCurrencyTwo = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeTwo = Currency.ValidCoinType.FRACTION;

		// Act
		psController.addPayment(coinValueOne, coinCurrencyOne, coinTypeOne);
		psController.addPayment(coinValueTwo, coinCurrencyTwo, coinTypeTwo);

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	/*
	 * MIXED CURRENCIES (DKK & EUR)
	 */

	@Test
	public void shouldDisplay94For1EuroAnd10Dkk() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 94; // In minutes

		int coinValueOne = 1;
		int coinValueTwo = 10;
		Currency.ValidCurrency coinCurrencyOne = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinTypeOne = Currency.ValidCoinType.INTEGER;

		Currency.ValidCurrency coinCurrencyTwo = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeTwo = Currency.ValidCoinType.INTEGER;

		// Act
		psController.addPayment(coinValueOne, coinCurrencyOne, coinTypeOne);
		psController.addPayment(coinValueTwo, coinCurrencyTwo, coinTypeTwo);

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	@Test
	public void shouldDisplay97MinFor1EuroAnd1CentAnd10DkkAnd50Ore() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 97; // In minutes

		int coinValueEuroInteger = 1;
		int coinValueEuroFrac = 1;

		int coinValueDkkInteger = 10;
		int coinValueDkkFrac = 50;
		Currency.ValidCurrency coinCurrencyDkk = Currency.ValidCurrency.DKK;
		Currency.ValidCurrency coinCurrencyEuro = Currency.ValidCurrency.EURO;

		Currency.ValidCoinType coinTypeInteger = Currency.ValidCoinType.INTEGER;
		Currency.ValidCoinType coinTypeFractional = Currency.ValidCoinType.FRACTION;

		// Act
		psController.addPayment(coinValueEuroInteger, coinCurrencyEuro, coinTypeInteger);
		psController.addPayment(coinValueEuroFrac, coinCurrencyEuro, coinTypeFractional);

		psController.addPayment(coinValueDkkInteger, coinCurrencyDkk, coinTypeInteger);
		psController.addPayment(coinValueDkkFrac, coinCurrencyDkk, coinTypeFractional);

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	@Test(expected = IllegalCoinException.class)
	public void shouldThrowIllegalCoinExceptionFromNokCurrency() throws IllegalCoinException {
		// Arrange

		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		psController.addPayment(coinValue, coinCurrency, coinType);

	}

	@Test
	public void shouldDisplay40MinFor1EuroAnd1Nok() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 40; // In minutes

		int coinValueOne = 1;
		int coinValueTwo = 1;
		Currency.ValidCurrency coinCurrencyOne = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinTypeOne = Currency.ValidCoinType.INTEGER;

		Currency.ValidCurrency coinCurrencyTwo = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinTypeTwo = Currency.ValidCoinType.INTEGER;

		// Act
		psController.addPayment(coinValueOne, coinCurrencyOne, coinTypeOne);

		try {
			psController.addPayment(coinValueTwo, coinCurrencyTwo, coinTypeTwo);
		} catch (IllegalCoinException e) {
			// This is the excepted behaviour
			e.printStackTrace();
		}

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());

	}

	@Test
	public void shouldDisplay6MinFor1DkkAnd1Nok() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 6; // In minutes

		int coinValueOne = 1;
		int coinValueTwo = 1;
		Currency.ValidCurrency coinCurrencyOne = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeOne = Currency.ValidCoinType.INTEGER;

		Currency.ValidCurrency coinCurrencyTwo = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinTypeTwo = Currency.ValidCoinType.INTEGER;

		// Act
		psController.addPayment(coinValueOne, coinCurrencyOne, coinTypeOne);

		try {
			psController.addPayment(coinValueTwo, coinCurrencyTwo, coinTypeTwo);
		} catch (IllegalCoinException e) {
			// This is the excepted behaviour
			e.printStackTrace();
		}

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	@Test
	public void shouldDisplay94MinFor1EuroAnd10DkkAnd1Nok() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 94; // In minutes

		int coinValueDkk = 10;
		int coinValueNok = 1;
		int coinValueEuro = 1;
		
		Currency.ValidCurrency coinCurrencyEUR = Currency.ValidCurrency.EURO;
		Currency.ValidCurrency coinCurrencyDKK = Currency.ValidCurrency.DKK;
		Currency.ValidCurrency coinCurrencyNOK = Currency.ValidCurrency.NOK;
		
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;


		// Act
		psController.addPayment(coinValueEuro, coinCurrencyEUR, coinType);
		psController.addPayment(coinValueDkk, coinCurrencyDKK, coinType);
		try {
			psController.addPayment(coinValueNok, coinCurrencyNOK, coinType);
		} catch (IllegalCoinException e) {
			// This is the excepted behaviour
			e.printStackTrace();
		}

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	
	@Test
	public void shouldPrintRecipt() throws IllegalCoinException, DatabaseLayerException {
		//Arrange
		int expectedValue = 40; // In minutes
		PReceipt recipt = null; 
		
		
		psController.addPayment(1, ValidCurrency.EURO, ValidCoinType.INTEGER);
		
		//Act
		recipt = psController.buy();
		
		
		// Assert
		String desc = String.format("Expected %s was %s ", expectedValue, recipt.getValue());
		assertEquals(desc, expectedValue, recipt.getValue());
	}
	
	
	@Test
	public void shouldDisplay0MinWhenCancelIsPressed() throws IllegalCoinException {
		//Arrange
		int expectedParkingTime = 0; // In minutes
		
		psController.addPayment(1, ValidCurrency.EURO, ValidCoinType.INTEGER);
		
		//Act
		psController.cancel();
		
		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

}
