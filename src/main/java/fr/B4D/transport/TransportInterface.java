package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * Interface representing a transport.
 * 
 * @author Lucas
 *
 */
public interface TransportInterface extends Serializable {
	
	/**
	 * Cost of using a zaap.
	 */
	final double ZAAP_COST = 10;
	
	/**
	 * Cost of using a zaapi.
	 */
	final double ZAAPI_COST = 1;
	
	/**
	 * Cost of using a booster potion.
	 */
	final double BOOSTER_POTION_COST = 3;
	
	/**
	 * Cost of using a Bonta potion.
	 */
	final double BONTA_POTION_COST = 3;
	
	/**
	 * Cost of using a Brakmar potion.
	 */
	final double BRAKMAR_POTION_COST = 3;
	
	/**
	 * Cost of using an Almanax potion.
	 */
	final double ALMANAX_POTION_COST = 5;
	
	/**
	 * Cost of walking to the next map.
	 */
	final double WALK_COST = 1;
	
	/**
	 * Destination of the Bonta potion.
	 */
	final Point BONTA_POTION_DESTINATION = new Point(-33, -56);
	
	/**
	 * Destination of the Brakmar potion.
	 */
	final Point BRAKMAR_POTION_DESTINATION = new Point(-26,36);

	/**
	 * Method to implement defining how to use the transport method.
	 * @param destination - Destination of the transport.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
	 */
	public void goTo(Point destination) throws StopProgramException, CancelProgramException, B4DException;
}
