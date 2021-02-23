package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	ControlPayStation psController;
	final Currency.ValidCurrency EURO = Currency.ValidCurrency.EURO;
	final Currency.ValidCurrency DKK = Currency.ValidCurrency.DKK;

	final Currency.ValidCoinType FRACTION = Currency.ValidCoinType.FRACTION;
	final Currency.ValidCoinType INTEGER = Currency.ValidCoinType.INTEGER;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		psController = new ControlPayStation();
	}

	/**
	 * Entering 1 cent and 50 �re should make the display report 4 minutes parking
	 * time.
	 */
	@Test
	public void shouldDisplay4MinFor1CentAnd50Ore() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 4; // In minutes

		int euroValue = 1;
		int dkkValue = 50;

		// Act
		psController.addPayment(euroValue, EURO, FRACTION);
		psController.addPayment(dkkValue, DKK, FRACTION);

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, psController.readDisplay());
		assertEquals(desc, expectedParkingTime, psController.readDisplay());
	}

	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		psController.setReady();
	}

}
