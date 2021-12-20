/**
 * 
 */
package application.models;

import java.io.Serializable;

/**
 * @author Federico Canali
 *
 */
public class ToPay implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int partnerId;
	private float price;
	private int boatId;
	private boolean isMembershipFee;
	
	/**
	 * @param id
	 * @param partnerId
	 * @param price
	 * @param isMembershipFee
	 * @param boatId
	 */
	public ToPay(int id, int partnerId, float price, boolean isMembershipFee, int boatId) {
		super();
		this.id = id;
		this.partnerId = partnerId;
		this.price = price;
		this.isMembershipFee = isMembershipFee;
		this.boatId = boatId;
	}

	/**
	 * @param partnerId
	 * @param price
	 * @param isMembershipFee
	 * @param boatId
	 */
	public ToPay(int partnerId, float price, boolean isMembershipFee, int boatId) {
		super();
		this.partnerId = partnerId;
		this.price = price;
		this.isMembershipFee = isMembershipFee;
		this.boatId = boatId;
	}
	
	/**
	 * @param id
	 * @param partnerId
	 * @param price
	 * @param isMembershipFee
	 */
	public ToPay(int id, int partnerId, float price, boolean isMembershipFee) {
		super();
		this.id = id;
		this.partnerId = partnerId;
		this.price = price;
		this.isMembershipFee = isMembershipFee;
	}

	/**
	 * @param partnerId
	 * @param price
	 * @param isMembershipFee
	 */
	public ToPay(int partnerId, float price, boolean isMembershipFee) {
		super();
		this.partnerId = partnerId;
		this.price = price;
		this.isMembershipFee = isMembershipFee;
	}
	
	/**
	 * Default constructor. 
	 **/
	public ToPay() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the partnerId
	 */
	public int getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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

	/**
	 * @return the isMembershipFee
	 */
	public boolean isMembershipFee() {
		return isMembershipFee;
	}

	/**
	 * @param isMembershipFee the isMembershipFee to set
	 */
	public void setMembershipFee(boolean isMembershipFee) {
		this.isMembershipFee = isMembershipFee;
	}
	
	@Override
	public String toString() {
		return "[ id = " + id + " , price = " + price 
				+ " , isMembershipFee = " + isMembershipFee
				+ " , partnerId = " + partnerId + "]";
	}
}
