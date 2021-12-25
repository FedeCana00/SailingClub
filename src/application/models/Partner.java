package application.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents users who are members of the sailing club.
 * 
 * @author Federico Canali
 *
 */
public class Partner extends Person {
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the list of the boast owned by the partner.
	 */
	private List<Boat> boats;
	
	/**
	 * Default constructor. 
	 */
	public Partner() {
		super();
		
		this.boats = new ArrayList<Boat>();
	}
	
	/**
	 * @param id it the id of the partner.
	 * @param name is the name of the partner.
	 * @param surname is the surname of the partner.
	 * @param address is the address of the partner.
	 * @param fiscalCode is the fiscal code of the partner.
	 * @param credentials are the credentials of the partner.
	 */
	public Partner(int id, String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super(id, name, surname, address, fiscalCode, credentials);
		
		//initialize list properties
		boats = new ArrayList<Boat>();
	}
	
	/**
	 * @param name is the name of the partner.
	 * @param surname is the surname of the partner.
	 * @param address is the address of the partner.
	 * @param fiscalCode is the fiscal code of the partner.
	 * @param credentials are the credentials of the partner.
	 */
	public Partner(String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super(name, surname, address, fiscalCode, credentials);
		
		//initialize list properties
		boats = new ArrayList<Boat>();
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
}
