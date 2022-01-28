package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.Credentials;
import application.models.Partner;
import application.models.Person;

/**
 * This class tests the class Person.
 * 
 * @see application.models.Person
 * 
 * @author Federico Canali
 *
 */
class PersonTest {
	
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
		Person person1 = new Person(NAME, SURNAME, ADDRESS, FISCAL_CODE, CREDENTIALS);
		assertTrue(person1.getName() == NAME);
		assertAll(() -> assertTrue(person1.getSurname().equals(SURNAME)),
				() -> assertTrue(person1.getAddress() == ADDRESS),
				() -> assertTrue(person1.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(person1.getCredentials().equals(CREDENTIALS)));
		
		Person person2 = new Partner(ID, NAME, SURNAME, ADDRESS, FISCAL_CODE, CREDENTIALS);
		assertTrue(person2.getId() == ID);
		assertAll(() -> assertTrue(person2.getName() == NAME),
				() -> assertTrue(person2.getSurname().equals(SURNAME)),
				() -> assertTrue(person2.getAddress() == ADDRESS),
				() -> assertTrue(person2.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(person2.getCredentials().equals(CREDENTIALS)));
		
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		Person person = new Partner();
		person.setId(ID);
		person.setName(NAME);
		person.setSurname(SURNAME);
		person.setFiscalCode(FISCAL_CODE);
		person.setAddress(ADDRESS);
		person.setCredentials(CREDENTIALS);
		
		assertTrue(person.getId() == ID);
		assertAll(() -> assertTrue(person.getName() == NAME),
				() -> assertTrue(person.getSurname().equals(SURNAME)),
				() -> assertTrue(person.getAddress() == ADDRESS),
				() -> assertTrue(person.getFiscalCode() == FISCAL_CODE),
				() -> assertTrue(person.getCredentials().equals(CREDENTIALS)));
	}

}
