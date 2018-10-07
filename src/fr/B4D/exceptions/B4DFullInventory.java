package fr.B4D.exceptions;

public class B4DFullInventory extends Exception{

	private static final long serialVersionUID = 5127139578773861176L;

	public B4DFullInventory()
    {
    	super("Your inventory is full.");
    }
}
