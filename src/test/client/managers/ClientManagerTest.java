package test.client.managers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.client.managers.ClientManager;
import application.server.managers.BoatsManager;

/**
 * This class tests the class ClientManager.
 * 
 * @see application.client.managers.ClientManager.
 * 
 * @author Federico Canali
 *
 */
class ClientManagerTest {

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void istanceTest() {
		ClientManager clientManager_1 = ClientManager.getInstance();
		ClientManager clientManager_2 = ClientManager.getInstance();
		
		assertTrue(clientManager_1.equals(clientManager_2));
	}

}
