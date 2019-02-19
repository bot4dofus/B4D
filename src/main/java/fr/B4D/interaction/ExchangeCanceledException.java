package fr.B4D.interaction;

import fr.B4D.bot.B4DException;

/** Signal que l'échange en cours à été annulé.
 *
 */
public class ExchangeCanceledException extends B4DException{

	private static final long serialVersionUID = 1721525819432312605L;

	/** Constructeur de l'exception {@code ExchangeCanceled}.
	 * 
	 */
	public ExchangeCanceledException()
    {
    	super("The exchange have been canceled.");
    }
}
