package application.models;

/**
 * This class represents the club staff of the sailing club.
 * 
 * @author Federico Canali
 *
 */

public class ClubStaff extends Person {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor. 
	 */
	public ClubStaff() {
		super();
	}

	/**
	 * @param id is the id of the club staff.
	 * @param name is the name of the club staff.
	 * @param surname is the surname of the club staff.
	 * @param address is the address of the club staff.
	 * @param fiscalCode is the fiscal code of the club staff.
	 * @param credentials are the credentials of the club staff.
	 */
	public ClubStaff(int id, String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super(id, name, surname, address, fiscalCode, credentials);
	}
	
	/**
	 * @param name is the name of the club staff.
	 * @param surname is the surname of the club staff.
	 * @param address is the address of the club staff.
	 * @param fiscalCode is the fiscal code of the club staff.
	 * @param credentials are the credentials of the club staff.
	 */
	public ClubStaff(String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super(name, surname, address, fiscalCode, credentials);
	}
}
