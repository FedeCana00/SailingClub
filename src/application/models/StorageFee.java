/**
 * 
 */
package application.models;

import java.sql.Date;

/**
 * @author Federico Canali
 *
 */
public class StorageFee extends Payment{
	private static final long serialVersionUID = 1L;
	private int boatId;
	
	/**
	 * 
	 */
	public StorageFee() {
		super();
	}

	/**
	 * @param partnerId
	 * @param price
	 * @param date
	 * @param paymentMethod
	 * @param boatId
	 */
	public StorageFee(int partnerId, float price, Date date, PaymentMethod paymentMethod, int boatId) {
		super(partnerId, price, date, paymentMethod);
		this.boatId = boatId;
	}

	/**
	 * @param id
	 * @param partnerId
	 * @param price
	 * @param date
	 * @param paymentMethod
	 * @param idS
	 * @param boatId
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
