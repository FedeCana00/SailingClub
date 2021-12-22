/**
 * 
 */
package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.PaymentMethod;

/**
 * @author Federico Canali
 *
 */
class PaymentMethodTest {
	
	private static final int ID = 12;
	private static final String OWNER_NAME = "Federico";
	private static final String OWNER_SURNAME = "Canali";

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		PaymentMethod paymentMethod1 = new PaymentMethod(OWNER_NAME, OWNER_SURNAME);
		assertTrue(paymentMethod1.getOwnerName() == OWNER_NAME);
		assertTrue(paymentMethod1.getOwnerSurname() == OWNER_SURNAME);
		
		PaymentMethod paymentMethod2 = new PaymentMethod(ID, OWNER_NAME, OWNER_SURNAME);
		assertTrue(paymentMethod2.getId() == ID);
		assertAll(() -> assertTrue(paymentMethod2.getOwnerName() == OWNER_NAME),
				() -> assertTrue(paymentMethod2.getOwnerSurname() == OWNER_SURNAME));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setId(ID);
		paymentMethod.setOwnerName(OWNER_NAME);
		paymentMethod.setOwnerSurname(OWNER_SURNAME);
		
		assertTrue(paymentMethod.getId() == ID);
		assertAll(() -> assertTrue(paymentMethod.getOwnerName() == OWNER_NAME),
				() -> assertTrue(paymentMethod.getOwnerSurname() == OWNER_SURNAME));
	}

}
