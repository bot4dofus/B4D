package fr.B4D.program;

import fr.B4D.bot.B4DException;

/** 
 * Specifies that the program is canceled.
 * The {@code outro} method will not be called.
 * 
 * @author Lucas
 *
 */
public class CancelProgramException extends B4DException{

	private static final long serialVersionUID = 6971038782335799708L;

	/**
	 * Constructs a {@code CancelProgramException}.
	 */
	public CancelProgramException() {
    	super();
    }

	/**
	 * Constructs a {@code CancelProgramException} exception.
	 * @param cause - Cause of the exception.
	 */
	public CancelProgramException(String cause) {
    	super("Le programme a été intérompu pour la raison suivante :\n" + cause);
    }
}
