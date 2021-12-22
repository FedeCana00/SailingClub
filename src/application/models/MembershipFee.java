package application.models;

import java.sql.Date;

/**
 * @author Federico Canali
 *
 */
public class MembershipFee extends Payment{
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MembershipFee() {
		super();
	}

	/**
	 * @param partnerId
	 * @param price
	 * @param date
	 * @param paymentMethod
	 */
	public MembershipFee(int partnerId, float price, Date date, PaymentMethod paymentMethod) {
		super(partnerId, price, date, paymentMethod);
	}

	/**
	 * @param id
	 * @param partnerId
	 * @param price
	 * @param date
	 * @param paymentMethod
	 * @param idM
	 */
	public MembershipFee(int id, int partnerId, float price, Date date, PaymentMethod paymentMethod) {
		super(id, partnerId, price, date, paymentMethod);
	}
}
