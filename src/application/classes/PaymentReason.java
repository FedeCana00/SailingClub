/**
 * 
 */
package application.classes;

import application.models.MembershipFee;
import application.models.Payment;
import application.models.StorageFee;
import application.models.SubscriptionFee;

/**
 * @author Federico Canali
 *
 */
public class PaymentReason {
	/* CONSTANTS */
	public static final String MEMBERSHIP_FEE = "Membership Fee";
	public static final String STORAGE_FEE = "Storage Fee";
	public static final String SUBSCRIPTION_FEE = "Subscription Fee";
	private static final String TABLE_SUBSCRIPTION_FEE = "subscriptionFee";
	private static final String TABLE_STORAGE_FEE = "storageFee";
	private static final String TABLE_MEMBERSHIP_FEE = "membershipFee";
	
	/**
	 * Used to return the right table in database.
	 * @return returns the name of the table in database. 
	 **/
	public static String getTableName(Payment payment) {
		if(payment instanceof SubscriptionFee)
			return TABLE_SUBSCRIPTION_FEE;
		else if(payment instanceof StorageFee)
			return TABLE_STORAGE_FEE;
		else if(payment instanceof MembershipFee)
			return TABLE_MEMBERSHIP_FEE;
		else {
			System.out.println("Error in instance of Payement...");
			return null;
		}
	}
	
	/**
	 * Used to return the value to insert into in database.
	 * @return returns the value of the table in database. 
	 **/
	public static String getValue(Payment payment) {
		String value = " values (?, ?, ?, ?, ?";
		if(payment instanceof SubscriptionFee)
			return value + ", ?, ?)";
		else if(payment instanceof StorageFee)
			return value + ", ?)";
		else if(payment instanceof MembershipFee)
			return value + ")";
		else {
			System.out.println("Error in instance of Payement...");
			return null;
		}
	}

}
