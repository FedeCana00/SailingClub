package test.server.managers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.server.managers.PaymentsManager;

/**
 * This class tests the class PaymentsManager.
 * 
 * @see application.server.managers.PaymentsManager
 * 
 * @author Federico Canali
 *
 */
class PaymentsManagerTest {

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void instanceTest() {
		PaymentsManager paymentsManager_1 = PaymentsManager.getInstance();
		PaymentsManager paymentsManager_2 = PaymentsManager.getInstance();
		
		assertTrue(paymentsManager_1.equals(paymentsManager_2));
	}

}
