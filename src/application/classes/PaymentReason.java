package application.classes;

import application.models.MembershipFee;
import application.models.Payment;
import application.models.StorageFee;
import application.models.SubscriptionFee;

/**
 * PaymentReason class keeps constant information of the 
 * reasons why a payment is made.
 * 
 * It also has methods that return the name of table for payment ({@link PaymentReason#getTableName(Payment)})
 * and the value to be entered into a SQL query based on 
 * the payment ({@link PaymentReason#getValue(Payment)}).
 * 
 * @author Federico Canali
 */
public class PaymentReason {
	/* CONSTANTS */
	/**
	 * Represents String of membership fee.
	 **/
	public static final String MEMBERSHIP_FEE = "Membership Fee";
	/**
	 * Represents String of storage fee.
	 **/
	public static final String STORAGE_FEE = "Storage Fee";
	/**
	 * Represents String of subscription fee.
	 **/
	public static final String SUBSCRIPTION_FEE = "Subscription Fee";
	/**
	 * Represents String of subscription fee of the table name in the database.
	 **/
	private static final String TABLE_SUBSCRIPTION_FEE = "subscriptionFee";
	/**
	 * Represents String of storage fee of the table name in the database.
	 **/
	private static final String TABLE_STORAGE_FEE = "storageFee";
	/**
	 * Represents String of membership fee of the table name in the database.
	 **/
	private static final String TABLE_MEMBERSHIP_FEE = "membershipFee";
	
	/**
	 * Used to return the right table in database name.
	 * 
	 * @return Returns the name of the table in database.
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
	 * E.g. " values (?, ?, ?, ?, ?".
	 * 
	 * @return Returns the value of the table in database. 
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
