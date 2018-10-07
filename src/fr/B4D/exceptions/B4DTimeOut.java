package fr.B4D.exceptions;

public class B4DTimeOut extends Exception{

	private static final long serialVersionUID = -7480048042802430440L;

	public B4DTimeOut()
    {
    	super("A waiting function timed out.");
    }
}
