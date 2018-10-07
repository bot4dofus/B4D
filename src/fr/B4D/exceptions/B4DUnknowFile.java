package fr.B4D.exceptions;

public class B4DUnknowFile extends Exception{

	private static final long serialVersionUID = -3335166258015974544L;

	public B4DUnknowFile()
    {
    	super("The file does not exist.");
    }
}
