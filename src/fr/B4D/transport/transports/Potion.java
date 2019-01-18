package fr.B4D.transport.transports;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.Configuration;
import fr.B4D.modules.B4DMouse;
import fr.B4D.modules.B4DWait;
import fr.B4D.transport.B4DCannotFind;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.transport.Transport;
import fr.B4D.utils.PointF;

public abstract class Potion extends Transport implements Serializable{
	
	private static final long serialVersionUID = -4986470216030169585L;

	/******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Potion(String name, Point position, PointF positionF, double weigth) {
		super(name, position, positionF, weigth);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void goTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition {
		if (!Configuration.getInstance().persons.get(0).position.equals(this.getPosition()))
			throw new B4DWrongPosition();
		
		B4DMouse.doubleLeftClick(super.getPositionF(), false);

		B4DWait.waitForMap();
		Configuration.getInstance().persons.get(0).position = destination;
	}
}
