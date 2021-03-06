package application.communications;

import java.io.Serializable;

/**
 * Class that serves as a means of communication between client and server.
 * 
 * @author Federico Canali
 *
 */
public class Message implements Serializable{
	/**
	 *  Represents the String of the action login. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_LOGIN = "login";
	/**
	 *  Represents the String of the action check the existence of the username. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_THIS_USERNAME_EXISTS = "thisUsernameExist";
	/**
	 *  Represents the String of the action add partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_NEW_USER_REGISTRATION = "newUserRegistration";
	/**
	 *  Represents the String of the action get user logged information. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_USER_INFORMATION = "userInformation";
	/**
	 *  Represents the String of the action get boats of partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_PARTNER_BOATS = "partnerBoats";
	/**
	 *  Represents the String of the action add boat. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_ADD_BOAT = "addBoat";
	/**
	 *  Represents the String of the action remove boat. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_REMOVE_BOAT = "removeBoat";
	/**
	 *  Represents the String of the action get all races. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_RACES = "races";
	/**
	 *  Represents the String of the action add registration of boat to a race. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_ADD_REGISTRATION = "addRegistrationToRace";
	/**
	 *  Represents the String of the action logout. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_LOGOUT = "logout";
	/**
	 *  Represents the String of the action payment. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_PAYMENT = "payment";
	/**
	 *  Represents the String of the action get all partners. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_ALL_PARTNERS = "getAllPartners";
	/**
	 *  Represents the String of the action get all subscribers of race. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_ALL_SUBSCRIBERS_OF_RACE = "getAllSubscribersOfRace";
	/**
	 *  Represents the String of the action delete race. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_DELETE_RACE = "deleteRace";
	/**
	 *  Represents the String of the action get all to pay to send. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_TO_PAY = "getToPay";
	/**
	 *  Represents the String of the action send notification to pay to partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_SEND_NOTIFICATION_PAYMENT = "sendNotificationPayment";
	/**
	 *  Represents the String of the action get all notifications of partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_NOTIFICATIONS = "getNotifications";
	/**
	 *  Represents the String of the action get to pay of partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_TO_PAY_PARTNER = "getToPayPartner";
	/**
	 *  Represents the String of the action delete to pay after the payment by partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_DELETE_TO_PAY = "deleteToPay";
	/**
	 *  Represents the String of the action get membership fee of partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_MEMBERSHIP_FEE = "getMembershipFee";
	/**
	 *  Represents the String of the action get subscription fee of partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_SUBSCRIPTION_FEE = "getSubscriptionFee";
	/**
	 *  Represents the String of the action get storage fee of partner. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_GET_STORAGE_FEE = "getStorageFee";
	/**
	 *  Represents the String of the action add race. Used for communicating the type of action to be performed.
	 **/
	public static final String ACTION_ADD_RACE = "addRace";
	
	private static final long serialVersionUID = 1L;
	/**
	 * Represents the action to be performed.
	 */
	private String action;
	/**
	 * Represents the value to used in the action.
	 */
	private Object value;
	
	/**
	 * Default constructor. 
	 */
	public Message() {}
	
	/**
	 * @param action is the action to be performed.
	 * @param value is the value to used in the action.
	 */
	public Message(String action, Object value) {
		super();
		this.action = action;
		this.value = value;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
