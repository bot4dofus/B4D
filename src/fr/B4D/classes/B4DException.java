package fr.B4D.classes;

@SuppressWarnings("serial")
public class B4DException extends Exception{

	private Reason reason;

    public B4DException(Reason reason)
    {
    	super();
        this.reason = reason;
    }

	  /***********/
	 /* GETTERS */
	/***********/
    
    public Reason getReason()
    {
    	return this.reason;
    }
    public void Reason() {
    	switch(getReason()) {
    		case TimeOut:
    			System.out.println("A waiting function timed out.");
    			break;
    		case CannotFind:
    			System.out.println("Cannot found the object on the map.");
    			break;
    		case UnknowFile:
    			System.out.println("The file does not exist.");
    			break;
    	}
    }

	  /***************/
	 /* ENUMERATION */
	/***************/
    
    public enum Reason{
    	TimeOut, CannotFind, UnknowFile,
    }
}
