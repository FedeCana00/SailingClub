package application.models;

import java.io.Serializable;

/**
 * This class represents the payment that has yet to be paid.
 * 
 * @author Federico Canali
 *
 */
public class ToPay implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the to pay.
	 */
	private int id;
	/**
	 * Represents the id of partner.
	 */
	private int partnerId;
	/**
	 * Represents the price to pay.
	 */
	private float price;
	/**
	 * Represents the id of the boat.
	 */
	private int boatId;
	/**
	 * Represents if it is membership fee or storage fee.
	 */
	private boolean isMembershipFee;
	
	/**
	 * @param id is the id of the to pay.
	 * @param partnerId is the id of partner.
	 * @param price is the price to pay.
	 * @param isMembershipFee check if it is membership fee or storage fee.
	 * @param boatId is the id of the boat.
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
	 * @param partnerId is the id of partner.
	 * @param price is the price to pay.
	 * @param isMembershipFee check if it is membership fee or storage fee.
	 * @param boatId is the id of the boat.
	 */
	public ToPay(int partnerId, float price, boolean isMembershipFee, int boatId) {
		super();
		this.partnerId = partnerId;
		this.price = price;
		this.isMembershipFee = isMembershipFee;
		this.boatId = boatId;
	}
	
	/**
	 * @param id is the id of the to pay.
	 * @param partnerId is the id of partner.
	 * @param price is the price to pay.
	 * @param isMembershipFee check if it is membership fee or storage fee.
	 */
	public ToPay(int id, int partnerId, float price, boolean isMembershipFee) {
		super();
		this.id = id;
		this.partnerId = partnerId;
		this.price = price;
		this.isMembershipFee = isMembershipFee;
	}

	/**
	 * @param partnerId is the id of partner.
	 * @param price is the price to pay.
	 * @param isMembershipFee check if it is membership fee or storage fee.
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
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ id = " + id + " , price = " + price 
				+ " , isMembershipFee = " + isMembershipFee
				+ " , partnerId = " + partnerId + "]";
	}
}
