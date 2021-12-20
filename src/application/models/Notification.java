/**
 * 
 */
package application.models;

import java.io.Serializable;

/**
 * @author Federico Canali
 *
 */
public class Notification implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String message;
	private int partnerId;
	private int toPayId;
	
	/**
	 * @param id
	 * @param message
	 * @param partnerId
	 * @param toPayId
	 */
	public Notification(int id, String message, int partnerId, int toPayId) {
		super();
		this.id = id;
		this.message = message;
		this.partnerId = partnerId;
		this.toPayId = toPayId;
	}

	/**
	 * @param message
	 * @param partnerId
	 * @param toPayId
	 */
	public Notification(String message, int partnerId, int toPayId) {
		super();
		this.message = message;
		this.partnerId = partnerId;
		this.toPayId = toPayId;
	}
	
	/**
	 * Default constructor. 
	 */
	public Notification() {}

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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return the toPayId
	 */
	public int getToPayId() {
		return toPayId;
	}

	/**
	 * @param toPayId the toPayId to set
	 */
	public void setToPayId(int toPayId) {
		this.toPayId = toPayId;
	}
	
	@Override
	public String toString() {
		return "[ id = " + id + " , message = " + message
				+ " , partnerId = " + partnerId 
				+ " , toPayId = " + toPayId + "]";
	}
}
