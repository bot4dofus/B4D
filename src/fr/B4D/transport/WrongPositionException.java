package fr.B4D.transport;

public class WrongPositionException extends Exception{

	private static final long serialVersionUID = 6562626650414708550L;

	public WrongPositionException()
    {
    	super("Wrong position.");
    }
}
