/**
 * 
 */
package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.models.Boat;
import application.models.Race;

/**
 * @author Federico Canali
 *
 */
class BoatTest {

	private static final int ID = 12;
	private static final String NAME = "Boat";
	private static final float LENGTH = 130;
	private static final int PARTNER_ID = 2;
	private static final List<Race> RACES_REGISTERED 
		= new ArrayList<Race>(Arrays.asList(new Race("Race", null, 12)));
	
	/**
	 * Test all constructor of the class. 
	 */
	@Test
	public void constructorTest() {
		Boat boat1 = new Boat(NAME, LENGTH);
		assertTrue(boat1.getName() == NAME);
		assertTrue(boat1.getLength() == LENGTH);
		
		Boat boat2 = new Boat(NAME, LENGTH, PARTNER_ID);
		assertTrue(boat2.getName() == NAME);
		assertAll(() -> assertTrue(boat2.getLength() == LENGTH), 
					() -> assertTrue(boat2.getPartnerId() == PARTNER_ID));
		
		Boat boat3 = new Boat(ID, NAME, LENGTH, PARTNER_ID);
		assertTrue(boat3.getId() == ID);
		assertAll(() -> assertTrue(boat3.getName() == NAME),
				() -> assertTrue(boat3.getLength() == LENGTH), 
				() -> assertTrue(boat3.getPartnerId() == PARTNER_ID));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	public void setterTest() {
		Boat boat = new Boat();
		boat.setId(ID);
		boat.setName(NAME);
		boat.setLength(LENGTH);
		boat.setPartnerId(PARTNER_ID);
		boat.setRacesRegistered(RACES_REGISTERED);
		
		assertAll(() -> assertTrue(boat.getId() == ID),
				() -> assertTrue(boat.getName() == NAME),
				() -> assertTrue(boat.getLength() == LENGTH), 
				() -> assertTrue(boat.getPartnerId() == PARTNER_ID),
				() -> assertTrue(boat.getRacesRegistered().equals(RACES_REGISTERED)));
	}

}
