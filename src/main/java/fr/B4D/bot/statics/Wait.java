package fr.B4D.bot.statics;

import java.time.Duration;
import java.time.Instant;

import javax.swing.JOptionPane;

import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/** La classe {@code Wait} permet au bot d'attendre.<br><br>
 * Elle gère la mise en pause et l'arret du programme.
 */
public final class Wait {
	
	private Thread current;
	
	  /*************/
	 /** SETTERS **/
	/*************/
	
	/** Permet de mettre le bot sur pause.
	 * 
	 */
	public void suspend() {
		current.interrupt();
	}

	  /*************/
	 /** METHODS **/
	/*************/

	/** Attend pendant un certain temps
	 * @param time - Temps d'attente en millisecondes.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public void sleep(long time) throws StopProgramException, CancelProgramException {

		if(current == null)
			current = Thread.currentThread();
		
		Instant startTime = Instant.now();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			setPause();
			Instant currentTime = Instant.now();
			long remainingTime = time-Duration.between(startTime, currentTime).toMillis();
			if(remainingTime > 0)
				sleep(remainingTime);
		}
	}
	
	/** Attend pendant un certain temps sur un objet.
	 * @param object - Objet sur lequel attendre.
	 * @param time - Temps d'attente en millisecondes.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public void waitOnObject(Object object, long time) throws StopProgramException, CancelProgramException {
		
		if(current == null)
			current = Thread.currentThread();
		
		Instant startTime = Instant.now();
		try {
			object.wait(time);
		} catch (InterruptedException e) {
			setPause();
			Instant currentTime = Instant.now();
			long remainingTime = time-Duration.between(startTime, currentTime).toMillis();
			if(remainingTime > 0)
				waitOnObject(object, remainingTime);
		}
	}
	
	/** Affiche un message popUp demandant au joueur si il veut stopper, interrompre ou continuer le programme
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public void setPause() throws StopProgramException, CancelProgramException {
		Object[] options = {"Continuer", "Stopper", "Interrompre"};
		int response = JOptionPane.showOptionDialog(null, "Le bot est sur pause. Appuyez sur \"Continuer\" pour reprendre le programme, sur \"Stop\" pour arreter le programme et sur \"Interrompre\" pour forcer l'arret.", "Pause", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);						
		if(response == JOptionPane.NO_OPTION)
			throw new StopProgramException();
		else if(response == JOptionPane.CANCEL_OPTION)
			throw new CancelProgramException();
	}
}
