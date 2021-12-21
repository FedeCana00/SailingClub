/**
 * 
 */
package application.client.managers;

import java.util.ArrayList;
import java.util.List;

import application.models.Partner;
import application.models.Race;
import application.models.Registration;
import application.models.ToPay;

/**
 * @author Federico Canali
 *
 */
public class ClubStaffManager {
	
	List<Partner> partners;
	List<Race> races;
	List<ToPay> toPays;
	
	/*
    * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static ClubStaffManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private ClubStaffManager() {
       partners = new ArrayList<Partner>();
       races = new ArrayList<Race>();
       toPays = new ArrayList<ToPay>();
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static ClubStaffManager getInstance() {
       if (instance == null)
           instance = new ClubStaffManager();
       return instance;
   }

	/**
	 * @return the partners
	 */
	public List<Partner> getPartners() {
		return partners;
	}
	
	/**
	 * @param partners the partners to set
	 */
	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	/**
	 * @return the races
	 */
	public List<Race> getRaces() {
		return races;
	}

	/**
	 * @param races the races to set
	 */
	public void setRaces(List<Race> races) {
		this.races = races;
	}

	/**
	 * @return the toPays
	 */
	public List<ToPay> getToPays() {
		refreshToPays();
		return toPays;
	}

	/**
	 * @param toPays the toPays to set
	 */
	public void setToPays(List<ToPay> toPays) {
		this.toPays = toPays;
	}
	
	/**
	 * Used to refresh list of toPays.
	 */
	public void refreshToPays() {
		toPays =  ClientManager.getInstance().getToPay();
	}

	/**
	 * Used to refresh list of races.
	 */
	public void refreshRaces() {
		races = ClientManager.getInstance().getAllRaces();
	}
	
	/**
	 * Used to get all registration of specific partner.
	 * @param partner.
	 * @return all the registration.
	 **/
	public List<Registration> getRegistrationOfPartner(Partner partern){
		List<Registration> registrations = new ArrayList<Registration>();
		
		partern.getBoats().forEach((boat) -> {
			boat.getRacesRegistered().forEach((race) -> {
				registrations.add(new Registration(race, boat));
			});
		});
		
		return registrations;
	}
	
	/**
	 * Used to send notification to user of payment.
	 * @param toPay.
	 */
	public void sendNotificationOfPayment(ToPay toPay) {
		ClientManager.getInstance().sendNotificationOfPayment(toPay);
	}
	
	/**
	 * Remove race.
	 * @param race to remove. 
	 **/
	public void removeRace(Race race) {
		ClientManager.getInstance().removeRace(race);
		refreshRaces();
	}
}
