package test.server.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.models.ClubStaff;
import application.models.Partner;
import application.models.Person;
import application.server.managers.PersonManager;

/**
 * This class tests the class PersonManager.
 * 
 * @see application.server.managers.PersonManager
 * 
 * @author Federico Canali
 *
 */
class PersonManagerTest {
	
	private static final List<Person> PEOPLE = new ArrayList<Person>(Arrays.asList(new Partner("f", "s", "a", "r", null)
			, new ClubStaff("c", "s", "a", "r", null)));

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void instanceTest() {
		PersonManager personManager_1 = PersonManager.getInstance();
		PersonManager personManager_2 = PersonManager.getInstance();
		
		assertTrue(personManager_1.equals(personManager_2));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		PersonManager.getInstance().setPeople(PEOPLE);
		
		assertTrue(PersonManager.getInstance().getPeople().equals(PEOPLE));
	}
}
