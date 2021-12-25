package application.models;

import java.sql.Date;

/**
 *  This class represents payment by wire transfer.
 * 
 * @author Federico Canali
 *
 */
public class WireTransfer extends PaymentMethod{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the emission date of wire transfer.
	 */
	private Date emissionDate;
	/**
	 * Represents the reference code of wire transfer.
	 */
	private String referenceCode;
	
	/**
	 * Default constructor. 
	 */
	public WireTransfer() {}

	/**
	 * @param ownerName is the owner name of wire transfer.
	 * @param ownerSurname is the owner surname of wire transfer.
	 * @param emissionDate is the emission date of wire transfer.
	 * @param referenceCode is the reference code of wire transfer.
	 */
	public WireTransfer(String ownerName, String ownerSurname, Date emissionDate, String referenceCode) {
		super(ownerName, ownerSurname);
		this.emissionDate = emissionDate;
		this.referenceCode = referenceCode;
	}

	/**
	 * @return the emissionDate
	 */
	public Date getEmissionDate() {
		return emissionDate;
	}

	/**
	 * @param emissionDate the emissionDate to set
	 */
	public void setEmissionDate(Date emissionDate) {
		this.emissionDate = emissionDate;
	}

	/**
	 * @return the referenceCode
	 */
	public String getReferenceCode() {
		return referenceCode;
	}

	/**
	 * @param referenceCode the referenceCode to set
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ emission date = " + emissionDate + " , reference code = "
				+ referenceCode + "]";
	}
}
