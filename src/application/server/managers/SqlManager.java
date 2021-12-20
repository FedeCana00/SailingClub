package application.server.managers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.classes.PaymentReason;
import application.models.Boat;
import application.models.ClubStaff;
import application.models.Credentials;
import application.models.CreditCard;
import application.models.MembershipFee;
import application.models.Notification;
import application.models.Partner;
import application.models.Payment;
import application.models.PaymentMethod;
import application.models.Person;
import application.models.Race;
import application.models.Registration;
import application.models.StorageFee;
import application.models.SubscriptionFee;
import application.models.ToPay;
import application.models.WireTransfer;

/**
 * 
 * @author Federico Canali
 */
public class SqlManager {
	
	/* SQL DB ELEMENTS */
	private static final String DBURL = "jdbc:mysql://localhost:3306/sailingclub?";
	private static final String ARG = "createDatabaseIfNotExist=true&serverTimezone=UTC";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";
	private Statement stmt;
	private Connection conn;
	
	/*
    * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static SqlManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private SqlManager() {
	   
	   try {
		   
		   conn = DriverManager.getConnection(
		   DBURL + ARG, LOGIN, PASSWORD);
		   stmt = (Statement) conn.createStatement();
		   
		   String createPersonTableString = "create table if not exists person" +
				   "(id int not null AUTO_INCREMENT, " +
				   " isPartner BIT, " +
				   " name VARCHAR(30), " +
				   " surname VARCHAR(30), " +
				   " address VARCHAR(50), " +
				   " fiscalCode VARCHAR(16), " +
				   " username VARCHAR(30), " +
				   " password VARCHAR(30), " +
				   " primary key ( id ))";
		   
		   String createBoatTableString = "create table if not exists boat" +
				   "(id int not null AUTO_INCREMENT, " +
				   " name VARCHAR(30), " +
				   " length REAL, " +
				   " partnerId int, " +
				   " primary key ( id )," +
				   " FOREIGN KEY (partnerId) REFERENCES person(id) ON DELETE CASCADE)";
		   
		   String createRaceTableString = "create table if not exists race" +
				   "(id int not null AUTO_INCREMENT, " +
				   " name VARCHAR(30), " +
				   " date DATE, " +
				   " subscriptionPrice REAL, " +
				   " primary key ( id ))";
		   
		   String createRegistrationTableString = "create table if not exists registration" +
				   "(id int not null AUTO_INCREMENT, " +
				   " boatId int, " +
				   " raceId int, " +
				   " primary key ( id ), " +
				   " FOREIGN KEY (boatId) REFERENCES boat(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (raceId) REFERENCES race(id) ON DELETE CASCADE)";
		   
		   String createPaymentTableString = "create table if not exists paymentMethod" +
				   "(id int not null AUTO_INCREMENT, " +
				   " isCreditCard BIT, " +
				   " ownerName VARCHAR(30), " +
				   " ownerSurname VARCHAR(30), " +
				   " cvv int, " +
				   " cardNumber BIGINT, " +
				   " expiration VARCHAR(5), " +
				   " emissionDate DATE, " +
				   " referenceCode VARCHAR(10), " +
				   " primary key ( id ))" ;
		   
		   String createSubscriptionFeeTableString = "create table if not exists subscriptionFee" +
				   "(id int not null AUTO_INCREMENT, " +
				   " price REAL, " +
				   " date DATE, " +
				   " paymentMethodId int, " +
				   " partnerId int, " +
				   " boatId int, " +
				   " raceId int, " +
				   " primary key ( id ), " +
				   " FOREIGN KEY (raceId) REFERENCES race(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (paymentMethodId) REFERENCES paymentMethod(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (boatId) REFERENCES boat(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (partnerId) REFERENCES person(id) ON DELETE CASCADE)";
		   
		   String createStorageFeeTableString = "create table if not exists storageFee" +
				   "(id int not null AUTO_INCREMENT, " +
				   " price REAL, " +
				   " date DATE, " +
				   " paymentMethodId int, " +
				   " partnerId int, " +
				   " boatId int, " +
				   " primary key ( id ), " +
				   " FOREIGN KEY (paymentMethodId) REFERENCES paymentMethod(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (boatId) REFERENCES boat(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (partnerId) REFERENCES person(id) ON DELETE CASCADE)";
		   
		   String createMembershipFeeTableString = "create table if not exists membershipFee" +
				   "(id int not null AUTO_INCREMENT, " +
				   " price REAL, " +
				   " date DATE, " +
				   " paymentMethodId int, " +
				   " partnerId int, " +
				   " primary key ( id ), " +
				   " FOREIGN KEY (paymentMethodId) REFERENCES paymentMethod(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (partnerId) REFERENCES person(id) ON DELETE CASCADE)";
		   
		   String createNotificationTableString = "create table if not exists notification" +
				   "(id int not null AUTO_INCREMENT, " +
				   " message VARCHAR(100), " +
				   " partnerId int, " +
				   " toPayId int, " +
				   " primary key ( id ), " +
				   " FOREIGN KEY (toPayId) REFERENCES toPay(id) ON DELETE CASCADE, " +
				   " FOREIGN KEY (partnerId) REFERENCES person(id) ON DELETE CASCADE)";
		   
		   String createToPayTableString = "create table if not exists toPay" +
				   "(id int not null AUTO_INCREMENT, " +
				   " price REAL, " +
				   " isMembershipFee BIT, " + //else it is storageFee
				   " partnerId int, " +
				   " boatId int, " +
				   " primary key ( id ), " +
				   " FOREIGN KEY (partnerId) REFERENCES person(id) ON DELETE CASCADE)";
		   
		   ((java.sql.Statement) stmt).executeUpdate(createPersonTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createBoatTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createRaceTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createRegistrationTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createPaymentTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createPaymentTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createSubscriptionFeeTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createStorageFeeTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createMembershipFeeTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createToPayTableString);
		   ((java.sql.Statement) stmt).executeUpdate(createNotificationTableString);
		   
		   
		   getPersonFromDB();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static SqlManager getInstance() {
       if (instance == null)
           instance = new SqlManager();
       return instance;
   }
   
   /** 
    * Method used to read and store the whole person table in the PersonManager list 
    */
   public void getPersonFromDB() {
	   String selectString = "select * from person";
	   
	   try {
		   
		   ResultSet rset = ((java.sql.Statement) stmt).executeQuery(selectString);
		   
		   List<Person> people = new ArrayList<Person>();
		   
		   while(rset.next()) {
			   if(rset.getBoolean("isPartner")) {
				   Partner partner = new Partner(rset.getInt("id"),
						   rset.getString("name"), rset.getString("surname"),
						   rset.getString("address"), rset.getString("fiscalCode"),
						   new Credentials(rset.getString("username"), rset.getString("password")));
				   
				   //set boats from DB
				   partner.setBoats(getPartnerBoatsFromDB(partner.getId()));
				   
				   people.add(partner);
			   } else {
				   ClubStaff clubStaff = new ClubStaff(rset.getInt("id"),
						   rset.getString("name"), rset.getString("surname"),
						   rset.getString("address"), rset.getString("fiscalCode"),
						   new Credentials(rset.getString("username"), rset.getString("password")));
				   people.add(clubStaff);
			   }
		   }
		   
		   //add list inside PersonManager
		   PersonManager.getInstance().setPeople(people);
		   RacesManager.getInstance().setRaces(getRacesFromDB());
		   getBoats();
		   
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
   }
   
   /* used to get from DB all boats of specific partner */
   private List<Boat> getPartnerBoatsFromDB(int partnerId){
	   String selectString = "select * from boat where partnerId = " + partnerId;
	   List<Boat> boats = new ArrayList<Boat>();
	   
	   try {
		   
		   Statement stmt2 = (Statement) conn.createStatement();
		   ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);
		   
		   while(rset.next()) {
			   Boat boat = new Boat(rset.getInt("id"), rset.getString("name")
					   , rset.getFloat("length"), rset.getInt("partnerId"));
			   
			   //set the races in which the boat is registered
			   boat.setRacesRegistered(getRacesBoatsFromDB(boat.getId()));
			   
			   boats.add(boat);
		   }
		   
		   stmt2.close();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return boats;
   }
   
   /* used to get all boats from database */
   private List<Boat> getBoats(){
	   String selectString = "select * from boat";
	   List<Boat> boats = new ArrayList<Boat>();
	   
	   try {
		   
		   Statement stmt2 = (Statement) conn.createStatement();
		   ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);
		   
		   while(rset.next()) {
			   Boat boat = new Boat(rset.getInt("id"), rset.getString("name")
					   , rset.getFloat("length"), rset.getInt("partnerId"));
			   
			   //set the races in which the boat is registered
			   boat.setRacesRegistered(getRacesBoatsFromDB(boat.getId()));
			   
			   boats.add(boat);
		   }
		   
		   BoatsManager.getInstance().setBoats(boats);
		   stmt2.close();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return boats;
   }
   
   /* used to get from DB all race of specific boat */
   private List<Race> getRacesBoatsFromDB(int boatId){
	   String selectString = "select * from race as r, registration as rg where rg.raceId = r.id and rg.boatId = " + boatId;
	   List<Race> races = new ArrayList<Race>();
	   
	   try {
		   
		   Statement stmt2 = (Statement) conn.createStatement();
		   ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);
		   
		   while(rset.next()) {
			   Race race = new Race(rset.getInt("r.id"), rset.getString("r.name"), rset.getDate("r.date")
					   , rset.getFloat("r.subscriptionPrice"));
			   races.add(race);
		   }
		   
		   stmt2.close();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return races;
   }
   
   /**
    *  Used to get all boats and name of user inside of specific race.
    *  @param raceId.
    *  @return list of all suberscibers of race.
    */
   public List<Boat> getAllBoatsOfRace(int raceId){
	   String selectString = "select * from boat as b, registration as rg where rg.raceId = " + raceId + " and rg.boatId = b.id ";
	   List<Boat> boats = new ArrayList<Boat>();
	   
	   try {
		   
		   Statement stmt2 = (Statement) conn.createStatement();
		   ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);
		   
		   while(rset.next()) {
			   Boat boat = new Boat(rset.getInt("b.id"), rset.getString("b.name"), rset.getFloat("b.length")
					   , rset.getInt("b.partnerId"));
			   boats.add(boat);
		   }
		   
		   stmt2.close();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return boats;
   }
   
   /* Used to get from database all races. */
   	private List<Race> getRacesFromDB(){
	   String selectString = "select * from race";
	   List<Race> races = new ArrayList<Race>();
	   
	   try {
		   
		   Statement stmt2 = (Statement) conn.createStatement();
		   ResultSet rset = ((java.sql.Statement) stmt2).executeQuery(selectString);
		   
		   while(rset.next()) {
			   Race race = new Race(rset.getInt("id"), rset.getString("name"), rset.getDate("date")
					   , rset.getFloat("subscriptionPrice"));
			   races.add(race);
		   }
		   
		   stmt2.close();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return races;
   }
   
   /** 
    * Used to insert new person in DB.
    * @param person the user who must be inserted in the DB.
    **/
   public void insertPersonInDB(Person person) {
	   String insertSql = "insert into person values (?, ?, ?, ?, ?, ?, ?, ?)";
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql);
		   
		   //configure PreparedStatement with values of Person
		   pstmt.setInt(1, person.getId());
		   pstmt.setBoolean(2, person instanceof Partner ? true : false);
		   pstmt.setString(3, person.getName());
		   pstmt.setString(4, person.getSurname());
		   pstmt.setString(5, person.getAddress());
		   pstmt.setString(6, person.getFiscalCode());
		   pstmt.setString(7, person.getCredentials().getUsername());
		   pstmt.setString(8, person.getCredentials().getPassword());
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB(); //read and memorize values in PersonManager
			   System.out.println("User added to DB => " + person.toString());
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /** 
    * Used to insert new boat in DB.
    * @param boat the boat which must be inserted in the DB.
    **/
   public void insertBoatInDB(Boat boat) {
	   String insertSql = "insert into boat values (?, ?, ?, ?)";
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql);
		   
		   //configure PreparedStatement with values of Person
		   pstmt.setInt(1, boat.getId());
		   pstmt.setString(2, boat.getName());
		   pstmt.setFloat(3, boat.getLength());
		   pstmt.setInt(4, boat.getPartnerId());
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("Boat added to DB => " + boat.toString());
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /** 
    * Used to delete specific boat in DB.
    * @param boat the boat which must be delete in the DB.
    **/
   public void deleteBoatInDB(Boat boat) {
	   String deleteSql = "delete from boat where id = " + boat.getId();
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(deleteSql);
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("Boat deleted in DB => " + boat.toString());
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /** 
    * Used to insert new race in DB.
    * @param race the race which must be inserted in the DB.
    **/
   public void insertRaceInDB(Race race) {
	   String insertSql = "insert into race values (?, ?, ?, ?)";
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql);
		   
		   //configure PreparedStatement with values of Person
		   pstmt.setInt(1, race.getId());
		   pstmt.setString(2, race.getName());
		   pstmt.setDate(3, race.getDate());
		   pstmt.setFloat(4, race.getSubscriptionPrice());
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("Race added to DB => " + race.toString());
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /** 
    * Used to insert new boats register to race in DB.
    * @param registration.
    **/
   public void insertRegistrationInDB(Registration registration) {
	   String insertSql = "insert into registration values (?, ?, ?)";
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql);
		   
		   //configure PreparedStatement with values of Person
		   pstmt.setInt(1, 0);
		   pstmt.setInt(2, registration.getBoat().getId());
		   pstmt.setInt(3, registration.getRace().getId());
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("Registration added to DB => " + registration.toString());
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /** 
    * Method used to read all payments inside the database.
    */
   public void getAllPayments() {
	   getAllSubscriptionFee();
	   getAllMembershipFee();
	   getAllStorageFee();
	   getAllToPayAndNotification();
   }
   
   /* used to get all subscriptionFee */
   private void getAllSubscriptionFee() {
	   String selectString = "select * from subscriptionFee as s, paymentMethod as p"
	   		+ " where s.paymentMethodId = p.id";
	   
	   try {
		   
		   ResultSet rset = ((java.sql.Statement) stmt).executeQuery(selectString);
		   
		   List<SubscriptionFee> subscriptionFees = new ArrayList<SubscriptionFee>();
		   
		   while(rset.next()) {
			   SubscriptionFee subscriptionFee = new SubscriptionFee();
			   subscriptionFee.setId(rset.getInt("s.id"));
			   subscriptionFee.setPartnerId(rset.getInt("s.partnerId"));
			   subscriptionFee.setPrice(rset.getFloat("s.price"));
			   subscriptionFee.setDate(rset.getDate("s.date"));
			   subscriptionFee.setBoatId(rset.getInt("s.boatId"));
			   subscriptionFee.setRaceId(rset.getInt("s.raceId"));
			   
			   if(rset.getBoolean("p.isCreditCard")) {
				   CreditCard creditCard = new CreditCard(rset.getString("p.ownerName")
						   , rset.getString("p.ownerSurname"), rset.getInt("p.cvv")
						   , rset.getLong("cardNumber"), rset.getString("p.expiration"));
				   
				   subscriptionFee.setPaymentMethod(creditCard);
			   } else {
				   WireTransfer wireTransfer = new WireTransfer(rset.getString("p.ownerName")
						   , rset.getString("p.ownerSurname"), rset.getDate("p.emissionDate")
						   , rset.getString("p.referenceCode"));
				   
				   subscriptionFee.setPaymentMethod(wireTransfer);
			   }
			   
			   subscriptionFees.add(subscriptionFee);
		   }
		   
		   //add list inside manager
		   PaymentsManager.getInstance().setSubscriptionFees(subscriptionFees);
		   
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
   }
   
   /* used to get all subscriptionFee */
   private void getAllMembershipFee() {
	   String selectString = "select * from membershipFee as m, paymentMethod as p"
	   		+ " where m.paymentMethodId = p.id";
	   
	   try {
		   
		   ResultSet rset = ((java.sql.Statement) stmt).executeQuery(selectString);
		   
		   List<MembershipFee> membershipFees = new ArrayList<MembershipFee>();
		   
		   while(rset.next()) {
			   MembershipFee membershipFee = new MembershipFee();
			   membershipFee.setId(rset.getInt("m.id"));
			   membershipFee.setPrice(rset.getFloat("m.price"));
			   membershipFee.setDate(rset.getDate("m.date"));
			   membershipFee.setPartnerId(rset.getInt("m.partnerId"));
			   
			   if(rset.getBoolean("p.isCreditCard")) {
				   CreditCard creditCard = new CreditCard(rset.getString("p.ownerName")
						   , rset.getString("p.ownerSurname"), rset.getInt("p.cvv")
						   , rset.getLong("cardNumber"), rset.getString("p.expiration"));
				   
				   membershipFee.setPaymentMethod(creditCard);
			   } else {
				   WireTransfer wireTransfer = new WireTransfer(rset.getString("p.ownerName")
						   , rset.getString("p.ownerSurname"), rset.getDate("p.emissionDate")
						   , rset.getString("p.referenceCode"));
				   
				   membershipFee.setPaymentMethod(wireTransfer);
			   }
			   
			   membershipFees.add(membershipFee);
		   }
		   
		   //add list inside manager
		   PaymentsManager.getInstance().setMembershipFees(membershipFees);
		   
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
   }
   
   /* used to get all storageFee */
   private void getAllStorageFee() {
	   String selectString = "select * from storageFee as s, paymentMethod as p"
	   		+ " where s.paymentMethodId = p.id";
	   
	   try {
		   
		   ResultSet rset = ((java.sql.Statement) stmt).executeQuery(selectString);
		   
		   List<StorageFee> storageFees = new ArrayList<StorageFee>();
		   
		   while(rset.next()) {
			   StorageFee storageFee = new StorageFee();
			   storageFee.setId(rset.getInt("s.id"));
			   storageFee.setPartnerId(rset.getInt("s.partnerId"));
			   storageFee.setPrice(rset.getFloat("s.price"));
			   storageFee.setDate(rset.getDate("s.date"));
			   storageFee.setBoatId(rset.getInt("s.boatId"));
			   
			   if(rset.getBoolean("p.isCreditCard")) {
				   CreditCard creditCard = new CreditCard(rset.getString("p.ownerName")
						   , rset.getString("p.ownerSurname"), rset.getInt("p.cvv")
						   , rset.getLong("cardNumber"), rset.getString("p.expiration"));
				   
				   storageFee.setPaymentMethod(creditCard);
			   } else {
				   WireTransfer wireTransfer = new WireTransfer(rset.getString("p.ownerName")
						   , rset.getString("p.ownerSurname"), rset.getDate("p.emissionDate")
						   , rset.getString("p.referenceCode"));
				   
				   storageFee.setPaymentMethod(wireTransfer);
			   }
			   
			   storageFees.add(storageFee);
		   }
		   
		   //add list inside manager
		   PaymentsManager.getInstance().setStorageFees(storageFees);
		   
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
   }
   
   /* used to get all to pay and notification */
   private void getAllToPayAndNotification() {
	   /*
	    * Used to do a left JOIN in order to get all value of ToPay and also all notification
	   	* associated to it. 
	   	*/
	   String selectString = "select * from toPay as tp left JOIN notification as n ON n.toPayId = tp.id";
	   
	   try {
		   
		   ResultSet rset = ((java.sql.Statement) stmt).executeQuery(selectString);
		   
		   Map<ToPay, Notification> toPays = new HashMap<ToPay, Notification>();
		   
		   while(rset.next()) {
			   ToPay toPay = new ToPay();
			   toPay.setId(rset.getInt("tp.id"));
			   toPay.setMembershipFee(rset.getBoolean("tp.isMembershipFee"));
			   toPay.setPartnerId(rset.getInt("tp.partnerId"));
			   toPay.setPrice(rset.getFloat("tp.price"));
			   toPay.setBoatId(rset.getInt("tp.boatId"));
			   
			   Notification notification = new Notification();
			   if(rset.getInt("n.id") == 0)
				   notification = null;
			   else {
				   notification.setId(rset.getInt("n.id"));
				   notification.setMessage(rset.getString("n.message"));
				   notification.setPartnerId(rset.getInt("n.partnerId"));
				   notification.setToPayId(rset.getInt("n.toPayId"));
			   }
			   
			   toPays.put(toPay, notification);
		   }
		   
		   //add hash map inside the payment manager
		   PaymentsManager.getInstance().setToPays(toPays);
		   
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
   }
   
   /**
    * Used to insert new payment.
    * @param payment.
    **/
   public void insertPayment(Payment payment) {
	   String insertSql = "insert into " + PaymentReason.getTableName(payment) 
	   		+ PaymentReason.getValue(payment);
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql);
		   
		   pstmt.setInt(1, 0);
		   pstmt.setFloat(2, payment.getPrice());
		   pstmt.setDate(3, payment.getDate());
		   pstmt.setInt(4, insertPaymentMethod(payment.getPaymentMethod())); //add paymentMethod and then get id of payment
		   pstmt.setInt(5, payment.getPartnerId());
		   
		   if(payment instanceof SubscriptionFee) {
			   SubscriptionFee subscriptionFee = (SubscriptionFee) payment;
			   pstmt.setInt(6, subscriptionFee.getBoatId());
			   pstmt.setInt(7, subscriptionFee.getRaceId());
		   } else if(payment instanceof StorageFee) {
			   StorageFee storageFee = (StorageFee) payment;
			   pstmt.setInt(6, storageFee.getBoatId());
		   } else if(payment instanceof MembershipFee) {
			   //nothing to do here...
		   } else {
			   System.out.println("Instance of payment not found...");
			   return;
		   }
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("Payment added to DB => " + payment.toString());
		   }
		   
		   //refresh payments
		   getAllPayments();
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /* used to add in database payment method*/
   private int insertPaymentMethod(PaymentMethod paymentMethod) {
	   String insertSql = "insert into paymentMethod values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
		   
		   pstmt.setInt(1, 0);
		   pstmt.setBoolean(2, paymentMethod instanceof CreditCard ? true : false);
		   pstmt.setString(3, paymentMethod.getOwnerName());
		   pstmt.setString(4, paymentMethod.getOwnerSurname());
		   
		   if(paymentMethod instanceof CreditCard) {
			   CreditCard creditCard = (CreditCard) paymentMethod;
			   pstmt.setInt(5, creditCard.getCvv());
			   pstmt.setLong(6, creditCard.getCardNumber());
			   pstmt.setString(7, creditCard.getExpirationDate());
			   pstmt.setDate(8, null);
			   pstmt.setString(9, null);
		   } else {
			   WireTransfer wireTransfer = (WireTransfer) paymentMethod;
			   pstmt.setInt(5, 0);
			   pstmt.setLong(6, 0);
			   pstmt.setString(7, null);
			   pstmt.setDate(8, wireTransfer.getEmissionDate());
			   pstmt.setString(9, wireTransfer.getReferenceCode());
		   }
		  
		  pstmt.execute();
		   
		   try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		   
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return -1;
   }
   
   /**
    * Used to insert notification.
    * @param notification
    **/
   public void insertNotification(Notification notification) {
	   String insertSql = "insert into notification values (?, ?, ?, ?)";
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql);
		   
		   pstmt.setInt(1, 0);
		   pstmt.setString(2, notification.getMessage());
		   pstmt.setInt(3, notification.getPartnerId());
		   pstmt.setInt(4, notification.getToPayId());
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("Notification added to DB => " + notification.toString());
		   }
		   
		   //refresh payments
		   //TODO: refresh notification
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /**
    * Used to insert toPay.
    * @param toPay
    **/
   public void insertToPay(ToPay toPay) {
	   String insertSql = "insert into toPay values (?, ?, ?, ?, ?)";
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(insertSql);
		   
		   pstmt.setInt(1, 0);
		   pstmt.setFloat(2, toPay.getPrice());
		   pstmt.setBoolean(3, toPay.isMembershipFee());
		   pstmt.setInt(4, toPay.getPartnerId());
		   pstmt.setInt(5, toPay.getBoatId());
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("ToPay added to DB => " + toPay.toString());
		   }
		   
		   //refresh payments
		   //TODO: refresh notification
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /** 
    * Used to delete specific toPay in DB.
    * @param toPay which must be delete in the DB.
    **/
   public void deleteToPayInDB(ToPay toPay) {
	   String deleteSql = "delete from toPay where id = " + toPay.getId();
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(deleteSql);
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("ToPay deleted in DB => " + toPay.toString());
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   /** 
    * Used to delete specific race in DB.
    * @param race which must be delete in the DB.
    **/
   public void deleteRaceInDB(Race race) {
	   String deleteSql = "delete from race where id = " + race.getId();
	   
	   try {
		   
		   PreparedStatement pstmt = conn.prepareStatement(deleteSql);
		   
		   //TODO: not work here! Async process...
		   //execute insert
		   if(pstmt.execute()) {
			   getPersonFromDB();
			   System.out.println("Race deleted in DB => " + race.toString());
		   }
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
}
