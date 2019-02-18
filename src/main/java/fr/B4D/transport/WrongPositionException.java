package fr.B4D.transport;

/** Signal le joueur n'est pas à la bonne position pour emprunter le transport.
 */
public class WrongPositionException extends Exception{

	private static final long serialVersionUID = 6562626650414708550L;

	/** Constructeur de l'exception {@code WrongPositionException}.
	 */
	public WrongPositionException()
    {
    	super("Wrong position.");
    }
}
