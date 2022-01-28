package test.server.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.models.Race;
import application.server.managers.RacesManager;

/**
 * This class tests the class RacesManager.
 * 
 * @see application.server.managers.RacesManager
 * 
 * @author Federico Canali
 *
 */
class RacesManagerTest {

	private static final List<Race> RACES = new ArrayList<Race>(Arrays.asList(new Race("race1", null, 123)
			, new Race("race2", null, 999)));

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void istanceTest() {
		RacesManager racesManager_1 = RacesManager.getInstance();
		RacesManager racesManager_2 = RacesManager.getInstance();
		
		assertTrue(racesManager_1.equals(racesManager_2));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		RacesManager.getInstance().setRaces(RACES);
		
		assertTrue(RacesManager.getInstance().getRaces().equals(RACES));
	}

}
