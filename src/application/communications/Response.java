package application.communications;

/**
 * @author Federico Canali
 *
 */
public class Response extends Message{
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc} 
	 **/
	public Response(String action, Object value) {
		super(action, value);
	}

}
