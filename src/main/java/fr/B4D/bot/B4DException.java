package fr.B4D.bot;

/** Signal qu'une exception de type B4D a eu lieu.
 */
public class B4DException extends Exception{

	private static final long serialVersionUID = -2949855971863227080L;
	
	/** Constructeur de l'exception {@code B4DException}.
	 * Aucun rapport d'erreur ne pourra être envoyé.
	 * Cela est identique à {@code B4DException(false)}.
	 */
	public B4DException() {
    	super();
    }
	
	/** Constructeur de l'exception {@code B4DException}.
	 * Un rapport d'erreur pourra être envoyé.
	 * Cela est identique à {@code B4DException(raison, true)}.
	 * @param raison - Raison de l'exception.
	 */
	public B4DException(String raison)
    {
    	super(raison);
    }

    public B4DException(Throwable cause) {
        super(cause);
    }
    
    public B4DException(String message, Throwable cause) {
        super(message, cause);
    }

}
