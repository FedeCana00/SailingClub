package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import application.models.WireTransfer;

/**
 * This class tests the class WireTransfer.
 * 
 * @see application.models.WireTransfer
 * 
 * @author Federico Canali
 *
 */
class WireTransferTest {

	private static final int ID = 12;
	private static final String OWNER_NAME = "Federico";
	private static final String OWNER_SURNAME = "Canali";
	private static final Date EMISSION_DATE = new Date(1234566);
	private static final String REFERENCE_CODE = "1234352";

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		WireTransfer wireTransfer1 = new WireTransfer(OWNER_NAME, OWNER_SURNAME, EMISSION_DATE, REFERENCE_CODE);
		assertTrue(wireTransfer1.getOwnerName() == OWNER_NAME);
		assertAll(() -> assertTrue(wireTransfer1.getOwnerSurname() == OWNER_SURNAME),
					() -> assertTrue(wireTransfer1.getEmissionDate().equals(EMISSION_DATE)),
					() -> assertTrue(wireTransfer1.getReferenceCode() == REFERENCE_CODE));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		WireTransfer wireTransfer = new WireTransfer();
		wireTransfer.setId(ID);
		wireTransfer.setOwnerName(OWNER_NAME);
		wireTransfer.setOwnerSurname(OWNER_SURNAME);
		wireTransfer.setEmissionDate(EMISSION_DATE);
		wireTransfer.setReferenceCode(REFERENCE_CODE);
		
		assertTrue(wireTransfer.getId() == ID);
		assertAll(() -> assertTrue(wireTransfer.getOwnerName() == OWNER_NAME),
					() -> assertTrue(wireTransfer.getOwnerSurname() == OWNER_SURNAME),
					() -> assertTrue(wireTransfer.getEmissionDate().equals(EMISSION_DATE)),
					() -> assertTrue(wireTransfer.getReferenceCode() == REFERENCE_CODE));
	}
}
