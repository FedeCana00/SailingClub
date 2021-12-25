package application.models;

import java.io.Serializable;

/**
 * This class represents the notifications a partner receives.
 * 
 * @author Federico Canali
 *
 */
public class Notification implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the notification.
	 */
	private int id;
	/**
	 * Represents the message of the notification.
	 */
	private String message;
	/**
	 * Represents the id of the partner of the notification.
	 */
	private int partnerId;
	/**
	 * Represents the id of the payment to pay.
	 */
	private int toPayId;
	
	/**
	 * @param id is the id of the notification.
	 * @param message is the message of the notification.
	 * @param partnerId is the id of the partner of the notification.
	 * @param toPayId is the id of the payment to pay.
	 */
	public Notification(int id, String message, int partnerId, int toPayId) {
		super();
		this.id = id;
		this.message = message;
		this.partnerId = partnerId;
		this.toPayId = toPayId;
	}

	/**
	 * @param message is the message of the notification.
	 * @param partnerId is the id of the partner of the notification.
	 * @param toPayId is the id of the payment to pay.
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
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ id = " + id + " , message = " + message
				+ " , partnerId = " + partnerId 
				+ " , toPayId = " + toPayId + "]";
	}
}
