/**
 * 
 */
package application.server.managers;

import java.util.ArrayList;
import java.util.List;

import application.models.Boat;

/**
 * @author Federico Canali
 *
 */
public class BoatsManager {
	
	private List<Boat> boats;
	
	/* 
	 * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static BoatsManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private BoatsManager() {
	   boats = new ArrayList<Boat>();
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static BoatsManager getInstance() {
       if (instance == null)
           instance = new BoatsManager();
       return instance;
   }

	/**
	 * @return the boats
	 */
	public List<Boat> getBoats() {
		return boats;
	}
	
	/**
	 * @param boats the boats to set
	 */
	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}

	/**
	 * Used to get all boats of a specific partner.
	 * @param partnerId is the partner's is.
	 * @return the list of partner's boats. 
	 **/
	public List<Boat> getPartnerBoats(int partnerId){
		refershList();
		
		List<Boat> list = new ArrayList<Boat>();
		
		for(Boat b: this.boats)
			if(b.getPartnerId() == partnerId)
				list.add(b);
		
		return list;
	}
	
	/**
	 * Used to add new boat.
	 * @param boat is the new boat to add.
	 **/
	public void addNewBoat(Boat boat) {
		SqlManager.getInstance().insertBoatInDB(boat);
		refershList();
	}
	
	/* used to update list of manager when data in DB changes */
	private void refershList() {
		this.boats.clear();
		SqlManager.getInstance().getPersonFromDB();
		System.out.println(this.boats);
	}
	
	/**
	 * Used to remove a boat from DB.
	 * @param boat is the boat to remove. 
	 **/
	public void removeBoat(Boat boat) {
		SqlManager.getInstance().deleteBoatInDB(boat);
		refershList();
	}
}
