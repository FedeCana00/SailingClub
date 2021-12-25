package application.models;

import java.sql.Date;

/**
 * This class represents membership fee of sailing club.
 * 
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
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 */
	public MembershipFee(int partnerId, float price, Date date, PaymentMethod paymentMethod) {
		super(partnerId, price, date, paymentMethod);
	}

	/**
	 * @param id is the id of the payment.
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 */
	public MembershipFee(int id, int partnerId, float price, Date date, PaymentMethod paymentMethod) {
		super(id, partnerId, price, date, paymentMethod);
	}
}
