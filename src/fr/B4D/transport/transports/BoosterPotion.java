package fr.B4D.transport.transports;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.transport.TransportType;
import fr.B4D.utils.PointF;

public class BoosterPotion extends Potion implements Serializable{
	
	private static final long serialVersionUID = -856550534597183430L;

	/******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public BoosterPotion(Point position, PointF positionF) {
		super(TransportType.BoosterPotion.toString(), position, positionF, boosterPotionCost);
	}
}
