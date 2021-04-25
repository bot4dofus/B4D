package fr.B4D.building.hdv;

import java.awt.Point;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.IndoorBuilding;
import fr.B4D.dofus.items.Item;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/**
 * The class {@code HDV} represents an HDV on the map.<br><br>
 * This class extends {@code IndoorBuilding}.
 * An HDV has a characterPosition position.
 * 
 * @author Lucas
 *
 */
public class HDV extends IndoorBuilding{

	/**
	 * The Astrub HDV.
	 */
	public static final HDV ASTRUB = new HDV(new Point(4,-17), null, null, new PointF(0.3809, 0.436));
	
	/**
	 * Specify whether the HDV is opened or not.
	 */
	private Boolean opened;
	
	/**
	 * Specify whether the HDV is in buy mode.
	 */
	private Boolean buyMode;
	
	/**
	 * Location of the table in relative coordinates.
	 */
	private PointF tablePosition;
	
	/**
	 * Builder of the {@code HDV} class.
	 * @param position - Position of the HDV on the map.
	 * @param inPoints - List of points to get into the building.
	 * @param outPoints - List of points to get out of the building.
	 * @param tablePosition - Position of the table on the screen.
	 */
	public HDV(Point position, List<PointF> inPoints, List<PointF> outPoints, PointF tablePosition) {
			super(position, inPoints, outPoints);
			
			if(tablePosition == null)
				throw new IllegalArgumentException("The HDV table position cannot be null.");
			
			this.tablePosition = tablePosition;
			this.opened = Boolean.FALSE;
			this.buyMode = true;
	}
	
	/**
	 * Returns the position of the table on the screen.
	 * @return Position of the table.
	 */
	public PointF getCharacterPosition() {
		return tablePosition;
	}
	
	/**
	 * Checks whether the HDV is opened or not.
	 * @return {@code true} if the HDV is opened, {@code false} otherwise.
	 */
	public boolean isOpened() {
		return opened;
	}
	
	/**
	 * Checks whether the HDV is in buy mode.
	 * @return {@code true} if the HDV is in buy mode, {@code false} otherwise.
	 */
	public boolean isBuyMode() {
		return buyMode;
	}
	
	/**
	 * Opens the HDV.
	 * @param person - Person which open the bank.
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
			B4D.mouse.leftClick(new PointF(0.9732, 0.063), false, 2000);
			//Wait for the HDV socket to be received
			opened = Boolean.FALSE;
		}
	}
	
	/**
	 * Set the mode to buy items.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void setBuyMode() throws StopProgramException, CancelProgramException, B4DException{
		B4D.mouse.leftClick(new PointF(0.2856, 0.1058), false);
		buyMode = Boolean.TRUE;
	}
	
	/**
	 * Set the mode to sale items.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void setSaleMode() throws StopProgramException, CancelProgramException, B4DException{
		B4D.mouse.leftClick(new PointF(0.436, 0.1038), false);
		buyMode = Boolean.FALSE;
	}
	
	/**
	 * Research for items in the HDV.
	 * @param name - Name of the item to find.
	 * @return - Found items.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws StopProgramException if the program is stopped.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public List<Item> research(String name) throws StopProgramException, CancelProgramException, B4DException{
		B4D.mouse.leftClick(new PointF(0.1924, 0.1732), false);		//Clear the field
		B4D.mouse.leftClick(new PointF(0.127, 0.1752), false);		//Click on the field
		B4D.keyboard.writeKeyboard(name); 							//Type the name
		//Wait for the response, no socket on research result !!! How do they do this ?
		return null;
	}
}
