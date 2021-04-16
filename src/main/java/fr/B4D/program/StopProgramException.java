package fr.B4D.program;

import fr.B4D.bot.B4DException;

/** 
 * Specifies that the program is stopped.
 * The {@code outro} method will be called.
 * 
 * @author Lucas
 *
 */
public class StopProgramException extends B4DException{

	private static final long serialVersionUID = 6971038782335799708L;

	/**
	 * Constructs a {@code StopProgramException}.
	 */
	public StopProgramException()
    {
    	super("Program stopped.");
    }
}
