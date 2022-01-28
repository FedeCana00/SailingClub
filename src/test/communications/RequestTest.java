package test.communications;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.communications.Message;
import application.communications.Request;
import application.models.Race;

/**
 * This class tests the class Request.
 * 
 * @see application.communications.Request
 * 
 * @author Federico Canali
 *
 */
class RequestTest {

	private static final String ACTION = Message.ACTION_ADD_RACE;
	private static final Object VALUE = new Race();
	
	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void constructorTest() {
		Request message = new Request(ACTION, VALUE);
		
		assertTrue(message.getAction().equals(ACTION));
		assertTrue(message.getValue().equals(VALUE));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		Request message = new Request();
		message.setAction(ACTION);
		message.setValue(VALUE);
		
		assertTrue(message.getAction().equals(ACTION));
		assertTrue(message.getValue().equals(VALUE));
	}
}
