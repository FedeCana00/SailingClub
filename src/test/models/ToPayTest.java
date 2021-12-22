/**
 * 
 */
package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.ToPay;

/**
 * @author Federico Canali
 *
 */
class ToPayTest {
	
	private static final int ID = 12;
	private static final int PARTNER_ID = 23;
	private static final float PRICE = 900;
	private static final int BOAT_ID = 1;
	private static final boolean IS_MEMBERSHIP_FEE = true;

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		ToPay toPay1 = new ToPay(PARTNER_ID, PRICE, IS_MEMBERSHIP_FEE);
		assertTrue(toPay1.getPartnerId() == PARTNER_ID);
		assertAll(() -> assertTrue(toPay1.getPrice() == PRICE),
					() -> assertTrue(toPay1.isMembershipFee() == IS_MEMBERSHIP_FEE));
		
		ToPay toPay2 = new ToPay(PARTNER_ID, PRICE, IS_MEMBERSHIP_FEE, BOAT_ID);
		assertTrue(toPay2.getPartnerId() == PARTNER_ID);
		assertAll(() -> assertTrue(toPay2.getPrice() == PRICE),
					() -> assertTrue(toPay2.isMembershipFee() == IS_MEMBERSHIP_FEE),
					() -> assertTrue(toPay2.getBoatId() == BOAT_ID));
		
		ToPay toPay3 = new ToPay(ID, PARTNER_ID, PRICE, IS_MEMBERSHIP_FEE);
		assertTrue(toPay3.getId() == ID);
		assertAll(() -> assertTrue(toPay3.getPartnerId() == PARTNER_ID),
					() -> assertTrue(toPay3.getPrice() == PRICE),
					() -> assertTrue(toPay3.isMembershipFee() == IS_MEMBERSHIP_FEE));
		
		ToPay toPay4 = new ToPay(ID, PARTNER_ID, PRICE, IS_MEMBERSHIP_FEE, BOAT_ID);
		assertTrue(toPay4.getId() == ID);
		assertAll(() -> assertTrue(toPay4.getPartnerId() == PARTNER_ID),
					() -> assertTrue(toPay4.getPrice() == PRICE),
					() -> assertTrue(toPay4.isMembershipFee() == IS_MEMBERSHIP_FEE),
					() -> assertTrue(toPay4.getBoatId() == BOAT_ID));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		ToPay toPay = new ToPay();
		toPay.setId(ID);
		toPay.setPrice(PRICE);
		toPay.setPartnerId(PARTNER_ID);
		toPay.setMembershipFee(IS_MEMBERSHIP_FEE);
		toPay.setBoatId(BOAT_ID);
		
		assertTrue(toPay.getId() == ID);
		assertAll(() -> assertTrue(toPay.getPartnerId() == PARTNER_ID),
					() -> assertTrue(toPay.getPrice() == PRICE),
					() -> assertTrue(toPay.isMembershipFee() == IS_MEMBERSHIP_FEE),
					() -> assertTrue(toPay.getBoatId() == BOAT_ID));
	}

}
