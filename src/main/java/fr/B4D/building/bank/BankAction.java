package fr.B4D.building.bank;

import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

public abstract class BankAction {
	
	/**
	 * Do the action.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public abstract void doAction() throws StopProgramException, CancelProgramException;
}
