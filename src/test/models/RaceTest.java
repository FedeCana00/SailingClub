package test.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.models.Boat;
import application.models.Race;

/**
 * This class tests the class Race.
 * 
 * @see application.models.Race
 * 
 * @author Federico Canali
 *
 */
class RaceTest {
	
	private static final String NAME = "Race";
	private static final int ID = 2;
	private static final Date DATE = new Date(1222221214);
	private static final float SUBSCRIPTION_PRICE = 200;
	private static final List<Boat> BOATS = 
			new ArrayList<Boat>(Arrays.asList(new Boat("Boat", 123)));
	
	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		Race race1 = new Race(NAME, DATE, SUBSCRIPTION_PRICE);
		assertTrue(race1.getName() == NAME);
		assertAll(() -> assertTrue(race1.getDate().equals(DATE)),
				() -> assertTrue(race1.getSubscriptionPrice() == SUBSCRIPTION_PRICE));
	
		Race race2 = new Race(ID, NAME, DATE, SUBSCRIPTION_PRICE);
		assertTrue(race2.getId() == ID);
		assertAll(() -> assertTrue(race1.getName() == NAME),
				() -> assertTrue(race1.getDate().equals(DATE)),
				() -> assertTrue(race1.getSubscriptionPrice() == SUBSCRIPTION_PRICE));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		Race race = new Race();
		race.setId(ID);
		race.setName(NAME);
		race.setDate(DATE);
		race.setSubscriptionPrice(SUBSCRIPTION_PRICE);
		race.setBoats(BOATS);
		
		assertAll(() -> assertTrue(race.getId() == ID),
				() -> assertTrue(race.getName() == NAME),
				() -> assertTrue(race.getDate().equals(DATE)),
				() -> assertTrue(race.getSubscriptionPrice() == SUBSCRIPTION_PRICE),
				() -> assertTrue(race.getBoats().equals(BOATS)));
	}

}
