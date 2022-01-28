package test.communications;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.communications.Message;
import application.models.Race;

/**
 * This class tests the class Message.
 * 
 * @see application.communications.Message
 * 
 * @author Federico Canali
 *
 */
class MessageTest {

	private static final String ACTION = Message.ACTION_ADD_RACE;
	private static final Object VALUE = new Race();
	
	/**
	 * This method tests the instance of the class. 
	 */
	@Test
	final void constructorTest() {
		Message message = new Message(ACTION, VALUE);
		
		assertTrue(message.getAction().equals(ACTION));
		assertTrue(message.getValue().equals(VALUE));
	}
	
	/**
	 *  This method tests the setters of the class. 
	 */
	@Test
	final void setterTest() {
		Message message = new Message();
		message.setAction(ACTION);
		message.setValue(VALUE);
		
		assertTrue(message.getAction().equals(ACTION));
		assertTrue(message.getValue().equals(VALUE));
	}
}
