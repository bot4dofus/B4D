package fr.B4D.dofus;

public class CannotFindException extends Exception{

	private static final long serialVersionUID = -2949855971863227080L;

	public CannotFindException()
    {
    	super("Cannot found the object on the map.");
    }
}
