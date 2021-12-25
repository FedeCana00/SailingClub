package application.models;

import java.sql.Date;

/**
 * This class represents subscription fee of sailing club.
 * 
 * @author Federico Canali
 *
 */
public class SubscriptionFee extends Payment{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the race.
	 */
	private int raceId;
	/**
	 * Represents the id of the boat.
	 */
	private int boatId;
	
	/**
	 * Default constructor.
	 */
	public SubscriptionFee() {
		super();
	}

	/**
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 * @param raceId is the id of the race.
	 * @param boatId is the id of the boat.
	 */
	public SubscriptionFee(int partnerId, float price, Date date, PaymentMethod paymentMethod
			, int raceId, int boatId) {
		super(partnerId, price, date, paymentMethod);
		this.raceId = raceId;
		this.boatId = boatId;
	}

	/**
	 * @param id is the id of the subscription fee.
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 * @param raceId is the id of the race.
	 * @param boatId is the id of the boat.
	 */
	public SubscriptionFee(int id, int partnerId, float price, Date date, PaymentMethod paymentMethod
			, int raceId, int boatId) {
		super(id, partnerId, price, date, paymentMethod);
		this.raceId = raceId;
		this.boatId = boatId;
	}

	/**
	 * @return the raceId
	 */
	public int getRaceId() {
		return raceId;
	}
	
	/**
	 * @param raceId the raceId to set
	 */
	public void setRaceId(int raceId) {
		this.raceId = raceId;
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
