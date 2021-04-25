package fr.B4D.transport.transports;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.Transport;
import fr.B4D.utils.PointF;

/** La classe {@code Potion} représente une potion de transport.<br><br>
 * Cette classe étend la classe {@code Transport}.
 * 
 * @author Lucas
 *
 */
public class Potion extends Transport implements Serializable{
	
	private static final long serialVersionUID = -4986470216030169585L;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	/** Constructeur de la classe {@code Potion}.
	 * @param name - Nom de la potion.
	 * @param position - Position de l'utilisation de la potion sur la carte.
	 * @param positionF - Position relative de la potion.
	 * @param weigth - Poid de la potion.
	 */
	public Potion(String name, Point position, PointF positionF, double weigth) {
		super(name, position, positionF, weigth);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	/* (non-Javadoc)
	 * @see fr.B4D.transport.TransportInterface#goTo(java.awt.Point)
	 */
	public void goTo(Point destination) throws StopProgramException, CancelProgramException, B4DException {		
		B4D.mouse.doubleLeftClick(super.getPositionF(), false);
	    B4D.screen.waitForMap(20000);
	}
}
