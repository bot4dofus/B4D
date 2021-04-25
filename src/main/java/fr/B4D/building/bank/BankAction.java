package fr.B4D.building.bank;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * The class {@code BankAction} represents an action to do on a bank.
 * 
 * @author Lucas
 *
 */
public abstract class BankAction {
	
	/**
	 * Do the action.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException If an unexpected error occurred.
	 */
	public abstract void doAction() throws StopProgramException, CancelProgramException, B4DException;
}
