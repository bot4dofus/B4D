package fr.B4D.classes.transport.transports;

import java.awt.Point;

import fr.B4D.classes.PointF;
import fr.B4D.enu.TransportType;

public class BrakmarPotion extends Potion{
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public BrakmarPotion(Point position, PointF positionF) {
		super(TransportType.BrakmarPotion.toString(), position, positionF, brakmarPotionCost);
	}
}
