/**
 * 
 */
package application.client.managers;

import java.util.ArrayList;
import java.util.List;

import application.models.Notification;

/**
 * @author Federico Canali
 *
 */
public class PartnerManager {
	
	private List<Notification> notifications;
	
	/*
	* The instance is static so it is shared among all instances of the class. It is also private
	* so it is accessible only within the class.
	*/
   private static PartnerManager instance = null;

   /*
	* The constructor is private so it is accessible only within the class.
	*/
   private PartnerManager() {
       notifications = new ArrayList<Notification>();
       
       setNotifications();
   }
	
   /**
	* The constructor is called only if the static instance is null, so only the first time
	* that the getInstance() method is invoked.
	* All the other times the same instance object is returned.
	* @return the instance object is returned.
	**/
   public static PartnerManager getInstance() {
       if (instance == null)
           instance = new PartnerManager();
       return instance;
   }

	/**
	 * @return the notifications
	 */
	public List<Notification> getNotifications() {
		return notifications;
	}
	
	/**
	 * Used to set notification. 
	 */
	public void setNotifications() {
		notifications = ClientManager.getInstance()
				.getNotifications(UserManager.getInstance()
						.getPerson().getId());
	}
	
}
