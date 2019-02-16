package fr.B4D.program;

public class CancelProgramException extends Exception{

	private static final long serialVersionUID = 6971038782335799708L;

	public CancelProgramException() {
    	super();
    }
	
	public CancelProgramException(String message) {
    	super("Le programme a été intérompu pour la raison suivante :\n" + message);
    }
}
