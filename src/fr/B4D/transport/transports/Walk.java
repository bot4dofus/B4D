package fr.B4D.transport.transports;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.Configuration;
import fr.B4D.enu.TransportType;
import fr.B4D.exceptions.B4DCannotFind;
import fr.B4D.exceptions.B4DWrongPosition;
import fr.B4D.modules.B4DMouse;
import fr.B4D.modules.B4DWait;
import fr.B4D.transport.Transport;
import fr.B4D.utils.PointF;

public class Walk extends Transport implements Serializable{
	
	private static final long serialVersionUID = 3052740487765574838L;
	
	  /****************/
	 /** CONSTANTES **/
	/****************/
	
	private final Point down = new Point(0, 1);
	private final Point up = new Point(0, -1);
	private final Point right = new Point(1, 0);
	private final Point left = new Point(-1, 0);
	
	private final PointF goUp = new PointF(0.5, 0.01);
	private final PointF goDown = new PointF(0.5, 0.879);
	private final PointF goLeft = new PointF(0.01, 0.5);
	private final PointF goRight = new PointF(0.99, 0.5);
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Walk(Point position) {
		super(TransportType.Walk.toString(), position, null, walkCost);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void goTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition {
		
		if (!Configuration.getInstance().persons.get(0).position.equals(this.getPosition()))
			throw new B4DWrongPosition();
		
		Point move = new Point(destination.x - super.getPosition().x, destination.y - super.getPosition().y);
		if(move.equals(up))
			B4DMouse.leftClick(goUp, true);
		else if(move.equals(down))
			B4DMouse.leftClick(goDown, true);
		else if(move.equals(left))
			B4DMouse.leftClick(goLeft, true);
		else if(move.equals(right))
			B4DMouse.leftClick(goRight, true);

		B4DWait.waitForMap();
		Configuration.getInstance().persons.get(0).position = destination;
	}
}
