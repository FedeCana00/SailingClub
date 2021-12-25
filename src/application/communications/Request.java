package application.communications;

/**
 * Class representing the client's request for the server.
 * 
 * @author Federico Canali
 *
 */
public class Request extends Message{
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc} 
	 **/
	public Request(String action, Object value) {
		super(action, value);
	}

}
