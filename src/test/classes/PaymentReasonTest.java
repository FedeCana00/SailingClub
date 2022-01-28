package test.classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.classes.PaymentReason;
import application.models.MembershipFee;
import application.models.Payment;
import application.models.StorageFee;
import application.models.SubscriptionFee;

/**
 * This class tests the class PaymentReason.
 * 
 * @see application.classes.PaymentReason
 * 
 * @author Federico Canali
 *
 */
class PaymentReasonTest {
	
	private static final Payment SUBSRIPTION_FEE = new SubscriptionFee();
	private static final Payment MEMBERSHIP_FEE = new MembershipFee();
	private static final Payment STORAGE_FEE = new StorageFee();

	/**
	 * This method tests the method @see application.classes.PaymentReason#getValue(Payment).
	 * Test the different outputs based on all possible outputs.
	 */
	@Test
	final void methodGetValueTest() {
		
		assertTrue(PaymentReason.getValue(MEMBERSHIP_FEE).equals(" values (?, ?, ?, ?, ?)"));
		
		assertTrue(PaymentReason.getValue(SUBSRIPTION_FEE).equals(" values (?, ?, ?, ?, ?, ?, ?)"));
		
		assertTrue(PaymentReason.getValue(STORAGE_FEE).equals(" values (?, ?, ?, ?, ?, ?)"));
		
		assertTrue(PaymentReason.getValue(new Payment()) == null);
	}
	
	/**
	 * This method tests the method @see application.classes.PaymentReason#getTableName(Payment).
	 * Test the different outputs based on all possible outputs.
	 */
	@Test
	final void methodGetTableNameTest() {
		
		assertTrue(PaymentReason.getTableName(MEMBERSHIP_FEE).equals(PaymentReason.TABLE_MEMBERSHIP_FEE));
		
		assertTrue(PaymentReason.getTableName(SUBSRIPTION_FEE).equals(PaymentReason.TABLE_SUBSCRIPTION_FEE));
		
		assertTrue(PaymentReason.getTableName(STORAGE_FEE).equals(PaymentReason.TABLE_STORAGE_FEE));
		
		assertTrue(PaymentReason.getTableName(new Payment()) == null);
	}

}
