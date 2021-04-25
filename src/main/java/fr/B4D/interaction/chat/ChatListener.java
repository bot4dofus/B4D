package fr.B4D.interaction.chat;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * Listener defining what to do when a message respect the filter criteria.
 * 
 * @author Lucas
 *
 */
public interface ChatListener {
	
	/**
	 * Process the messages respecting the filter criteria..
	 * @param message - Message received.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void messageReceived(Message message) throws StopProgramException, CancelProgramException, B4DException;
}
