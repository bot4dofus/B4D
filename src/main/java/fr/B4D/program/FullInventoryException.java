package fr.B4D.program;

import fr.B4D.bot.B4DException;

/** 
 * Specifies that the player inventory is full.
 * 
 * @author Lucas
 *
 */
public class FullInventoryException extends B4DException{

	private static final long serialVersionUID = 5127139578773861176L;

	/**
	 * Constructs a {@code FullInventoryException}.
	 */
	public FullInventoryException()
    {
    	super("Your inventory is full.");
    }
}
