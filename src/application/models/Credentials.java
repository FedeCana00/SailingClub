/**
 * 
 */
package application.models;

import java.io.Serializable;

/**
 * @author Federico
 *
 */

public class Credentials implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	/**
	 * Default constructor. 
	 */
	public Credentials() {}
	
	/**
	 * @param username
	 * @param password
	 */
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * {@inheritDoc} 
	 **/
	@Override
	public String toString() {
		return "[ username = " + username + " , password = " + password + " ]";
	}
	
	/**
	 * {@inheritDoc} 
	 **/
	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if(obj == null)
			return false;
		Credentials o = (Credentials) obj;
		return this.username.equals(o.getUsername()) && this.password.equals(o.getPassword());
	}
}
