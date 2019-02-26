package fr.B4D.program;

import fr.B4D.bot.B4DException;

/** Signal que le programme a été stoppé.
 * La fonction de sortie (outro) sera exécuté.
 */
public class StopProgramException extends B4DException{

	private static final long serialVersionUID = 6971038782335799708L;

	/** Constructeur de l'exception {@code StopProgramException}.
	 */
	public StopProgramException()
    {
    	super("Program stoped", false);
    }
}
