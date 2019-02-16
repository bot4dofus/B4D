package fr.B4D.program;

public class StopProgramException extends Exception{

	private static final long serialVersionUID = 6971038782335799708L;

	public StopProgramException()
    {
    	super("Program stoped");
    }
}
