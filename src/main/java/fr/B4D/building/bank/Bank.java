package fr.B4D.building.bank;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.IndoorBuilding;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/** The class {@code Bank} represents a bank on the map.<br><br>
 * This class extends {@code IndoorBuilding}.
 * A bank has a banker position.
 */
public class Bank extends IndoorBuilding{

	public static final Bank ASTRUB = new Bank(new Point(4,-18), Arrays.asList(new PointF(0.6552, 0.3683)), Arrays.asList(new PointF(0.3048, 0.6776)), new PointF(0.632, 0.4162));
	
	private PointF bankerPosition;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Builder of the {@code Bank} class.
	 * @param position - Position of the bank on the map.
	 * @param inPoints - List of points to get into the building.
	 * @param outPoints - List of points to get out of the building.
	 * @param bankerPosition - Position of the banker on the screen.
	 */
	public Bank(Point position, List<PointF> inPoints, List<PointF> outPoints, PointF bankerPosition) {
			super( position, inPoints, outPoints);
			this.bankerPosition = bankerPosition;
	}

	  /***************/
	 /** GET & SET **/
	/***************/
	
	/** Returns the position of the banker on the screen.
	 * @return Position of the banker.
	 */
	public PointF getBankerPosition() {
		return bankerPosition;
	}
	
	  /********************/
	 /** PUBLIC METHODS **/
	/********************/

	/** Performs actions on the bank.
	 * @param person Person which go to the building.
	 * @param bankActions - Array of actions to realize.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException If a B4D exception has been raised.
	 */
	public void doActions(Person person, BankAction...bankActions) throws StopProgramException, CancelProgramException, B4DException {
		doActions(person, Arrays.asList(bankActions));
	}
	
	/** Performs actions on the bank.
	 * @param person Person which go to the building.
	 * @param bankActions - List of actions to realize.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException If a B4D exception has been raised.
	 */
	public void doActions(Person person, List<BankAction> bankActions) throws StopProgramException, CancelProgramException, B4DException {
		super.enter(person);
		B4D.mouse.leftClick(bankerPosition, false);
		B4D.mouse.leftClick(new PointF(0.444, 0.7305), false, 5000);
		for(BankAction bankAction:bankActions)
			bankAction.doAction();
		B4D.mouse.leftClick(new PointF(0.9832, 0.0828), false);
		super.exit(person);
	}
}
