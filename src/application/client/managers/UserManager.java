package application.client.managers;

import java.util.ArrayList;
import java.util.List;

import application.models.Boat;
import application.models.Partner;
import application.models.Person;
import application.models.Registration;

/**
 * This class is a singleton pattern. This class takes care of keeping information about the logged in user.
 * 
 * @author Federico Canali
 */
public class UserManager {
	
	private Person person = null;
	
	/*
    * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static UserManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private UserManager() {
       
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static UserManager getInstance() {
       if (instance == null)
           instance = new UserManager();
       return instance;
   }

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	
	/**
	 * Update partner's boat list.
	 **/
	public void updateBoats() {
		if(person == null || person instanceof Partner == false)
			return;
		
		((Partner) person).setBoats(ClientManager.getInstance().updatePartnerBoats(person.getId()));
	}
	
	/**
	 * Add new partner's boat.
	 * @param boat Represents the boat to add.
	 **/
	public void addNewBoat(Boat boat) {
		boat.setPartnerId(person.getId());
		
		ClientManager.getInstance().addBoat(boat);
		updateBoats();
	}
	
	/**
	 * Used to remove boat.
	 * @param boat Represents the boat to remove.
	 **/
	public void removeBoat(Boat boat) {
		boat.setPartnerId(person.getId());
		
		ClientManager.getInstance().removeBoat(boat);
		updateBoats();
	}
	
	/**
	 * Used to get all registrations.
	 * @return all the registrations.
	 **/
	public List<Registration> getRegistration(){
		List<Registration> registrations = new ArrayList<Registration>();
		
		((Partner) person).getBoats().forEach((boat) -> {
			boat.getRacesRegistered().forEach((race) -> {
				registrations.add(new Registration(race, boat));
			});
		});
		
		return registrations;
	}
	
	/**
	 * Add new registration.
	 * @param registration Represents the registration that must be send to server. 
	 **/
	public void addRegistration(Registration registration) {
		ClientManager.getInstance().addRegistration(registration);
		updateBoats();
	}
	
	/**
	 * Used to logout this user. 
	 **/
	public void logout() {
		person = null;
		PaymentsManager.getInstance().logout();
		ClientManager.getInstance().logout();
		System.out.println("Logout from the system!");
	}
}
