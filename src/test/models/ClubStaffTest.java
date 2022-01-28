package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.ClubStaff;
import application.models.Credentials;

/**
 * This class tests the class ClubStaff.
 * 
 * @see application.models.ClubStaff
 * 
 * @author Federico Canali
 *
 */
class ClubStaffTest {
	
	private static final int ID = 12;
	private static final String NAME = "Federico";
	private static final String SURNAME = "Canali";
	private static final String ADDRESS = "Address";
	private static final String FISCAL_CODE = "DFJAO12KIDHN3";
	private static final Credentials CREDENTIALS = new Credentials("Username", "Password");
	
	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		ClubStaff clubStaff1 = new ClubStaff(NAME, SURNAME, ADDRESS, FISCAL_CODE, CREDENTIALS);
		assertTrue(clubStaff1.getName() == NAME);
		assertAll(() -> assertTrue(clubStaff1.getSurname().equals(SURNAME)),
				() -> assertTrue(clubStaff1.getAddress() == ADDRESS),
				() -> assertTrue(clubStaff1.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(clubStaff1.getCredentials().equals(CREDENTIALS)));
		
		ClubStaff clubStaff2 = new ClubStaff(ID, NAME, SURNAME, ADDRESS, FISCAL_CODE, CREDENTIALS);
		assertTrue(clubStaff2.getId() == ID);
		assertAll(() -> assertTrue(clubStaff2.getName() == NAME),
				() -> assertTrue(clubStaff2.getSurname().equals(SURNAME)),
				() -> assertTrue(clubStaff2.getAddress() == ADDRESS),
				() -> assertTrue(clubStaff2.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(clubStaff2.getCredentials().equals(CREDENTIALS)));
		
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		ClubStaff clubStaff = new ClubStaff();
		clubStaff.setId(ID);
		clubStaff.setName(NAME);
		clubStaff.setSurname(SURNAME);
		clubStaff.setFiscalCode(FISCAL_CODE);
		clubStaff.setAddress(ADDRESS);
		clubStaff.setCredentials(CREDENTIALS);
		
		assertTrue(clubStaff.getId() == ID);
		assertAll(() -> assertTrue(clubStaff.getName() == NAME),
				() -> assertTrue(clubStaff.getSurname().equals(SURNAME)),
				() -> assertTrue(clubStaff.getAddress() == ADDRESS),
				() -> assertTrue(clubStaff.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(clubStaff.getCredentials().equals(CREDENTIALS)));
	}

}
