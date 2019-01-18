package fr.B4D.exceptions;

public class B4DExchangeCanceled extends Exception{

	private static final long serialVersionUID = 1721525819432312605L;

	public B4DExchangeCanceled()
    {
    	super("The exchange have been canceled.");
    }
}
