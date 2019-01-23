package fr.B4D.transport.transports;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.transport.B4DWrongPosition;
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
		super("Walking", position, null, walkCost);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void goTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition {		
		Point move = new Point(destination.x - super.getPosition().x, destination.y - super.getPosition().y);
		if(move.equals(up))
			B4D.mouse.leftClick(goUp, true);
		else if(move.equals(down))
			B4D.mouse.leftClick(goDown, true);
		else if(move.equals(left))
			B4D.mouse.leftClick(goLeft, true);
		else if(move.equals(right))
			B4D.mouse.leftClick(goRight, true);
	}
}
