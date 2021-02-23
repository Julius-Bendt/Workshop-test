package test;

import org.junit.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation psController;
	final Currency.ValidCurrency EURO = Currency.ValidCurrency.EURO;
	final Currency.ValidCurrency NOK = Currency.ValidCurrency.NOK;

	final Currency.ValidCoinType FRACTION = Currency.ValidCoinType.FRACTION;
	final Currency.ValidCoinType INTEGER = Currency.ValidCoinType.INTEGER;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		psController = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */

	// Norwegian coin
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalCurrencyNokCoin() throws IllegalCoinException {
		// Arrange
		int coinValue = 1;

		// Act
		psController.addPayment(coinValue, NOK, INTEGER);
	}

	// unknown Euro coin value
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalEuroCoin() throws IllegalCoinException {
		// Arrange
		int coinValue = 69;

		// Act
		psController.addPayment(coinValue, EURO, FRACTION);
	}
}
