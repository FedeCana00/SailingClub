package application.communications;

/**
 * Class representing the server response for the client.
 * 
 * @author Federico Canali
 *
 */
public class Response extends Message{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor. 
	 */
	public Response() {}

	/**
	 * {@inheritDoc} 
	 **/
	public Response(String action, Object value) {
		super(action, value);
	}

}
