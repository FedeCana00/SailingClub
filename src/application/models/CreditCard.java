package application.models;

/**
 * This class represents payment by credit card.
 * 
 * @author Federico Canali
 *
 */
public class CreditCard extends PaymentMethod{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the cvv of the card. 
	 */
	private int cvv;
	/**
	 * Represents the number of the card.
	 */
	private long cardNumber;
	/**
	 * Represents the expiration date of the card. 
	 */
	private String expirationDate;
	
	/**
	 * Default constructor. 
	 */
	public CreditCard() {
		super();
	}
	
	/**
	 * @param ownerName is the name of the card owner.
	 * @param ownerSurname is the surname of the card owner.
	 * @param cvv is the cvv of the card.
	 * @param cardNumber is the number of the card.
	 * @param expirationDate is the expiration date of the card.
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
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ cvv = " + cvv + ", card number = " + cardNumber
				+ ", expiration date = " + expirationDate + "]";
	}
}
