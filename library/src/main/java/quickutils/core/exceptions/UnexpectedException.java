package quickutils.core.exceptions;

public class UnexpectedException extends AbstractException {
	
	private static final long serialVersionUID = -7051268210410092107L;
	
	/**
	 * @param message
	 * @param cause
	 */
	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public UnexpectedException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public UnexpectedException(Throwable cause) {
		super(cause);
	}
	
}
