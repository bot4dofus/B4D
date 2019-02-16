package fr.B4D.program;

public class FullInventoryException extends Exception{

	private static final long serialVersionUID = 5127139578773861176L;

	public FullInventoryException()
    {
    	super("Your inventory is full.");
    }
}
