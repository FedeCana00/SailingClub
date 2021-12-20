/**
 * 
 */
package application.models;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Federico
 *
 */
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int partnerId;
	private float price;
	private Date date;
	private PaymentMethod paymentMethod;
	
	/**
	 * Default constructor. 
	 **/
	public Payment() {}
	
	/**
	 * @param id
	 * @param partnerId
	 * @param price
	 * @param date
	 * @param paymentMethod
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
	 * @param partnerId
	 * @param price
	 * @param date
	 * @param paymentMethod
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
	
	@Override
	public String toString() {
		return "[ id = " + id + ", price =  " + price 
				+ " €, date = " + date.toString()
				+ ", payment method : " + paymentMethod.toString() + "]";
	}
}
