package fr.B4D.bot;

public class CannotGetPositionException extends Exception{

	private static final long serialVersionUID = -2949855971863227080L;

	public CannotGetPositionException()
    {
    	super("Can not get the current position.");
    }
}
