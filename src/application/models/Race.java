package application.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a sailboat race, proposed by the sailing club.
 * 
 * @author Federico Canali
 *
 */

public class Race implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the name of the race.
	 */
	private String name;
	/**
	 * Represents the id of the race.
	 */
	private int id;
	/**
	 * Represents the date of the race.
	 */
	private Date date;
	/**
	 * Represents the subscription price of the race.
	 */
	private float subscriptionPrice;
	/**
	 * Represents list of boats registered to race.
	 */
	private List<Boat> boats;
	
	/**
	 * Default constructor. 
	 */
	public Race() {}
	
	/**
	 * @param name is the name of the race.
	 * @param date is the date of the race.
	 * @param subscriptionPrice is the subscription price of the race.
	 */
	public Race(String name, Date date, float subscriptionPrice) {
		super();
		this.name = name;
		this.date = date;
		this.subscriptionPrice = subscriptionPrice;
		boats = new ArrayList<Boat>();
	}
	
	
	/**
	 * @param id is the id of the race.
	 * @param name is the name of the race.
	 * @param date is the date of the race.
	 * @param subscriptionPrice is the subscription price of the race.
	 */
	public Race(int id ,String name, Date date, float subscriptionPrice) {
		super();
		this.name = name;
		this.id = id;
		this.date = date;
		this.subscriptionPrice = subscriptionPrice;
		boats = new ArrayList<Boat>();
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
	 * @return the boats
	 */
	public List<Boat> getBoats() {
		return boats;
	}
	/**
	 * @param boats the boats to set
	 */
	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the subscriptionPrice
	 */
	public float getSubscriptionPrice() {
		return subscriptionPrice;
	}


	/**
	 * @param subscriptionPrice the subscriptionPrice to set
	 */
	public void setSubscriptionPrice(float subscriptionPrice) {
		this.subscriptionPrice = subscriptionPrice;
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public String toString() {
		return "[ id = " + id + " , name = " + name +  
				" , date = " + date + " , boats : " + 
				boats.toString() + "]";
	}
}
