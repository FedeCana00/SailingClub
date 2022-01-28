package test.client.managers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.client.managers.UserManager;
import application.models.Partner;
import application.models.Person;

/**
 * This class tests the class UserManager.
 * 
 * @see application.client.managers.UserManager
 * 
 * @author Federico Canali
 *
 */
class UserManagerTest {
	
	private static final Person PERSON = new Partner("name", "surname", "address", "fiscalCode", null);

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void instanceTest() {
		UserManager userManager_1 = UserManager.getInstance();
		UserManager userManager_2 = UserManager.getInstance();
		
		assertTrue(userManager_1.equals(userManager_2));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		UserManager.getInstance().setPerson(PERSON);
		
		assertTrue(UserManager.getInstance().getPerson().equals(PERSON));
	}

}
