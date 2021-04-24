package fr.B4D.bot.statics;

import java.time.Duration;
import java.time.Instant;

import javax.swing.JOptionPane;

import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * The {@code Wait} class is used to access to the waiting methods.<br><br>
 * It manages the cancellation of the program.
 * 
 * @author Lucas
 *
 */
public final class Wait {
	
	/**
	 * Current thread.
	 */
	private Thread current;
	
	/**
	 * Pause the bot.
	 */
	public void suspend() {
		current.interrupt();
	}

	/**
	 * Wait for a given time in ms.
	 * @param time - Time to wait iin ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
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
	
	/**
	 * Wait for a given time on an object.
	 * @param object - Object on which wait.
	 * @param time - Time to wait in ms, {@code 0} to wait indefinitely.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public void waitOnObject(Object object, long time) throws StopProgramException, CancelProgramException {
		
		if(current == null)
			current = Thread.currentThread();
		
		Instant startTime = Instant.now();
		try {
			synchronized(object){
				object.wait(time);
			}
		} catch (InterruptedException e) {
			setPause();
			Instant currentTime = Instant.now();
			long remainingTime = time-Duration.between(startTime, currentTime).toMillis();
			if(remainingTime > 0)
				waitOnObject(object, remainingTime);
		}
	}
	
	/**
	 * Displays a popup message asking to the user if he wants to pause, cancel or continue the program.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
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
