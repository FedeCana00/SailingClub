package application.communications;

import java.io.Serializable;

/**
 * @author Federico Canali
 *
 */
public class Message implements Serializable{
	/**
	 *  
	 **/
	public static final String ACTION_LOGIN = "login";
	/**
	 *  
	 **/
	public static final String ACTION_THIS_USERNAME_EXISTS = "thisUsernameExist";
	/**
	 *  
	 **/
	public static final String ACTION_NEW_USER_REGISTRATION = "newUserRegistration";
	/**
	 *  
	 **/
	public static final String ACTION_USER_INFORMATION = "userInformation";
	/**
	 *  
	 **/
	public static final String ACTION_PARTNER_BOATS = "partnerBoats";
	/**
	 *  
	 **/
	public static final String ACTION_ADD_BOAT = "addBoat";
	/**
	 *  
	 **/
	public static final String ACTION_REMOVE_BOAT = "removeBoat";
	/**
	 *  
	 **/
	public static final String ACTION_RACES = "races";
	/**
	 *  
	 **/
	public static final String ACTION_ADD_REGISTRATION = "addRegistrationToRace";
	/**
	 *  
	 **/
	public static final String ACTION_LOGOUT = "logout";
	/**
	 *  
	 **/
	public static final String ACTION_PAYMENT = "payment";
	/**
	 *  
	 **/
	public static final String ACTION_GET_ALL_PARTNERS = "getAllPartners";
	/**
	 *  
	 **/
	public static final String ACTION_GET_ALL_SUBSCRIBERS_OF_RACE = "getAllSubscribersOfRace";
	/**
	 *  
	 **/
	public static final String ACTION_DELETE_RACE = "deleteRace";
	/**
	 *  
	 **/
	public static final String ACTION_GET_TO_PAY = "getToPay";
	/**
	 *  
	 **/
	public static final String ACTION_SEND_NOTIFICATION_PAYMENT = "sendNotificationPayment";
	/**
	 *  
	 **/
	public static final String ACTION_GET_NOTIFICATIONS = "getNotifications";
	/**
	 *  
	 **/
	public static final String ACTION_GET_TO_PAY_PARTNER = "getToPayPartner";
	/**
	 *  
	 **/
	public static final String ACTION_DELETE_TO_PAY = "deleteToPay";
	/**
	 *  
	 **/
	public static final String ACTION_GET_MEMBERSHIP_FEE = "getMembershipFee";
	/**
	 *  
	 **/
	public static final String ACTION_GET_SUBSCRIPTION_FEE = "getSubscriptionFee";
	/**
	 *  
	 **/
	public static final String ACTION_GET_STORAGE_FEE = "getStorageFee";
	/**
	 *  
	 **/
	public static final String ACTION_ADD_RACE = "addRace";
	
	private static final long serialVersionUID = 1L;
	private String action;
	private Object value;
	
	/**
	 * @param action
	 * @param value
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
