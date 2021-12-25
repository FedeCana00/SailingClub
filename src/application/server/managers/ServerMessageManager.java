package application.server.managers;

import application.communications.Message;
import application.communications.Request;
import application.communications.Response;
import application.models.Boat;
import application.models.Credentials;
import application.models.MembershipFee;
import application.models.Partner;
import application.models.Race;
import application.models.Registration;
import application.models.StorageFee;
import application.models.SubscriptionFee;
import application.models.ToPay;

/**
 * This class deals with the management of the server messages.
 * 
 * @see application.server.Server
 * @see application.communications.Message
 * 
 * @author Federico Canali
 *
 */
public class ServerMessageManager {
	
	/* 
	 * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static ServerMessageManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private ServerMessageManager() {
	   
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static ServerMessageManager getInstance() {
       if (instance == null)
           instance = new ServerMessageManager();
       return instance;
   }
   
   /**
    * Used to process the client request.
    * @param request represents the client's request that must be taken care of.
    * @return the appropriate response to the request is returned.
    **/
   public Response processRequest(Request request) {
	   try {
		   
		   switch (request.getAction()) {
				case Message.ACTION_LOGIN:
					return loginAction(request);
				case Message.ACTION_THIS_USERNAME_EXISTS:
					return hasSomeUserSameUserName(request);
				case Message.ACTION_NEW_USER_REGISTRATION:
					return addPerson(request);
				case Message.ACTION_USER_INFORMATION:
					return userInformation();
				case Message.ACTION_PARTNER_BOATS:
					return sendPartnerBoats(request);
				case Message.ACTION_ADD_BOAT:
					return addBoat(request);
				case Message.ACTION_REMOVE_BOAT:
					return removeBoat(request);
				case Message.ACTION_RACES:
					return getAllRaces(request);
				case Message.ACTION_ADD_REGISTRATION:
					return addBoatRegistrationToRace(request);
				case Message.ACTION_LOGOUT:
					return logout(request);
				case Message.ACTION_PAYMENT:
					return payment(request);
				case Message.ACTION_GET_ALL_PARTNERS:
					return getAllPartners(request);
				case Message.ACTION_GET_ALL_SUBSCRIBERS_OF_RACE:
					return getAllSubscribersOfRace(request);
				case Message.ACTION_DELETE_RACE:
					return removeRace(request);
				case Message.ACTION_GET_TO_PAY:
					return getToPay(request);
				case Message.ACTION_SEND_NOTIFICATION_PAYMENT:
					return sendNotificationPayment(request);
				case Message.ACTION_GET_NOTIFICATIONS:
					return getNotifications(request);
				case Message.ACTION_GET_TO_PAY_PARTNER:
					return getToPayOfPartner(request);
				case Message.ACTION_DELETE_TO_PAY:
					return deleteToPay(request);
				case Message.ACTION_GET_MEMBERSHIP_FEE:
					return getMembershipFees(request);
				case Message.ACTION_GET_SUBSCRIPTION_FEE:
					return getSubscriptionFees(request);
				case Message.ACTION_GET_STORAGE_FEE:
					return getStorageFees(request);
				case Message.ACTION_ADD_RACE:
					return addRace(request);
				default:
					throw new IllegalArgumentException("Unexpected value: " + request.getAction());
		   }
		   
	   } catch (Exception e) {
		   System.out.println(e.getMessage());
	   }
	   
	   return null;
   }
   
   /* process login request*/
   private Response loginAction(Request request) {
	   
	   if(request.getValue() instanceof Credentials == false)
		   return null;
	   
	   Credentials credentials = (Credentials) request.getValue();
	   Boolean isRegistered = true;
	   
	   //check if the user is registerd. If it's false return null.
	   if(!PersonManager.getInstance().logIn(credentials))
		   isRegistered = false;
	   
	   return new Response(request.getAction(), isRegistered);
   }
   
   /* check if this username already exists */
   private Response hasSomeUserSameUserName(Request request) {
	   
	   if(request.getValue() instanceof Credentials == false)
		   return null;
	   
	   Credentials credentials = (Credentials) request.getValue();
	   Boolean hasThisUsername = true;
	   
	   //check if the username exists. If it's false return null.
	   if(!PersonManager.getInstance().hasSomeUserSameUserName(credentials.getUsername()))
		   hasThisUsername = false;
	   
	   return new Response(request.getAction(), hasThisUsername);
   }
   
   /* add new Partner to database */
   private Response addPerson(Request request) {
	   
	   if(request.getValue() instanceof Partner == false)
		   return null;
	   
	   Partner partner = (Partner) request.getValue();
	   
	   PersonManager.getInstance().addPerson(partner);
		 
	   return new Response(request.getAction(), null);
   }
   
