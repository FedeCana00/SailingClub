package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import application.models.Payment;
import application.models.PaymentMethod;

/**
 * This class tests the class Payment.
 * 
 * @see application.models.Payment
 * 
 * @author Federico Canali
 *
 */
class PaymentTest {
	
	private static final int ID = 12;
	private static final int PARTNER_ID = 3;
	private static final float PRICE = 99;
	private static final Date DATE = new Date(12344556);
	private static final PaymentMethod PAYMENT_METHOD = new PaymentMethod("Federico", "Canali");

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		Payment payment1 = new Payment(PARTNER_ID, PRICE, DATE, PAYMENT_METHOD);
		assertTrue(payment1.getPartnerId() == PARTNER_ID);
		assertAll(() -> assertTrue(payment1.getPrice() == PRICE),
				() -> assertTrue(payment1.getDate().equals(DATE)),
				() -> assertTrue(payment1.getPaymentMethod().equals(PAYMENT_METHOD)));
		
		Payment payment2 = new Payment(ID, PARTNER_ID, PRICE, DATE, PAYMENT_METHOD);
		assertTrue(payment2.getId() == ID);
		assertAll(() -> assertTrue(payment2.getPartnerId() == PARTNER_ID),
					() -> assertTrue(payment2.getPrice() == PRICE),
					() -> assertTrue(payment2.getDate().equals(DATE)),
					() -> assertTrue(payment2.getPaymentMethod().equals(PAYMENT_METHOD)));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		Payment payment = new Payment();
		payment.setId(ID);
		payment.setPartnerId(PARTNER_ID);
		payment.setDate(DATE);
		payment.setPrice(PRICE);
		payment.setPaymentMethod(PAYMENT_METHOD);
		
		assertTrue(payment.getId() == ID);
		assertAll(() -> assertTrue(payment.getPartnerId() == PARTNER_ID),
				() -> assertTrue(payment.getPrice() == PRICE),
				() -> assertTrue(payment.getDate().equals(DATE)),
				() -> assertTrue(payment.getPaymentMethod().equals(PAYMENT_METHOD)));
	}

}
