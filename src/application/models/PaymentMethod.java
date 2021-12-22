package application.models;

import java.io.Serializable;

public class PaymentMethod implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String ownerName;
	private String ownerSurname;
	
	/**
	 * Default constructor. 
	 */
	public PaymentMethod() {}
	
	/**
	 * @param ownerName
	 * @param ownerSurname
	 */
	public PaymentMethod(String ownerName, String ownerSurname) {
		super();
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
	}

	
	/**
	 * @param id
	 * @param ownerName
	 * @param ownerSurname
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
