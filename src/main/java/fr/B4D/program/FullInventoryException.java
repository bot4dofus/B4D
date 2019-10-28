package fr.B4D.program;

import fr.B4D.bot.B4DException;

/** Signal que l'inventaire de joueur est plein et donc qu'il faut le vider.
 *
 */
public class FullInventoryException extends B4DException{

	private static final long serialVersionUID = 5127139578773861176L;

	/** Constructeur de l'exception {@code FullInventoryException}.
	 */
	public FullInventoryException()
    {
    	super("Your inventory is full.", false);
    }
}