   /* used to send user information to client */
   private Response userInformation() {
	   return new Response(Message.ACTION_USER_INFORMATION, PersonManager.getInstance().getLoggedIn());
   }
   
   /* used to send partner's boats to client */
   private Response sendPartnerBoats(Request request) {
	   return new Response(request.getAction()
			   , BoatsManager.getInstance().getPartnerBoats((int) request.getValue()));
   }
   
   /* used to add new partner's boat into DB and send response to client*/
   private Response addBoat(Request request) {
	   if(request.getValue() instanceof Boat == false)
		   return null;
	   
	   SqlManager.getInstance().insertBoatInDB((Boat) request.getValue());
	   return new Response(request.getAction(), true);
   }
   
   /* used to remove boat from DB and send response to client */
   private Response removeBoat(Request request) {
	   if(request.getValue() instanceof Boat == false)
		   return null;
	   
	   BoatsManager.getInstance().removeBoat((Boat) request.getValue());
	   return new Response(request.getAction(), true);
   }
   
   /* used to get all races and send response to client */
   private Response getAllRaces(Request request) {
	   return new Response(request.getAction(), RacesManager.getInstance().getRaces());
   }
   
   /* used to add new registration to race from boat */
   private Response addBoatRegistrationToRace(Request request) {
	   RacesManager.getInstance().registerBoatToRace((Registration) request.getValue());
	   return new Response(request.getAction(), true);
   }
   
   /* used to logout */
   private Response logout(Request request) {
	   PersonManager.getInstance().logout();
	   return new Response(request.getAction(), true);
   }
   
   /* used to insert payment into database */
   private Response payment(Request request) {
	   if(request.getValue() instanceof SubscriptionFee) {
		   PaymentsManager.getInstance().insertPayment((SubscriptionFee) request.getValue());
		   return new Response(request.getAction(), true);
	   } else if (request.getValue() instanceof MembershipFee) {
		   PaymentsManager.getInstance().insertPayment((MembershipFee) request.getValue());
		   return new Response(request.getAction(), true);
	   } else if (request.getValue() instanceof StorageFee) {
		   PaymentsManager.getInstance().insertPayment((StorageFee) request.getValue());
		   return new Response(request.getAction(), true);
	   } else {
		   System.out.println("Instance of payment class not found... ");
		   return null;
	   }
   }
   
   /* used to get all partners from database */
   private Response getAllPartners(Request request) {
	   return new Response(request.getAction(), PersonManager.getInstance().getAllPartners());
   }
   
   /* used to get all subscribers of race from database (all boats of specific race) */
   private Response getAllSubscribersOfRace(Request request) {
	   return new Response(request.getAction(), SqlManager.getInstance().getAllBoatsOfRace((int) request.getValue()));
   }
   
   /* used to delete race */
   private Response removeRace(Request request) {
	   RacesManager.getInstance().deleteRace((Race) request.getValue());
	   return new Response(request.getAction(), true);
   }
   
   /* used to get to pay */
   private Response getToPay(Request request) {
	   //refresh list of payments before execute command
	   PaymentsManager.getInstance().refresh();
	   return new Response(request.getAction(), PaymentsManager.getInstance().getAllToPayNotYetSent());
   }
   
   /* used to send notification payment to partner */
   private Response sendNotificationPayment(Request request) {
	   PaymentsManager.getInstance().sendNotification((ToPay) request.getValue());
	   return new Response(request.getAction(), true);
   }
   
   /* used to get all notifications of partner */
   private Response getNotifications(Request request) {
	   return new Response(request.getAction(), PaymentsManager.getInstance().getNotifications((int) request.getValue()));
   }
   
   /* used to get all to pay of partner */
   private Response getToPayOfPartner(Request request) {
	   return new Response(request.getAction(), PaymentsManager.getInstance().getToPayOfPartner((int) request.getValue()));
   }
   
   /* used delete to pay in database */
   private Response deleteToPay(Request request) {
	   PaymentsManager.getInstance().deleteToPay((ToPay) request.getValue());
	   return new Response(request.getAction(), true);
   }
   
   /* used get membership fees of partner in database */
   private Response getMembershipFees(Request request) {
	   return new Response(request.getAction(), PaymentsManager.getInstance().getMembershipFeesOfPartner((int) request.getValue()));
   }
   
   /* used get subscription fees of partner in database */
   private Response getSubscriptionFees(Request request) {
	   return new Response(request.getAction(), PaymentsManager.getInstance().getSubscriptionFees((int) request.getValue()));
   }
   
   /* used get storage fees of partner in database */
   private Response getStorageFees(Request request) {
	   return new Response(request.getAction(), PaymentsManager.getInstance().getStorageFees((int) request.getValue()));
   }
   
   /* used add new race in database */
   private Response addRace(Request request) {
	   RacesManager.getInstance().addRace((Race) request.getValue());
	   return new Response(request.getAction(), true);
   }
}
