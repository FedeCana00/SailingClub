package test.client.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.client.managers.ClubStaffManager;
import application.models.Partner;
import application.models.Race;

/**
 * This class tests the class ClubStaffManager.
 * 
 * @see application.client.managers.ClubStaffManager
 * 
 * @author Federico Canali
 *
 */
class ClubStaffManagerTest {
	
	private static final List<Partner> PARTNERS = new ArrayList<Partner>(Arrays.asList(new Partner("n", "s", "ad", "fiscal", null), new Partner()));
	private static final List<Race> RACES = new ArrayList<Race>(Arrays.asList(new Race(), new Race("race", null, 123)));

	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void instanceTest() {
		ClubStaffManager clubStaffManager_1 = ClubStaffManager.getInstance();
		ClubStaffManager clubStaffManager_2 = ClubStaffManager.getInstance();
		
		assertTrue(clubStaffManager_1.equals(clubStaffManager_2));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		ClubStaffManager.getInstance().setPartners(PARTNERS);
		
		assertTrue(ClubStaffManager.getInstance().getPartners().equals(PARTNERS));
		
		ClubStaffManager.getInstance().setRaces(RACES);
		
		assertTrue(ClubStaffManager.getInstance().getRaces().equals(RACES));
	}

}
