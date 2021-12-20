package application.communications;

/**
 * @author Federico Canali
 *
 */
public class Request extends Message{
	/* CONSANTS TYPE OF REQUEST */
	
	/**
	 * {@inheritDoc} 
	 **/
	public Request(String action, Object value) {
		super(action, value);
	}

}
