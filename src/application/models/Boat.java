package application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the sailboat.
 * 
 * @author Federico Canali
 */
public class Boat implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the id of the boat.
	 */
	private int id;
	/**
	 * Represents the name of the boat.
	 */
	private String name;
	/**
	 * Represents the length of the boat.
	 */
	private float length;
	/**
	 * Represents the id of the owner.
	 */
	private int partnerId;
	/**
	 * Represents the list of the races to which the boat is registered.
	 */
	private List<Race> racesRegistered;
	
	/**
	 * Default constructor. 
	 */
	public Boat() {}
	
	/**
	 * @param id is the id of the boat.
	 * @param name is the name of the boat.
	 * @param length is the length of the boat.
	 * @param partnerId is the id of owner.
	 */
	public Boat(int id, String name, float length, int partnerId) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.partnerId = partnerId;
		racesRegistered = new ArrayList<Race>();
	}
	
	/**
	 * @param name is the name of the boat.
	 * @param length is the length of the boat.
	 * @param partnerId is the id of owner.
	 */
	public Boat(String name, float length, int partnerId) {
		super();
		this.name = name;
		this.length = length;
		this.partnerId = partnerId;
		racesRegistered = new ArrayList<Race>();
	}

	/**
	 * @param name is the name of the boat.
	 * @param length is the length of the boat.
	 */
	public Boat(String name, float length) {
		super();
		this.name = name;
		this.length = length;
		racesRegistered = new ArrayList<Race>();
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the length
	 */
	public float getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}

	/**
	 * @return the partnerId
	 */
	public int getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	
	/**
	 * @return the racesRegistered
	 */
	public List<Race> getRacesRegistered() {
		return racesRegistered;
	}

	/**
	 * @param racesRegistered the racesRegistered to set
	 */
	public void setRacesRegistered(List<Race> racesRegistered) {
		this.racesRegistered = racesRegistered;
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ id = " + id + ", name = " + name
				+ " , length = " + length + 
				", partner id = " + partnerId + " ]";
	}

}
