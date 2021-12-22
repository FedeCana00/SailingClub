/**
 * 
 */
package test.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.models.Notification;

/**
 * @author Federico Canali
 *
 */
class NotificationTest {
	
	private static final int ID = 12;
	private static final String MESSAGE = "Hello this is a message!";
	private static final int PARTNER_ID = 1;
	private static final int TO_PAY_ID = 13;

	/**
	 * Test all constructor of the class. 
	 */
	@Test
	void constructorTest() {
		Notification notification1 = new Notification(MESSAGE, PARTNER_ID, TO_PAY_ID);
		assertTrue(notification1.getMessage() == MESSAGE);
		assertAll(() -> assertTrue(notification1.getPartnerId() == PARTNER_ID),
					() -> assertTrue(notification1.getToPayId() == TO_PAY_ID));
		
		Notification notification2 = new Notification(ID, MESSAGE, PARTNER_ID, TO_PAY_ID);
		assertTrue(notification2.getId() == ID);
		assertAll(() -> assertTrue(notification2.getMessage() == MESSAGE),
					() -> assertTrue(notification2.getPartnerId() == PARTNER_ID),
					() -> assertTrue(notification2.getToPayId() == TO_PAY_ID));
	}
	
	/**
	 * Test all setter of the class. 
	 */
	@Test
	void setterTest() {
		Notification notification = new Notification();
		notification.setId(ID);
		notification.setMessage(MESSAGE);
		notification.setPartnerId(PARTNER_ID);
		notification.setToPayId(TO_PAY_ID);
		
		assertTrue(notification.getId() == ID);
		assertAll(() -> assertTrue(notification.getMessage() == MESSAGE),
				() -> assertTrue(notification.getPartnerId() == PARTNER_ID),
				() -> assertTrue(notification.getToPayId() == TO_PAY_ID));
	}

}
