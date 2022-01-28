package test.client.managers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.client.managers.ClubStaffManager;
import application.client.managers.PartnerManager;

/**
 * This class tests the class PartnerManager.
 * 
 * @see application.client.managers.PartnerManager
 * 
 * @author Federico Canali
 *
 */
class PartnerManagerTest {

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void instanceTest() {
		PartnerManager partnerManager_1 = PartnerManager.getInstance();
		PartnerManager partnerManager_2 = PartnerManager.getInstance();
		
		assertTrue(partnerManager_1.equals(partnerManager_2));
	}
}
