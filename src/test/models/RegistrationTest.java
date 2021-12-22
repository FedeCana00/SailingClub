/**
 * 
 */
package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.Boat;
import application.models.Race;
import application.models.Registration;

/**
 * @author Federico Canali
 *
 */
class RegistrationTest {
	
	private static final int ID = 12;
	private static final Race RACE = new Race("Race", null, 500);
	private static final Boat BOAT = new Boat("Boat", 123);

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		Registration registration1 = new Registration(RACE, BOAT);
		assertTrue(registration1.getRace().equals(RACE));
		assertTrue(registration1.getBoat().equals(BOAT));
		
		Registration registration2 = new Registration(ID, RACE, BOAT);
		assertTrue(registration2.getId() == ID);
		assertAll(() -> assertTrue(registration2.getRace() == RACE),
					() -> assertTrue(registration2.getBoat() == BOAT));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		Registration registration = new Registration();
		registration.setId(ID);
		registration.setBoat(BOAT);
		registration.setRace(RACE);
		
		assertTrue(registration.getId() == ID);
		assertAll(() -> assertTrue(registration.getRace() == RACE),
					() -> assertTrue(registration.getBoat() == BOAT));
	}

}
