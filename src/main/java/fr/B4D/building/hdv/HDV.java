package fr.B4D.building.hdv;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.IndoorBuilding;
import fr.B4D.dofus.items.Item;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/** The class {@code HDV} represents an HDV on the map.<br><br>
 * This class extends {@code IndoorBuilding}.
 * An HDV has a characterPosition position.
 */
public class HDV extends IndoorBuilding{

	/**
	 * The Astrub HDV.
	 */
	public static final HDV ASTRUB = new HDV(new Point(4,-18), Arrays.asList(new PointF(0.6552, 0.3683)), Arrays.asList(new PointF(0.3048, 0.6776)), new PointF(0.632, 0.4162));
	
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
			this.buyMode = true;
	}

	  /***************/
	 /** GET & SET **/
	/***************/
	
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
			B4D.mouse.leftClick(new PointF(0.9832, 0.0828), false, 2000);
			//Wait for the HDV socket to be received
			opened = Boolean.FALSE;
		}
	}
	
	/**
	 * Set the mode to buy items.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 */
	public void setBuyMode() throws StopProgramException, CancelProgramException{
		B4D.mouse.leftClick(new PointF(0.2856, 0.1058), false);
		buyMode = Boolean.TRUE;
	}
	
	/**
	 * Set the mode to sale items.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 */
	public void setSaleMode() throws StopProgramException, CancelProgramException{
		B4D.mouse.leftClick(new PointF(0.436, 0.1038), false);
		buyMode = Boolean.FALSE;
	}
	
	/**
	 * Research for items in the HDV.
	 * @param name - Name of the item to find.
	 * @return - Found items.
	 */
	public List<Item> research(String name){
		//Clear the field
		//Type the name
		//Wait for the response
		return null;
	}
}
