package fr.B4D.classes.transport.transports;

import java.awt.Point;

import fr.B4D.classes.PointF;
import fr.B4D.enu.TransportType;

public class BoosterPotion extends Potion{
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public BoosterPotion(Point position, PointF positionF) {
		super(TransportType.BoosterPotion.toString(), position, positionF, boosterPotionCost);
	}
}
