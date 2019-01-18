package fr.B4D.transport.transports;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.enu.TransportType;
import fr.B4D.utils.PointF;

public class BontaPotion extends Potion implements Serializable{
	
	private static final long serialVersionUID = -7210563600870501556L;

	/******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public BontaPotion(Point position, PointF positionF) {
		super(TransportType.BontaPotion.toString(), position, positionF, bontaPotionCost);
	}
}
