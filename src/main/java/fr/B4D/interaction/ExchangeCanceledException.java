package fr.B4D.interaction;

import fr.B4D.bot.B4DException;

/**
 * Specifies that the exchange has been canceled, whether by the player or by the bot.
 * 
 * @author Lucas
 *
 */
public class ExchangeCanceledException extends B4DException{

	private static final long serialVersionUID = 1721525819432312605L;

	/**
	 * Constructor of the {@code ExchangeCanceledException}.
	 */
	public ExchangeCanceledException()
    {
    	super("The exchange have been canceled.");
    }
}
