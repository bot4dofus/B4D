package fr.B4D.building.hdv;

import java.awt.Point;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.IndoorBuilding;
import fr.B4D.dofus.items.Stack;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/** The class {@code HDV} represents an HDV on the map.<br><br>
 * This class extends {@code IndoorBuilding}.
 * An HDV has a characterPosition position.
 */
public class HDV extends IndoorBuilding{

	//public static final HDV ASTRUB = new HDV(new Point(4,-18), Arrays.asList(new PointF(0.6552, 0.3683)), Arrays.asList(new PointF(0.3048, 0.6776)), new PointF(0.632, 0.4162));
	
	private Boolean opened;
	private Boolean BuyMode;
	private PointF tablePosition;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Builder of the {@code HDV} class.
	 * @param position - Position of the HDV on the map.
	 * @param inPoints - List of points to get into the building.
	 * @param outPoints - List of points to get out of the building.
	 * @param tablePosition - Position of the table on the screen.
	 */
	public HDV(Point position, List<PointF> inPoints, List<PointF> outPoints, PointF tablePosition) {
			super( position, inPoints, outPoints);
			this.tablePosition = tablePosition;
			this.opened = Boolean.FALSE;
			this.BuyMode = true;
	}

	  /***************/
	 /** GET & SET **/
	/***************/
	
	/** Returns the position of the table on the screen.
	 * @return Position of the table.
	 */
	public PointF getCharacterPosition() {
		return tablePosition;
	}
	
	/**
	 * Opens the HDV.
	 * @param person - Person which open the bank.
	 * @return List of stacks in the bank, {@code null} if not opened.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException If a B4D exception has been raised.
	 */
	
	public void open(Person person) throws StopProgramException, CancelProgramException, B4DException {
		super.enter(person);
		
		if(!opened) {
			B4D.mouse.leftClick(tablePosition, false, 10000);
			//Wait for the HDV socket to be received
			opened = Boolean.TRUE;
		}
	}
	
	/**
	 * Closes the HDV.
	 * @param person - Person which open the HDV.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException If a B4D exception has been raised.
	 */
	public void close(Person person) throws StopProgramException, CancelProgramException, B4DException {
		if(opened) {
			B4D.mouse.leftClick(new PointF(0.9832, 0.0828), false, 2000);
			//Wait for the HDV socket to be received
			opened = Boolean.FALSE;
		}
	}
	
	public void setBuyMode() throws StopProgramException, CancelProgramException{
		B4D.mouse.leftClick(new PointF(0.2856, 0.1058), false);
		BuyMode = Boolean.TRUE;
	}
	
	public void setSaleMode() throws StopProgramException, CancelProgramException{
		B4D.mouse.leftClick(new PointF(0.436, 0.1038), false);
		BuyMode = Boolean.FALSE;
	}
	
	public List<Stack> research(String name){
		//Clear the field
		//Type the name
		//Wait for the response
		return null;
	}
}
