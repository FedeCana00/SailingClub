package application.models;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class represents the payments that take place through the sailing club.
 * 
 * @author Federico
 *
 */
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of payment.
	 */
	private int id;
	/**
	 * Represents the id of the partner.
	 */
	private int partnerId;
	/**
	 * Represents the price to pay.
	 */
	private float price;
	/**
	 * Represents the date of payment.
	 */
	private Date date;
	/**
	 * Represents the method of payment used.
	 */
	private PaymentMethod paymentMethod;
	
	/**
	 * Default constructor. 
	 **/
	public Payment() {}
	
	/**
	 * @param id is the id of payment.
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 */
	public Payment(int id, int partnerId, float price, Date date, PaymentMethod paymentMethod) {
		super();
		this.id = id;
		this.partnerId = partnerId;
		this.price = price;
		this.date = date;
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @param partnerId is the id of the partner.
	 * @param price is the price to pay.
	 * @param date is the date of payment.
	 * @param paymentMethod is the method of payment used.
	 */
	public Payment(int partnerId, float price, Date date, PaymentMethod paymentMethod) {
		super();
		this.partnerId = partnerId;
		this.price = price;
		this.date = date;
		this.paymentMethod = paymentMethod;
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
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ id = " + id + ", price =  " + price 
				+ " €, date = " + date.toString()
				+ ", payment method : " + paymentMethod.toString() + "]";
	}
}
