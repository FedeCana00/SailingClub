package application.client.managers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.List;

import application.communications.Message;
import application.communications.Request;
import application.communications.Response;
import application.managers.AlertManager;
import application.models.Boat;
import application.models.ClubStaff;
import application.models.Credentials;
import application.models.MembershipFee;
import application.models.Notification;
import application.models.Partner;
import application.models.Payment;
import application.models.Race;
import application.models.Registration;
import application.models.StorageFee;
import application.models.SubscriptionFee;
import application.models.ToPay;

/**
 * @author Federico Canali
 *
 */
public class ClientManager {
	private static final int SERVER_PORT = 8080;
	private static final String SERVER_HOST = "localhost";
	
	private static final int MAX = 10;
	
	/* 
	 * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static ClientManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private ClientManager() {
	   
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static ClientManager getInstance() {
       if (instance == null)
           instance = new ClientManager();
       return instance;
   }
   
   
   /**
    * Used to request to server access (login).
    * @return returns if the access has been accepted or refused.
    **/
   public boolean login(Credentials credentials) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_LOGIN, credentials);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (Boolean) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /** 
    * Used to ask to server informations about user connected 
    */
   public boolean userInformation() {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_USER_INFORMATION, null);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   UserManager.getInstance()
				   		.setPerson(response.getValue() instanceof Partner ? (Partner) response.getValue()
				   			: (ClubStaff) response.getValue());
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {	  	   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to request to server if already exist this username.
    * @return returns if exists or not.
    **/
   public boolean hasSomeUserSameUserName(Credentials credentials) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_THIS_USERNAME_EXISTS, credentials);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (Boolean) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used add new Partner to database.
    * @return returns true.
    **/
   public boolean addPerson(Partner partner) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_NEW_USER_REGISTRATION, partner);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used request partner boats from database.
    * @return returns list of boats.
    **/
   public List<Boat> updatePartnerBoats(int partnerId) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_PARTNER_BOATS, partnerId);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   
				   return (List<Boat>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to add new partner's boat to database.
    * @return returns true.
    **/
   public boolean addBoat(Boat boat) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_ADD_BOAT, boat);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to remove a partner's boat to database.
    * @return returns true.
    **/
   public boolean removeBoat(Boat boat) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_REMOVE_BOAT, boat);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to get all races available.
    * @return returns races.
    **/
   public List<Race> getAllRaces() {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_RACES , null);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<Race>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to send new registration to database.
    * @return returns true.
    **/
   public boolean addRegistration(Registration registration) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_ADD_REGISTRATION, registration);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   
   /**
    * Used to send logout request to server.
    * @return returns true.
    **/
   public boolean logout() {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_LOGOUT, null);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to send payment request to server.
    * @return returns true.
    **/
   public boolean insertPayment(Payment payment) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_PAYMENT, payment);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to request to server all partners.
    * @return returns true.
    **/
   public boolean getAllPartners() {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_ALL_PARTNERS, null);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   ClubStaffManager.getInstance().setPartners((List<Partner>) response.getValue());
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to request to server all subscribers (boats) of specific race.
    * @param raceId is the id of that specific race.
    * @return list of boats.
    **/
   public List<Boat> getAllSubscribers(int raceId) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_ALL_SUBSCRIBERS_OF_RACE, raceId);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<Boat>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to add race to database.
    * @return returns true.
    **/
   	public boolean addRace(Race race) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_ADD_RACE, race);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to remove a race from database.
    * @return returns true.
    **/
   	public boolean removeRace(Race race) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_DELETE_RACE, race);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to get all to pay.
    * @return returns list of toPays.
    **/
   public List<ToPay> getToPay() {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_TO_PAY, null);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<ToPay>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to send payment notification to partner.
    * @param toPay is the payment to send.
    * @return true.
    **/
   public boolean sendNotificationOfPayment(ToPay toPay) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_SEND_NOTIFICATION_PAYMENT, toPay);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
   
   /**
    * Used to get all notifications of partner.
    * @return returns list of notifications.
    **/
   public List<Notification> getNotifications(int partnerId) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_NOTIFICATIONS, partnerId);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<Notification>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to get all to pay of partner.
    * @return returns list of to pay.
    **/
   public List<ToPay> getToPaysOfPartner(int partnerId) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_TO_PAY_PARTNER, partnerId);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<ToPay>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to get all membership fees of partner.
    * @return returns list of to pay.
    **/
   public List<MembershipFee> getMembershipFees(int partnerId) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_MEMBERSHIP_FEE, partnerId);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<MembershipFee>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to get all subscription fees of partner.
    * @return returns list of subscription fees.
    **/
   public List<SubscriptionFee> getSubscriptionFees(int partnerId) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_SUBSCRIPTION_FEE, partnerId);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<SubscriptionFee>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to get all storage fees of partner.
    * @return returns list of storage fees.
    **/
   public List<StorageFee> getStorageFees(int partnerId) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_GET_STORAGE_FEE, partnerId);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return (List<StorageFee>) response.getValue();
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return null;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return null;
   }
   
   /**
    * Used to delete toPay.
    * @param toPay is the payment to remove.
    * @return true.
    **/
   public boolean deleteToPay(ToPay toPay) {
	   try {
		    Socket client = new Socket(SERVER_HOST, SERVER_PORT);
		   
		   ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream is = null;
		   
		   while(true) {
			   Request request = new Request(Message.ACTION_DELETE_TO_PAY, toPay);
			   
			   System.out.println("Client sends: " + request.getAction()  + " action to Server");
			   
			   os.writeObject(request);
			   os.flush();
			   
			   if(is == null) {
				   is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			   }
			   
			   Object o = is.readObject();
			   
			   if(o instanceof Response) {
				   Response response = (Response) o;
				   
				   System.out.println(" and received response: " + response.getAction() + " action from Server");
				   
				   client.close();
				   return true;
			   }
		   }
		   
	   } catch (IOException | ClassNotFoundException e) {
		   
		   if(e instanceof ConnectException) {
			   System.out.println("Server is in down! Please retry...");
			   AlertManager.getInstance().showErrorMessage("Server is in down! Please retry...");
			   return false;
		   }
		   
		   e.printStackTrace();
	   }
	   
	   return false;
   }
}
