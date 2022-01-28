package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import application.models.PaymentMethod;
import application.models.StorageFee;

/**
 * This class tests the class StorageFee.
 * 
 * @see application.models.StorageFee
 * 
 * @author Federico Canali
 *
 */
class StorageFeeTest {
	
	private static final int ID = 12;
	private static final int PARTNER_ID = 3;
	private static final float PRICE = 99;
	private static final Date DATE = new Date(12344556);
	private static final PaymentMethod PAYMENT_METHOD = new PaymentMethod("Federico", "Canali");
	private static final int BOAT_ID = 1234;

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		StorageFee storageFee1 = new StorageFee(PARTNER_ID, PRICE, DATE, PAYMENT_METHOD, BOAT_ID);
		assertTrue(storageFee1.getPartnerId() == PARTNER_ID);
		assertAll(() -> assertTrue(storageFee1.getPrice() == PRICE),
				() -> assertTrue(storageFee1.getDate().equals(DATE)),
				() -> assertTrue(storageFee1.getPaymentMethod().equals(PAYMENT_METHOD)),
				() -> assertTrue(storageFee1.getBoatId() == BOAT_ID));
		
		StorageFee storageFee2 = new StorageFee(ID, PARTNER_ID, PRICE, DATE, PAYMENT_METHOD, BOAT_ID);
		assertTrue(storageFee2.getId() == ID);
		assertAll(() -> assertTrue(storageFee2.getPartnerId() == PARTNER_ID),
					() -> assertTrue(storageFee2.getPrice() == PRICE),
					() -> assertTrue(storageFee2.getDate().equals(DATE)),
					() -> assertTrue(storageFee2.getPaymentMethod().equals(PAYMENT_METHOD)),
					() -> assertTrue(storageFee2.getBoatId() == BOAT_ID));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		StorageFee storageFee = new StorageFee();
		storageFee.setId(ID);
		storageFee.setPartnerId(PARTNER_ID);
		storageFee.setDate(DATE);
		storageFee.setPrice(PRICE);
		storageFee.setPaymentMethod(PAYMENT_METHOD);
		storageFee.setBoatId(BOAT_ID);
		
		assertTrue(storageFee.getId() == ID);
		assertAll(() -> assertTrue(storageFee.getPartnerId() == PARTNER_ID),
				() -> assertTrue(storageFee.getPrice() == PRICE),
				() -> assertTrue(storageFee.getDate().equals(DATE)),
				() -> assertTrue(storageFee.getPaymentMethod().equals(PAYMENT_METHOD)),
				() -> assertTrue(storageFee.getBoatId() == BOAT_ID));
	}
}
