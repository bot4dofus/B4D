package fr.B4D.bot;

/**
 * Specifies that a B4D exceptions as occurred.
 * 
 * @author Lucas
 *
 */
public class B4DException extends Exception{

	private static final long serialVersionUID = -2949855971863227080L;
	
	/**
	 * Constructs a {@code B4DException}.
	 */
	public B4DException() {
    	super();
    }
	
	/**
	 * Constructs a {@code B4DException}.
     * @param cause - Cause of the exception.
	 */
	public B4DException(String cause)
    {
    	super(cause);
    }

    /**
	 * Constructs a {@code B4DException}.
     * @param cause - Cause of the exception.
     */
    public B4DException(Throwable cause) {
        super(cause);
    }
    
    /**
	 * Constructs a {@code B4DException}.
     * @param message - Message of the exception.
     * @param cause - Cause of the exception.
     */
    public B4DException(String message, Throwable cause) {
        super(message, cause);
    }

}
