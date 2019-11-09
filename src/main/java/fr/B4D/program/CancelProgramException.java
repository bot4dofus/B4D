package fr.B4D.program;

import fr.B4D.bot.B4DException;

/** Signal que le programme a été annulé.
 * La fonction de sortie (outro) ne sera pas exécuté.
 */
public class CancelProgramException extends B4DException{

	private static final long serialVersionUID = 6971038782335799708L;

	/** Constructeur de l'exception {@code CancelProgramException}.
	 */
	public CancelProgramException() {
    	super();
    }
	
	/** Constructeur de l'exception {@code CancelProgramException}.
	 * @param raison - Raison pour laquelle le programme à été annulé.
	 */
	public CancelProgramException(String raison) {
    	super("Le programme a été intérompu pour la raison suivante :\n" + raison);
    }
}
