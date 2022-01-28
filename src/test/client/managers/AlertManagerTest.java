package test.client.managers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.client.managers.AlertManager;

/**
 * This class tests the class AlertManager.
 * 
 * @see application.client.managers.AlertManager
 * 
 * @author Federico Canali
 *
 */
class AlertManagerTest {

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void istanceTest() {
		AlertManager alertManager_1 = AlertManager.getInstance();
		AlertManager alertManager_2 = AlertManager.getInstance();
		
		assertTrue(alertManager_1.equals(alertManager_2));
	}

}
