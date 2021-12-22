/**
 * 
 */
package application.models;

import java.sql.Date;

/**
 * @author Federico Canali
 *
 */
public class WireTransfer extends PaymentMethod{
	private static final long serialVersionUID = 1L;
	private Date emissionDate;
	private String referenceCode;

	/**
	 * @param ownerName
	 * @param ownerSurname
	 * @param emissionDate
	 * @param referenceCode
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
	
	@Override
	public String toString() {
		return "[ emission date = " + emissionDate + " , reference code = "
				+ referenceCode + "]";
	}
}
