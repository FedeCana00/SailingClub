package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.models.Boat;
import application.models.Credentials;
import application.models.Partner;

/**
 * This class tests the class Partner.
 * 
 * @see application.models.Partner
 * 
 * @author Federico Canali
 *
 */
class PartnerTest {

	private static final int ID = 12;
	private static final String NAME = "Federico";
	private static final String SURNAME = "Canali";
	private static final String ADDRESS = "Address";
	private static final String FISCAL_CODE = "DFJAO12KIDHN3";
	private static final Credentials CREDENTIALS = new Credentials("Username", "Password");
	private static final List<Boat> BOATS = new ArrayList<Boat>(Arrays.asList(new Boat("Boat", 1234)));
	
	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		Partner partner1 = new Partner(NAME, SURNAME, ADDRESS, FISCAL_CODE, CREDENTIALS);
		assertTrue(partner1.getName() == NAME);
		assertAll(() -> assertTrue(partner1.getSurname().equals(SURNAME)),
				() -> assertTrue(partner1.getAddress() == ADDRESS),
				() -> assertTrue(partner1.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(partner1.getCredentials().equals(CREDENTIALS)));
		
		Partner partner2 = new Partner(ID, NAME, SURNAME, ADDRESS, FISCAL_CODE, CREDENTIALS);
		assertTrue(partner2.getId() == ID);
		assertAll(() -> assertTrue(partner2.getName() == NAME),
				() -> assertTrue(partner2.getSurname().equals(SURNAME)),
				() -> assertTrue(partner2.getAddress() == ADDRESS),
				() -> assertTrue(partner2.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(partner2.getCredentials().equals(CREDENTIALS)));
		
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		Partner partner = new Partner();
		partner.setId(ID);
		partner.setName(NAME);
		partner.setSurname(SURNAME);
		partner.setFiscalCode(FISCAL_CODE);
		partner.setAddress(ADDRESS);
		partner.setCredentials(CREDENTIALS);
		partner.setBoats(BOATS);
		
		assertTrue(partner.getId() == ID);
		assertAll(() -> assertTrue(partner.getName() == NAME),
				() -> assertTrue(partner.getSurname().equals(SURNAME)),
				() -> assertTrue(partner.getAddress() == ADDRESS),
				() -> assertTrue(partner.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(partner.getCredentials().equals(CREDENTIALS)),
				() -> assertTrue(partner.getBoats().equals(BOATS)));
	}

}
