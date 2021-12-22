/**
 * 
 */
package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import application.models.PaymentMethod;
import application.models.SubscriptionFee;

/**
 * @author Federico Canali
 *
 */
class SubscriptionFeeTest {

	private static final int ID = 12;
	private static final int PARTNER_ID = 3;
	private static final float PRICE = 99;
	private static final Date DATE = new Date(12344556);
	private static final PaymentMethod PAYMENT_METHOD = new PaymentMethod("Federico", "Canali");
	private static final int BOAT_ID = 1234;
	private static final int RACE_ID = 1537;

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		SubscriptionFee subscriptionFee1 = new SubscriptionFee(PARTNER_ID, PRICE, DATE, PAYMENT_METHOD, RACE_ID, BOAT_ID);
		assertTrue(subscriptionFee1.getPartnerId() == PARTNER_ID);
		assertAll(() -> assertTrue(subscriptionFee1.getPrice() == PRICE),
				() -> assertTrue(subscriptionFee1.getDate().equals(DATE)),
				() -> assertTrue(subscriptionFee1.getPaymentMethod().equals(PAYMENT_METHOD)),
				() -> assertTrue(subscriptionFee1.getBoatId() == BOAT_ID),
				() -> assertTrue(subscriptionFee1.getRaceId() == RACE_ID));
		
		SubscriptionFee subscriptionFee2 = new SubscriptionFee(ID, PARTNER_ID, PRICE, DATE, PAYMENT_METHOD, RACE_ID, BOAT_ID);
		assertTrue(subscriptionFee2.getId() == ID);
		assertAll(() -> assertTrue(subscriptionFee2.getPartnerId() == PARTNER_ID),
					() -> assertTrue(subscriptionFee2.getPrice() == PRICE),
					() -> assertTrue(subscriptionFee2.getDate().equals(DATE)),
					() -> assertTrue(subscriptionFee2.getPaymentMethod().equals(PAYMENT_METHOD)),
					() -> assertTrue(subscriptionFee2.getBoatId() == BOAT_ID),
					() -> assertTrue(subscriptionFee2.getRaceId() == RACE_ID));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		SubscriptionFee subscriptionFee = new SubscriptionFee();
		subscriptionFee.setId(ID);
		subscriptionFee.setPartnerId(PARTNER_ID);
		subscriptionFee.setDate(DATE);
		subscriptionFee.setPrice(PRICE);
		subscriptionFee.setPaymentMethod(PAYMENT_METHOD);
		subscriptionFee.setBoatId(BOAT_ID);
		subscriptionFee.setRaceId(RACE_ID);
		
		assertTrue(subscriptionFee.getId() == ID);
		assertAll(() -> assertTrue(subscriptionFee.getPartnerId() == PARTNER_ID),
				() -> assertTrue(subscriptionFee.getPrice() == PRICE),
				() -> assertTrue(subscriptionFee.getDate().equals(DATE)),
				() -> assertTrue(subscriptionFee.getPaymentMethod().equals(PAYMENT_METHOD)),
				() -> assertTrue(subscriptionFee.getBoatId() == BOAT_ID),
				() -> assertTrue(subscriptionFee.getRaceId() == RACE_ID));
	}
}
