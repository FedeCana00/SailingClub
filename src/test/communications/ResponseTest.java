package test.communications;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.communications.Message;
import application.communications.Request;
import application.communications.Response;
import application.models.Race;

/**
 * This class tests the class Response.
 * 
 * @see application.communications.Response
 * 
 * @author Federico Canali
 *
 */
class ResponseTest {

	private static final String ACTION = Message.ACTION_ADD_RACE;
	private static final Object VALUE = new Race();
	
	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void constructorTest() {
		Response message = new Response(ACTION, VALUE);
		
		assertTrue(message.getAction().equals(ACTION));
		assertTrue(message.getValue().equals(VALUE));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		Response message = new Response();
		message.setAction(ACTION);
		message.setValue(VALUE);
		
		assertTrue(message.getAction().equals(ACTION));
		assertTrue(message.getValue().equals(VALUE));
	}
}
