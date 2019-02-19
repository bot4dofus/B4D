package fr.B4D.dofus;

import fr.B4D.bot.B4DException;

/** Signal que l'objet recherché sur la map n'a pas pu être trouvé.
*/
public class CannotFindException extends B4DException{

	private static final long serialVersionUID = -2949855971863227080L;

	/** Constructeur de l'exception {@code CannotFindException}.
	 */
	public CannotFindException()
    {
    	super("Cannot found the object on the map.");
    }
}
