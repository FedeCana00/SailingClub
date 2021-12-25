package application.server.managers;

import java.util.ArrayList;
import java.util.List;

import application.models.Race;
import application.models.Registration;

/**
 * This class deals with the management of sailing club's races.
 * 
 * @see application.models.Race
 * 
 * @author Federico Canali
 *
 */
public class RacesManager {
	
	private List<Race> races;
	
	/* 
	 * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static RacesManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private RacesManager() {
	   races = new ArrayList<Race>();
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static RacesManager getInstance() {
       if (instance == null)
           instance = new RacesManager();
       return instance;
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
	 * Used to add new race. 
	 * @param race is the race to add.
	 **/
	public void addRace(Race race) {
		SqlManager.getInstance().insertRaceInDB(race);
		refershList();
	}
	
	/**
	 * Used to add new races. 
	 * @param race are the races to add.
	 **/
	public void addRace(List<Race> race) {
		races.addAll(race);
	}
	
	/* used to update list of manager when data in DB changes */
	private void refershList() {
		this.races.clear();
		SqlManager.getInstance().getPersonFromDB();
		System.out.println(this.races);
	}
	
	/**
	 * Register boat to race.
	 * @param registration is the registration to add in database.
	 **/
	public void registerBoatToRace(Registration registration) {
		SqlManager.getInstance().insertRegistrationInDB(registration);
		refershList();
	}
	
	/**
	 * Used to remove race.
	 * @param race is the race to remove from database. 
	 **/
	public void deleteRace(Race race) {
		SqlManager.getInstance().deleteRaceInDB(race);
		refershList();
	}
}
