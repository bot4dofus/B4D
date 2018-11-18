package fr.B4D.classes.transport.transports;

import java.awt.AWTException;
import java.awt.Point;

import fr.B4D.classes.Bot;
import fr.B4D.classes.PointF;
import fr.B4D.classes.transport.Transport;
import fr.B4D.exceptions.B4DCannotFind;
import fr.B4D.exceptions.B4DWrongPosition;
import fr.B4D.modules.B4DMouse;
import fr.B4D.modules.B4DWait;

public abstract class Potion extends Transport{
	
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
		if (!Bot.configuration.persons.get(0).position.equals(this.getPosition()))
			throw new B4DWrongPosition();
		
		B4DMouse.doubleLeftClick(super.getPositionF(), false);

		B4DWait.waitForMap();
		Bot.configuration.persons.get(0).position = destination;
	}
}
