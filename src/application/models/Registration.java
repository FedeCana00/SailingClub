package application.models;

import java.io.Serializable;

/**
 * This class represents an entry to a race by a sailboat.
 * 
 * @author Federico Canali
 *
 */
public class Registration implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the registration.
	 */
	private int id;
	/**
	 * Represents the race to register for.
	 */
	private Race race;
	/**
	 * Represents the boat to register.
	 */
	private Boat boat;
	
	/**
	 * Default constructor.
	 */
	public Registration() {}
	
	/**
	 * @param race is the race to register for.
	 * @param boat is the boat to register.
	 */
	public Registration(Race race, Boat boat) {
		super();
		this.race = race;
		this.boat = boat;
	}

	/**
	 * @param id is the id of the registration.
	 * @param race is the race to register for.
	 * @param boat is the boat to register.
	 */
	public Registration(int id, Race race, Boat boat) {
		super();
		this.id = id;
		this.race = race;
		this.boat = boat;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(Race race) {
		this.race = race;
	}

	/**
	 * @return the boat
	 */
	public Boat getBoat() {
		return boat;
	}

	/**
	 * @param boat the boat to set
	 */
	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ id = " + id + ", boat : [" 
				+ boat.toString() + "], race : ["
				+ race.toString() + " ]]";
	}
}
