package fr.B4D.interaction;

import java.awt.Point;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/**
 * The {@code Status} represents a status in the game.<br><br>
 * A status is defined by a name and a relative location in the menu.
 * 
 * @author Lucas
 *
 */
public class Status {
	
	/**
	 * Status available. You can talk, exchange and fight.
	 */
	public final static Status AVAILABLE = new Status("Disponible", new PointF(0.0131, -0.1008 + 0*0.0216));
	
	/**
	 * Status absent. You are away from keyboard.
	 */
	public final static Status ABSENT = new Status("Absent", new PointF(0.0131, -0.1008 + 1*0.0216));
	
	/**
	 * Status private. Friends only.
	 */
	public final static Status PRIVATE = new Status("Privé", new PointF(0.0131, -0.1008 + 3*0.0216));
	
	/**
	 * Status solo. Do not disturb.
	 */
	public final static Status SOLO = new Status("Solo", new PointF(0.0131, -0.1008 + 4*0.0216));
	
	/**
	 * Location of the status menu button.
	 */
	private static Point statusMenuPosition;
	

	/**
	 * Sets the status button location.
	 * @param statusMenuPosition - Location of the status button in simple coordinates.
	 */
	public static void setStatusMenuPosition(Point statusMenuPosition) {
		Status.statusMenuPosition = statusMenuPosition;
	}
	
	/**
	 * Name of the status.
	 */
	private String name;
	
	/**
	 * Relative location of the status in the menu.
	 */
	private PointF relativPosition;
	
	/** 
	 * Constructor of the {@code Status} class.
	 * @param name - Name of the status.
	 * @param relativPosition - Location of the status in relative to the status button in relative coordinates.
	 */
	public Status(String name, PointF relativPosition) {
		this.name = name;
		this.relativPosition = relativPosition;
	}
	
	/**
	 * Returns the name of the status.
	 * @return Name of the status.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Defines the status for the current payer.
	 * @return {@code true} if the status is set, {@code false} otherwise.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public boolean setStatus() throws StopProgramException, CancelProgramException, B4DException {
		if(statusMenuPosition != null) {
			PointF menu = B4D.converter.toPointF(statusMenuPosition);
			B4D.mouse.leftClick(menu, false, 200);		//Ouvre le menu des status
			PointF checkPosition = new PointF(menu.x + relativPosition.x, menu.y + relativPosition.y);		
			B4D.mouse.leftClick(checkPosition, false, 100);
			return true;
		}
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
