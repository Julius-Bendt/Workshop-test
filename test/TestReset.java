package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy
	 * scenario
	 */
	@Test
	public void shouldClearAfterBuy() throws IllegalCoinException, Exception {
		// Arrange
		int expectedParkingTime = 0; // In minutes

		ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.INTEGER);

		// Act
		ps.buy();

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, ps.readDisplay());
		assertEquals(desc, expectedParkingTime, ps.readDisplay());
	}

	/**
	 * Verify that cancel() clears the pay station
	 */
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 0; // In minutes

		ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.INTEGER);

		// Act
		ps.cancel();

		// Assert
		String desc = String.format("Expected %s was %s ", expectedParkingTime, ps.readDisplay());
		assertEquals(desc, expectedParkingTime, ps.readDisplay());
	}
}
