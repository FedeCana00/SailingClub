package application.models;

import java.sql.Date;

/**
 * This class represents storage fee of sailing club.
 * 
 * @author Federico Canali
 *
 */
public class StorageFee extends Payment{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the boat.
	 */
	private int boatId;
	
	/**
	 * Default constructor.
	 */
	public StorageFee() {
		super();
	}

	/**
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 * @param boatId is the id of the boat.
	 */
	public StorageFee(int partnerId, float price, Date date, PaymentMethod paymentMethod, int boatId) {
		super(partnerId, price, date, paymentMethod);
		this.boatId = boatId;
	}

	/**
	 * @param id is the id of storage fee.
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 * @param boatId is the id of the boat.
	 */
	public StorageFee(int id, int partnerId, float price, Date date, PaymentMethod paymentMethod
			, int boatId) {
		super(id, partnerId, price, date, paymentMethod);
		this.boatId = boatId;
	}

	/**
	 * @return the boatId
	 */
	public int getBoatId() {
		return boatId;
	}

	/**
	 * @param boatId the boatId to set
	 */
	public void setBoatId(int boatId) {
		this.boatId = boatId;
	}
}
