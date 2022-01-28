package test.client.managers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.client.managers.PaymentsManager;
import application.client.managers.ViewManager;

/**
 * This class tests the class ViewManager.
 * 
 * @see application.client.managers.ViewManager
 * 
 * @author Federico Canali
 *
 */
class ViewManagerTest {

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void instanceTest() {
		ViewManager viewManager_1 = ViewManager.getInstance();
		ViewManager viewManager_2 = ViewManager.getInstance();
		
		assertTrue(viewManager_1.equals(viewManager_2));
	}

}
