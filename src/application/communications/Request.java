package application.communications;

/**
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
