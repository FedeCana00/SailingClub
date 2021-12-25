package application.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

import application.communications.Request;
import application.communications.Response;
import application.server.managers.ServerMessageManager;

/**
 * This class handles requests to the server on separate threads.
 * 
 * @author Federico Canali
 *
 */
public class ServerThread implements Runnable{
	private static final int MAX = 10;
	private static final long SLEEPTIME = 200;
	
	private Server server;
	private Socket socket;
	private String id;
	
	/**
	 * @param s is the server.
	 * @param c is the socket.
	 */
	public ServerThread(final Server s, final Socket c) {
		this.server = s;
		this.socket = c;
		this.id = String.valueOf(this.hashCode());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		ObjectInputStream is = null;
		ObjectOutput os = null;
		
		try {
			
			is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		while(true) {
			
			try {
				
				Object i = is.readObject();
				
				if(i instanceof Request) {
					Request request = (Request) i;
					
					//here process request in manager
					Response response = ServerMessageManager.getInstance().processRequest(request);
					
					System.out.println("Thread " + id + " receives: " + request.getAction() + " action from its client.");
					Thread.sleep(SLEEPTIME);
					
					if(os == null) {
						os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
					}
					
					System.out.println("Thread " + id + " replies to " + response.getAction() + " action");
					
					os.writeObject(response);
					os.flush();
					
					socket.close();
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
	}

}
