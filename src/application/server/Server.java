package application.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import application.server.managers.BoatsManager;
import application.server.managers.PersonManager;
import application.server.managers.RacesManager;
import application.server.managers.SqlManager;
import application.models.Boat;
import application.models.ClubStaff;
import application.models.Credentials;
import application.models.Partner;
import application.models.Race;
import application.models.Registration;

/**
 * This class is the main class of the server.
 * 
 * @author Federico Canali
 *
 */
public class Server {
	private static final int COREPOOL = 5;
	private static final int MAXPOOL = 10;
	private static final long IDLETIME = 5000;
	
	private static int SERVER_PORT = 8080;
	private ServerSocket socket;
	private ThreadPoolExecutor pool;
	
	/**
	 * Public constructor.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. Thisclass is the general class of exceptions produced by failed orinterrupted I/O operations.
	 **/
	public Server() throws IOException{
		this.socket = new ServerSocket(SERVER_PORT);
	}
	
	private void run() {
		System.out.println("Server is online!");
		
		this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME
				, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		while(true) {
			try {
				Socket s = this.socket.accept();
				
				this.pool.execute(new ServerThread(s));
			} catch(Exception e) {
				break;
			}
		}
		
		this.pool.shutdown();
	}
	
	
	/**
	 * This method is used to close socket connection. 
	 **/
	public void close() {
		try {
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the pool
	 */
	public ThreadPoolExecutor getPool() {
		return pool;
	}
	
	
	/**
	 * Main function that run Server.
	 * @param args.
	 * @throws ParseException Signals that an error has been reached unexpectedlywhile parsing.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. Thisclass is the general class of exceptions produced by failed orinterrupted I/O operations.
	 **/
	public static void main(final String[] args) throws IOException, ParseException{
		
		initDefaultValues();
		
		new Server().run();
	}
	
	/* used to initialize some default values */
	private static void initDefaultValues() throws ParseException {
		SqlManager.getInstance().getPersonFromDB();
		SqlManager.getInstance().getAllPayments();
		
		if(PersonManager.getInstance().getPeople().isEmpty()){
			ClubStaff cs = new ClubStaff("Federico", "Canali", "Parma", "123456789", new Credentials("fede", "f"));
	    	PersonManager.getInstance().addPerson(cs);
	    	
	    	Partner p = new Partner("Jhon", "Doe", "Parma", "123456789", new Credentials("jo", "d"));
	    	PersonManager.getInstance().addPerson(p);
	    	Partner p2 = new Partner("Mario", "Rossi", "Roma", "123454789", new Credentials("mario", "r"));
	    	PersonManager.getInstance().addPerson(p2);
	    	
	    	Boat b1 = new Boat("Crociera", 24, 2);
	    	Boat b2 = new Boat("Velina", 2, 2);
	    	Boat b3 = new Boat("Santa Maria", 3, 2);
	    	BoatsManager.getInstance().addNewBoat(b1);
	    	BoatsManager.getInstance().addNewBoat(b2);
	    	BoatsManager.getInstance().addNewBoat(b3);
	           
			Calendar calendar = Calendar.getInstance();
			calendar.set(2021, 9, 22);
			 
			Race r1 = new Race("Cornovalia", new Date(calendar.getTimeInMillis()), 200);
			RacesManager.getInstance().addRace(r1);
			
			calendar.set(2021, 11, 25);
			Race r2 = new Race("Liguria", new Date(calendar.getTimeInMillis()), 100);
			RacesManager.getInstance().addRace(r2);
			
			//set right id before add it into DB
			b1.setId(1);
			b2.setId(2);
			r1.setId(1);
			
			RacesManager.getInstance().registerBoatToRace(new Registration(r1, b1));
			RacesManager.getInstance().registerBoatToRace(new Registration(r1, b2));
		}
	}
}
