package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.CreditCard;

/**
 * This class tests the class CreditCard.
 * 
 * @see application.models.CreditCard
 * 
 * @author Federico Canali
 *
 */
class CreditCardTest {
	
	private static final int ID = 1;
	private static final String OWNER_NAME = "Federico";
	private static final String OWNER_SURNAME = "Canali";
	private static final int CVV = 123;
	private static final long CARD_NUMBER = 1234567890;
	private static final String EXPIRATION_DATE = "11/22";

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		CreditCard creditCard = new CreditCard(OWNER_NAME, OWNER_SURNAME, CVV, CARD_NUMBER, EXPIRATION_DATE);
		assertTrue(creditCard.getOwnerName()== OWNER_NAME);
		assertAll(() -> assertTrue(creditCard.getOwnerSurname()== OWNER_SURNAME),
				() -> assertTrue(creditCard.getCvv()== CVV),
				() -> assertTrue(creditCard.getCardNumber()== CARD_NUMBER),
				() -> assertTrue(creditCard.getExpirationDate()== EXPIRATION_DATE));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		CreditCard creditCard = new CreditCard();
		creditCard.setId(ID);
		creditCard.setOwnerName(OWNER_NAME);
		creditCard.setOwnerSurname(OWNER_SURNAME);
		creditCard.setCvv(CVV);
		creditCard.setCardNumber(CARD_NUMBER);
		creditCard.setExpirationDate(EXPIRATION_DATE);
		
		assertTrue(creditCard.getId() == ID);
		assertAll(() -> assertTrue(creditCard.getOwnerName()== OWNER_NAME),
				() -> assertTrue(creditCard.getOwnerSurname()== OWNER_SURNAME),
				() -> assertTrue(creditCard.getCvv()== CVV),
				() -> assertTrue(creditCard.getCardNumber()== CARD_NUMBER),
				() -> assertTrue(creditCard.getExpirationDate()== EXPIRATION_DATE));
	}

}
