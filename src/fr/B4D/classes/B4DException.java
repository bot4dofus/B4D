package fr.B4D.classes;

@SuppressWarnings("serial")
public class B4DException extends Exception{

	private Raison raison;

    public B4DException(Raison raison)
    {
    	super();
        this.raison = raison;
    }

	  /***********/
	 /* GETTERS */
	/***********/
    
    public Raison getRaison()
    {
    	return this.raison;
    }
    public void Raison() {
    	switch(getRaison()) {
    		case TimeOut:
    			System.out.println("A waiting function timed out.");
    			break;
    		case PlacerSouris:
    			System.out.println("Can't move the mouse.");
    			break;
    		case ClicDroit:
    			System.out.println("Right click impossible.");
    			break;
    		case ClicGauche:
    			System.out.println("Left click impossible.");
    			break;
    		case OCR:
    			System.out.println("OCR impossible.");
    			break;
    		case Clavier:
    			System.out.println("Impossible to use the keyboard.");
    			break;
    		case PressePapier:
    			System.out.println("Impossible to use the ClipBoard.");
    			break;
    		case Pixel:
    			System.out.println("Impossible to get pixel color.");
    			break;
    	}
    }

	  /***************/
	 /* ENUMERATION */
	/***************/
    
    public enum Raison{
    	TimeOut,
    	PlacerSouris,
    	ClicDroit,
    	ClicGauche,
    	OCR,
    	Clavier,
    	PressePapier,
    	Pixel,
    }
}
