package test.server.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.models.Boat;
import application.server.managers.BoatsManager;

/**
 * This class tests the class BoatsManager.
 * 
 * @see application.server.managers.BoatsManager
 * 
 * @author Federico Canali
 *
 */
class BoatsManagerTest {
	
	private static final List<Boat> BOATS = new ArrayList<Boat>(Arrays.asList(new Boat("B1", 12), new Boat("B2", 1, 12)));

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void instanceTest() {
		BoatsManager boatsManager_1 = BoatsManager.getInstance();
		BoatsManager boatsManager_2 = BoatsManager.getInstance();
		
		assertTrue(boatsManager_1.equals(boatsManager_2));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		BoatsManager.getInstance().setBoats(BOATS);
		
		assertTrue(BoatsManager.getInstance().getBoats().equals(BOATS));
	}

}
