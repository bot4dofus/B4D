package fr.B4D.transport;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/** Interface représentant un transport.
 */
public interface TransportInterface extends Serializable {
	final double zaapCost = 10;
	final double zaapiCost = 1;
	final double boosterPotionCost = 3;
	final double bontaPotionCost = 3;
	final double brakmarPotionCost = 3;
	final double almanaxPotionCost = 5;
	final double walkCost = 1;
	
	final Point bontaPotionDestination = new Point(-33, -56);
	final Point brakmarPotionDestination = new Point(-26,36);

	/** Permet d'utiliser le transport.
	 * @param destination - Destination du transport.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws AWTException Si un problème de souris survient.
	 */
	public void goTo(Point destination) throws StopProgramException, CancelProgramException, B4DException, AWTException;
}
