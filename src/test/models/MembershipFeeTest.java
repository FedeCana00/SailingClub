package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import application.models.MembershipFee;
import application.models.PaymentMethod;

/**
 * This class tests the class MembershipFee.
 * 
 * @see application.models.MembershipFee
 * 
 * @author Federico Canali
 *
 */
class MembershipFeeTest {
	
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
		MembershipFee membershipFee1 = new MembershipFee(PARTNER_ID, PRICE, DATE, PAYMENT_METHOD);
		assertTrue(membershipFee1.getPartnerId() == PARTNER_ID);
		assertAll(() -> assertTrue(membershipFee1.getPrice() == PRICE),
				() -> assertTrue(membershipFee1.getDate().equals(DATE)),
				() -> assertTrue(membershipFee1.getPaymentMethod().equals(PAYMENT_METHOD)));
		
		MembershipFee membershipFee2 = new MembershipFee(ID, PARTNER_ID, PRICE, DATE, PAYMENT_METHOD);
		assertTrue(membershipFee2.getId() == ID);
		assertAll(() -> assertTrue(membershipFee2.getPartnerId() == PARTNER_ID),
					() -> assertTrue(membershipFee2.getPrice() == PRICE),
					() -> assertTrue(membershipFee2.getDate().equals(DATE)),
					() -> assertTrue(membershipFee2.getPaymentMethod().equals(PAYMENT_METHOD)));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		MembershipFee membershipFee = new MembershipFee();
		membershipFee.setId(ID);
		membershipFee.setPartnerId(PARTNER_ID);
		membershipFee.setDate(DATE);
		membershipFee.setPrice(PRICE);
		membershipFee.setPaymentMethod(PAYMENT_METHOD);
		
		assertTrue(membershipFee.getId() == ID);
		assertAll(() -> assertTrue(membershipFee.getPartnerId() == PARTNER_ID),
				() -> assertTrue(membershipFee.getPrice() == PRICE),
				() -> assertTrue(membershipFee.getDate().equals(DATE)),
				() -> assertTrue(membershipFee.getPaymentMethod().equals(PAYMENT_METHOD)));
	}

}
