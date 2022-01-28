package test.server.managers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.server.managers.ServerMessageManager;

/**
 * This class tests the class ServerMessageManager.
 * 
 * @see application.server.managers.ServerMessageManager
 * 
 * @author Federico Canali
 *
 */
class ServerMessageManagerTest {

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void istanceTest() {
		ServerMessageManager ServerMessageManager_1 = ServerMessageManager.getInstance();
		ServerMessageManager ServerMessageManager_2 = ServerMessageManager.getInstance();
		
		assertTrue(ServerMessageManager_1.equals(ServerMessageManager_2));
	}

}
