package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.Credentials;

/**
 * This class tests the class Credentials.
 * 
 * @see application.models.Credentials
 * 
 * @author Federico Canali
 *
 */
class CredentialsTest {
	
	private static final String USERNAME = "Username";
	private static final String PASSWORD = "Password";

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		Credentials credentials = new Credentials(USERNAME, PASSWORD);
		
		assertTrue(credentials.getUsername() == USERNAME);
		assertTrue(credentials.getPassword() == PASSWORD);
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		Credentials credentials = new Credentials();
		credentials.setUsername(USERNAME);
		credentials.setPassword(PASSWORD);
		
		assertTrue(credentials.getUsername() == USERNAME);
		assertTrue(credentials.getPassword() == PASSWORD);
	}

}
