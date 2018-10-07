package fr.B4D.exceptions;

public class B4DStopProgram extends Exception{

	private static final long serialVersionUID = 6971038782335799708L;

	public B4DStopProgram()
    {
    	super("The program have been stop.");
    }
}
