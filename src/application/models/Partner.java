/**
 * 
 */
package application.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Federico Canali
 *
 */
public class Partner extends Person {
	private static final long serialVersionUID = 1L;
	private List<Boat> boats;
	
	/**
	 * @param name
	 * @param surname
	 * @param address
	 * @param fiscalCode
	 */
	public Partner(int id, String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super(id, name, surname, address, fiscalCode, credentials);
		
		//initialize list properties
		boats = new ArrayList<Boat>();
	}
	
	/**
	 * @param surname
	 * @param address
	 * @param fiscalCode
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
