/**
 * 
 */
package application.models;

/**
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

	public ClubStaff(int id, String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super(id, name, surname, address, fiscalCode, credentials);
	}
	
	public ClubStaff(String name, String surname, String address, String fiscalCode, Credentials credentials) {
		super(name, surname, address, fiscalCode, credentials);
	}
}
