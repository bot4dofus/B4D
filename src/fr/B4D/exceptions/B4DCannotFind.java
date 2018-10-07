package fr.B4D.exceptions;

public class B4DCannotFind extends Exception{

	private static final long serialVersionUID = -2949855971863227080L;

	public B4DCannotFind()
    {
    	super("Cannot found the object on the map.");
    }
}
