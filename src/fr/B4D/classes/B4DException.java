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
    		case Mouse:
    			System.out.println("Cannot use the mouse;");
    			break;
    		case Keyboard:
    			System.out.println("Cannot use the keyboard.");
    			break;
    		case Clipboard:
    			System.out.println("Cannot use the clipBoard.");
    			break;
    		case OCR:
    			System.out.println("OCR impossible.");
    			break;
    		case Pixel:
    			System.out.println("Cannot get the pixel color.");
    			break;
    		case CannotFind:
    			System.out.println("Cannot found the object on the map.");
    			break;
    		case MacAdress:
    			System.out.println("Cannot get the mac adress.");
    			break;
    	}
    }

	  /***************/
	 /* ENUMERATION */
	/***************/
    
    public enum Reason{
    	TimeOut,
    	Mouse,
    	Keyboard,
    	Clipboard,
    	OCR,
    	Pixel,
    	CannotFind,
    	MacAdress,
    }
}
