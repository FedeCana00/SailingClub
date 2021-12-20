/**
 * 
 */
package application.models;

import java.sql.Date;

/**
 * @author Federico Canali
 *
 */
public class SubscriptionFee extends Payment{
	private static final long serialVersionUID = 1L;
	private int raceId;
	private int boatId;
	
	/**
	 * 
	 */
	public SubscriptionFee() {
		super();
	}

	/**
	 * @param price
	 * @param date
	 * @param paymentMethod
	 * @param raceId
	 * @param boatId
	 */
	public SubscriptionFee(int partnerId, float price, Date date, PaymentMethod paymentMethod
			, int raceId, int boatId) {
		super(partnerId, price, date, paymentMethod);
		this.raceId = raceId;
		this.boatId = boatId;
	}

	/**
	 * @param id
	 * @param partnerId
	 * @param price
	 * @param date
	 * @param paymentMethod
	 * @param idS
	 * @param raceId
	 * @param boatId
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
