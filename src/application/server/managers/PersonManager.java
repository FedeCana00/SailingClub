package application.server.managers;

import java.util.ArrayList;
import java.util.List;

import application.models.ClubStaff;
import application.models.Credentials;
import application.models.Partner;
import application.models.Person;

/**
 * This class deals with the management of the people.
 * 
 * @see application.models.Person
 * 
 * @author Federico Canali
 *
 */
public class PersonManager {
	/* CONSTANTS */
	/**
	 * this variable indicates that no user has logged in yet.
	 * */
	public static final int NOT_DEFINED = -1;
	/**
	 * this variable indicates that the person logged in is a partner.
	 * */
	public static final int PARTNER = 0;
	/**
	 * this variable indicates that the person logged in is a club staff.
	 * */
	public static final int CLUB_STAFF = 1;
	/* VARIABLES */
	private List<Person> people;
	Person loggedIn = null;
	int typeOfPersonLoggedIn = -1;
	
	/* 
	 * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static PersonManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private PersonManager() {
	   //init variables lists
	   people = new ArrayList<Person>();
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static PersonManager getInstance() {
       if (instance == null)
           instance = new PersonManager();
       return instance;
   }

	/**
	 * @return the people
	 */
	public List<Person> getPeople() {
		return people;
	}

	/**
	 * @param people the people to set
	 */
	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	/** 
    * Used to register new person to club.
    * @param person the user who must be register.
    **/
	public void addPerson(Person person) {
		SqlManager.getInstance().insertPersonInDB(person);
		refershList();
	}
	
	/* used to update list of manager when data in DB changes */
	private void refershList() {
		SqlManager.getInstance().getPersonFromDB();
		System.out.println(this.people);
	}
	
	/**
	 *  Used to check if there is already someone with same username.
	 *  @param userName is the username we want to check.
	 *  @return If the username already exists it returns true otherwise false.
	 **/
	public boolean hasSomeUserSameUserName(String userName) {
	   for(Person p: this.people)
		   if(p.getCredentials().getUsername().equals(userName))
			   return true;
	   return false;
	}
	
	/** 
    * Used to log in with credentials.
    * @param credentials is user trying to connect.
    * @return This method returns true if access is successful, otherwise false.
    * */
	public boolean logIn(Credentials credentials) {
	   try {
		   
		   if(isThisCredentialsExist(credentials)) {
			   Person person = getPersonFromUsername(credentials.getUsername());
			   if(person == null) {
				  throw new Exception("Something went wrong...");
			   }
			   
			   //insert person logged in 
			   this.loggedIn = person;
			   changeTypeOfPersonLoggedIn();
			   System.out.println("Welcome " + credentials.getUsername() + " [type of user : " + getTypeOfUser() + "]");
			   return true;
		   }
		   
		   //else show message if credentials not found
		   throw new Exception("Credentials not found.. Please retry!");
	   
	   } catch(Exception e){
		   System.out.println(e.getMessage());
	   }
	   return false;
	}
	
	/* check if this credentials exists */
	private boolean isThisCredentialsExist(Credentials credentials) {
	   for(Person p: this.people)
		   if(p.getCredentials().equals(credentials))
			   return true;
	   return false;
	}
	
	/* used to get user with this specific username */
	private Person getPersonFromUsername(String username) {
	   for(Person p: this.people)
		   if(p.getCredentials().getUsername().equals(username))
			   return p;
	   return null;
	}
	
	/* used to insert type of person logged in */
	private void changeTypeOfPersonLoggedIn() {
	   if(this.loggedIn == null) 
		   this.typeOfPersonLoggedIn = NOT_DEFINED;
	   
	   if(this.loggedIn instanceof Partner)
		   this.typeOfPersonLoggedIn = PARTNER;
	   
	   if(this.loggedIn instanceof ClubStaff)
		   this.typeOfPersonLoggedIn = CLUB_STAFF;
	}
	
	/**
    *  Used to get the type of user logged on.
    *  @return This method returns the type of connected user in string. 
    *  If none linked, it returns <code>null</code>.
    **/
	public String getTypeOfUser() {
	   switch(this.typeOfPersonLoggedIn) {
	   		case PARTNER:
	   			return "Partner";
	   		case CLUB_STAFF:
	   			return "Club staff";
	   		default:
	   			return null;
	   }
	}

	/**
	 * @return the loggedIn.
	 */
	public Person getLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Used to logout.
	 **/
	public void logout() {
		loggedIn = null;
		typeOfPersonLoggedIn = -1;
		System.out.println("Logout from the system!");
	}
	
	/**
	 * Used to returns all partners from database.
	 * @return returns all partners in database. 
	 **/
	public List<Partner> getAllPartners(){
		List<Partner> partners = new ArrayList<Partner>();
		
		people.forEach(person -> {
			if(person instanceof Partner)
				partners.add((Partner) person);
		});
		
		return partners;
	}
}
