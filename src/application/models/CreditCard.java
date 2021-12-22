/**
 * 
 */
package application.models;

/**
 * @author Federico Canali
 *
 */
public class CreditCard extends PaymentMethod{
	private static final long serialVersionUID = 1L;
	private int cvv;
	private long cardNumber;
	private String expirationDate;
	/**
	 * @param ownerName
	 * @param ownerSurname
	 * @param cvv
	 * @param cardNumber
	 * @param expirationDate
	 */
	public CreditCard(String ownerName, String ownerSurname, int cvv, long cardNumber, String expirationDate) {
		super(ownerName, ownerSurname);
		this.cvv = cvv;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
	}
	
	/**
	 * @return the cvv
	 */
	public int getCvv() {
		return cvv;
	}
	/**
	 * @param cvv the cvv to set
	 */
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	/**
	 * @return the cardNumber
	 */
	public long getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "[ cvv = " + cvv + ", card number = " + cardNumber
				+ ", expiration date = " + expirationDate + "]";
	}
}
