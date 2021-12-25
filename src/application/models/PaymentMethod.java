package application.models;

import java.io.Serializable;

/**
 *	This class represents the payment method used to pay a sailing club fee.
 *
 * @author Federico Canali
 */
public class PaymentMethod implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the payment method.
	 */
	private int id;
	/**
	 * Represents the name of owner.
	 */
	private String ownerName;
	/**
	 * Represents the surname of owner.
	 */
	private String ownerSurname;
	
	/**
	 * Default constructor. 
	 */
	public PaymentMethod() {}
	
	/**
	 * @param ownerName is the name of owner.
	 * @param ownerSurname is the surname of the owner.
	 */
	public PaymentMethod(String ownerName, String ownerSurname) {
		super();
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
	}

	
	/**
	 * @param id is the id of the payment method.
	 * @param ownerName is the name of owner.
	 * @param ownerSurname is the surname of the owner.
	 */
	public PaymentMethod(int id, String ownerName, String ownerSurname) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	/**
	 * @return the ownerSurname
	 */
	public String getOwnerSurname() {
		return ownerSurname;
	}
	/**
	 * @param ownerSurname the ownerSurname to set
	 */
	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname;
	}

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
}
