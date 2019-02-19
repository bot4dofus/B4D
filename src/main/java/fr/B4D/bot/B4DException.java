package fr.B4D.bot;

/** Signal qu'une exception de type B4D a eu lieu.
 */
public class B4DException extends Exception{

	private static final long serialVersionUID = -2949855971863227080L;
	
	/** Constructeur de l'exception {@code B4DException}.
	 */
	public B4DException() {
    	super();
    }
	
	/** Constructeur de l'exception {@code B4DException}.
	 * @param raison - Raison de l'exception.
	 */
	public B4DException(String raison)
    {
    	super(raison);
    }
}
