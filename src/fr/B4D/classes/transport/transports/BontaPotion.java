package fr.B4D.classes.transport.transports;

import java.awt.Point;

import fr.B4D.classes.PointF;
import fr.B4D.enu.TransportType;

public class BontaPotion extends Potion{
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public BontaPotion(Point position, PointF positionF) {
		super(TransportType.BontaPotion.toString(), position, positionF, bontaPotionCost);
	}
}
