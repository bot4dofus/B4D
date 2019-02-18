package fr.B4D.bot;

/** Signal que la position réelle du joueur n'a pas pu être récupérée.
 */
public class CannotGetPositionException extends Exception{

	private static final long serialVersionUID = -2949855971863227080L;
	
	/** Constructeur de l'exception {@code CannotGetPositionException}.
	 */
	public CannotGetPositionException()
    {
    	super("Can not get the current position.");
    }
}
