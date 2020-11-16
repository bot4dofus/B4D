package fr.B4D.socket;

import fr.B4D.bot.B4DException;

/**
 * The class {@code PatternNotFoundException} occurs when a pattern is not found.
 * 
 * @author Lucas
 *
 */
public class PatternNotFoundException extends B4DException{

	private static final long serialVersionUID = -1639313215176284377L;

	/**
	 * Constructs a {@code PatternNotFoundException}.
	 */
	public PatternNotFoundException() {
		super("Could find the pattern.");
	}
	
	/** 
	 * Constructs a {@code PatternNotFoundException}.
     * @param cause - Cause of the exception.
	 */
	public PatternNotFoundException(String cause)
    {
    	super(cause);
    }
}
